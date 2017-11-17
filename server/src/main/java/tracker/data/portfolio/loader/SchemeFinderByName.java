package tracker.data.portfolio.loader;

import com.gs.collections.impl.list.mutable.FastList;
import org.apache.commons.text.similarity.LevenshteinDistance;
import tracker.domain.Scheme;
import tracker.domain.SchemeFinder;
import tracker.domain.SchemeList;

import java.util.Comparator;
import java.util.List;

public class SchemeFinderByName {
    private final SchemeList schemes;

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
        schemeNameFromTx = schemeNameFromTx.replaceAll("motilal oswal most", "most");
        if (Character.isDigit(schemeNameFromTx.charAt(0))) {
            schemeNameFromTx = schemeNameFromTx.substring(schemeNameFromTx.indexOf(' ') + 1);
        }
        schemeNameFromTx = simplify(schemeNameFromTx);

        List<Match> similarTexts = similarTexts(schemeNameFromTx, new LevenshteinDistance()::apply);
        for (Match topMatch : similarTexts) {
            System.out.println(topMatch.distance + " :: " + topMatch.match);
        }

        return similarTexts.get(0).schemeCode;
    }

    private List<Match> similarTexts(String schemeNameFromTx, SimilarityScore similarityScore) {
        List<Match> matches = FastList.newList();
        for (Scheme scheme : schemes) {
            String schemeName = scheme.getName();
            if (schemeNameFromTx.equals(schemeName)) {
                return FastList.newListWith(new Match(scheme.getCode(), schemeName, 0));
            }
            Integer result = similarityScore.apply(schemeNameFromTx, simplify(schemeName.toLowerCase()));
            Match match = new Match(scheme.getCode(), schemeName, result);
            matches.add(match);
        }
        matches.sort(Comparator.comparingInt(o -> o.distance));
        return FastList.newListWith(matches.get(0), matches.get(1));
    }

    private static String simplify(String schemeName) {
        schemeName = schemeName.replaceAll("-", " ");
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

