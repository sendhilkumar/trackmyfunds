import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

public class FundSearch {
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();

        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        Document doc1 = new Document();
        doc1.add(new Field("schemeName", "This is the text to be indexed.", TextField.TYPE_STORED));
        Document doc2 = new Document();
        doc2.add(new Field("schemeName", "another text", TextField.TYPE_STORED));
        indexWriter.addDocument(doc1);
        indexWriter.addDocument(doc2);
        indexWriter.close();

        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        QueryParser parser = new QueryParser("schemeName", analyzer);
        serach(indexSearcher, parser, "text");
        serach(indexSearcher, parser, "txt");
        serach(indexSearcher, parser, "other");
        serach(indexSearcher, parser, "another");

        directoryReader.close();
        directory.close();
    }

    private static void serach(IndexSearcher indexSearcher, QueryParser parser, String text) throws ParseException, IOException {
        System.out.println("Searching "+text);
        Query query = parser.parse(text+"~");

        ScoreDoc[] hits = indexSearcher.search(query, 100).scoreDocs;
        System.out.println("found "+hits.length);
        for (ScoreDoc hit : hits) {
            Document hitDoc = indexSearcher.doc(hit.doc);
            System.out.println(hitDoc.get("schemeName"));
        }
        System.out.println();
    }
}
