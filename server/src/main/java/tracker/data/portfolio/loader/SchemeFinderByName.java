package tracker.data.portfolio.loader;

import com.gs.collections.impl.list.mutable.FastList;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.Scheme;
import tracker.domain.SchemeList;
import tracker.server.SchemesServices;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SchemeFinderByName {
    private static Logger logger = LoggerFactory.getLogger(SchemeFinderByName.class.getName());

    private final SchemeList schemes;
    private final SchemesServices schemesServices = new SchemesServices();

    public SchemeFinderByName(SchemeList schemes) {
        this.schemes = schemes;
    }

    public int find(String schemeNameFromTx) {
        schemeNameFromTx = schemeNameFromTx.toLowerCase();
        schemeNameFromTx = schemeNameFromTx.substring(schemeNameFromTx.indexOf("-") + 1);
        int advisorIndex = schemeNameFromTx.indexOf("(advisor");
        if (advisorIndex != -1) {
            schemeNameFromTx = schemeNameFromTx.substring(0, advisorIndex);
        }
        schemeNameFromTx = schemeNameFromTx.replaceAll("motilal oswal most focused", "most focused");
        schemeNameFromTx = schemeNameFromTx.replaceAll("motilal oswal focused", "most focused");
        schemeNameFromTx = schemeNameFromTx.replaceAll("motilal oswal", "most focused");
        if (Character.isDigit(schemeNameFromTx.charAt(0))) {
            schemeNameFromTx = schemeNameFromTx.substring(schemeNameFromTx.indexOf(' ') + 1);
        }
        schemeNameFromTx = simplify(schemeNameFromTx);

        SimilarityScore similarityScore = new LevenshteinDistance()::apply;

        List<Match> similarTexts = similarTexts(schemeNameFromTx, similarityScore);
        Match topMatch = similarTexts.get(0);

        int schemeCode;
        try {
            List<Scheme> schemes = schemesServices.find(schemeNameFromTx);
            Scheme scheme = schemes.get(0);

            Integer score = similarityScore.apply(schemeNameFromTx, simplify(scheme.getName().toLowerCase()));
            if (topMatch.distance < score) {
                schemeCode = topMatch.schemeCode;
                logger.info(1 + "::" + topMatch.distance + " :: " + topMatch.match);
            }else{
                schemeCode = scheme.getCode();
                logger.info(2 + "::" + score + " :: " + scheme.getName());
            }
            logger.info("Mapping::"+schemeNameFromTx + "::" + scheme.getName());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return schemeCode;
    }

    private List<Match> similarTexts(String schemeNameFromTx, SimilarityScore similarityScore) {
        List<Match> matches = FastList.newList();
        for (Scheme scheme : schemes) {
            String schemeName = scheme.getName();
            if (schemeNameFromTx.equals(schemeName)) {
                return FastList.newListWith(new Match(scheme.getCode(), schemeName, 0));
            }
            Integer distance = similarityScore.apply(schemeNameFromTx, simplify(schemeName.toLowerCase()));
            Match match = new Match(scheme.getCode(), schemeName, distance);
            matches.add(match);
        }
        matches.sort(Comparator.comparingInt(o -> o.distance));
        return FastList.newListWith(matches.get(0), matches.get(1), matches.get(2), matches.get(3), matches.get(5));
    }

    private static String simplify(String schemeName) {
        schemeName = schemeName.replaceAll("-", " ");
        schemeName = schemeName.replaceAll("/", " ");
        schemeName = schemeName.replaceAll("fund", "");
        schemeName = schemeName.replaceAll("direct plan", "direct");
        schemeName = schemeName.replaceAll("growth plan", "growth");
        schemeName = schemeName.replaceAll("growth option", "growth");
        schemeName = schemeName.replaceAll("dividend option", "dividend");
        schemeName = schemeName.replaceAll("dividend plan", "dividend");
        schemeName = schemeName.replaceAll("reinvestment", "");
        schemeName = schemeName.replaceAll("reinvest", "");
        schemeName = schemeName.replaceAll("  ", " ");
        schemeName = schemeName.replaceAll("  ", " ");
        schemeName = schemeName.replaceAll(" & ", " and ");
        schemeName = schemeName.trim();
        return schemeName;
    }

    private static class Match {
        private int schemeCode;
        private String match;
        private int distance;

        public Match(int schemeCode, String match, int distance) {
            this.schemeCode = schemeCode;
            this.match = match;
            this.distance = distance;
        }
    }

    public interface SimilarityScore {
        Integer apply(CharSequence left, CharSequence right);
    }
}

