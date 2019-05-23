package contributionTweeter.github;

import java.util.List;

class Analyzer {
    static ContributionData analyzeRawData(final List<String> rawData, final String userName) {
        int counter = 0;
        for (final String oneDayActivity : rawData) {
            final String[] elements = oneDayActivity.split("\\s+");
            final String numOfContributionOfTheDay = getStringInDoubleQuote(elements[8]);
            if (numOfContributionOfTheDay.equals("0")) {
                counter = 0;
                continue;
            }
            counter++;
        }

        final String[] lastActivity = rawData.get(rawData.size() - 1).split("\\s+");
        final String contribution = getStringInDoubleQuote(lastActivity[8]);
        final String date = getStringInDoubleQuote(lastActivity[9]);

        return new ContributionData(userName, date, contribution, counter);
    }

    private static String getStringInDoubleQuote(final String string) {
        final int begin = string.indexOf("\"") + 1;
        final int end = string.lastIndexOf("\"");
        return string.substring(begin, end);
    }
}
