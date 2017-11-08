package tracker.server;

import com.gs.collections.impl.list.mutable.FastList;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.complexPhrase.ComplexPhraseQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import tracker.domain.Scheme;

import java.io.IOException;
import java.util.List;

public class SchemeIndex {
    private static final String SCHEME_NAME = "SCHEME_NAME";
    private static final String SCHEME_NAME_START = "SCHEME_NAME_START";
    private static final String SCHEME_CODE = "SCHEME_CODE";
    private static final String AMC = "AMC";

    private final QueryParser schemeNameStartParser;
    private final QueryParser schemeNameParser;

    private final DirectoryReader directoryReader;
    private final IndexSearcher indexSearcher;
    private final Directory directory;

    public SchemeIndex(List<Scheme> schemeList) {
        try {
            Analyzer analyzer = new StandardAnalyzer();
            directory = new RAMDirectory();
            schemeNameStartParser = new ComplexPhraseQueryParser(SCHEME_NAME_START, analyzer);
            schemeNameParser = new ComplexPhraseQueryParser(SCHEME_NAME, analyzer);

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);

            for (Scheme scheme : schemeList) {
                Document doc = new Document();

                doc.add(new StoredField(SCHEME_NAME_START, scheme.getName().split(" ")[0], TextField.TYPE_STORED));
                doc.add(new StoredField(SCHEME_NAME, scheme.getName(), TextField.TYPE_STORED));
                doc.add(new StoredField(SCHEME_CODE, String.valueOf(scheme.getCode()), TextField.TYPE_STORED));
                doc.add(new StoredField(AMC, scheme.getAmc()));

                indexWriter.addDocument(doc);
            }

            indexWriter.close();

            directoryReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(directoryReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Scheme> findSchemes(String schemeName) throws IOException, ParseException {
        String[] terms = schemeName.split(" ");

        String schemeNameStartTerm = terms[0];
        Query exactStartsWith = new BoostQuery(schemeNameStartParser.parse(schemeNameStartTerm + "*"), 5);
        Query fuzzyStartsWith = schemeNameStartParser.parse(schemeNameStartTerm + "~");

        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(exactStartsWith, BooleanClause.Occur.SHOULD);
        builder.add(fuzzyStartsWith, BooleanClause.Occur.SHOULD);

        if (terms.length > 1) {
            for (int i = 1; i < terms.length; i++) {
                String term = terms[i];
                Query termQuery = schemeNameParser.parse(term);
                builder.add(termQuery, BooleanClause.Occur.SHOULD);

                Query fuzzyTermQuery = schemeNameParser.parse(term + "~");
                builder.add(fuzzyTermQuery, BooleanClause.Occur.SHOULD);
            }
        } else {
            Query fuzzyTermQuery = new BoostQuery(schemeNameParser.parse(schemeName + "~"), (float) 0.3);
            builder.add(fuzzyTermQuery, BooleanClause.Occur.SHOULD);
        }

        Query termsQuery = schemeNameParser.parse(schemeName);
        builder.add(termsQuery, BooleanClause.Occur.SHOULD);

        Query query = builder.build();

        List<ScoreDoc> scoreDocs = FastList.newListWith(indexSearcher.search(query, 20).scoreDocs);

        List<Scheme> results = FastList.newList();
        for (ScoreDoc hit : scoreDocs) {
            Document hitDoc = indexSearcher.doc(hit.doc);
            results.add(new Scheme(hitDoc.get(SCHEME_CODE), hitDoc.get(SCHEME_NAME), hitDoc.get(AMC)));
        }

        return results;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        directoryReader.close();
        directory.close();
    }
}
