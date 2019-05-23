package contributionTweeter.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GitHubUser {
    private static final String USER_URL_PREFIX = "https://github.com/users/";
    private static final String USER_URL_SUFFIX = "/contributions";
    private final URL userURL;
    private final String userName;

    public GitHubUser(final String userName) throws MalformedURLException {
        this.userURL = new URL(USER_URL_PREFIX + userName + USER_URL_SUFFIX);
        this.userName = userName;
    }

    public ContributionData fetchContributionData() {
        try {
            final HttpURLConnection connection = setConnection();
            final List<String> rawData = fetchRawData(connection);
            if (rawData == null) {
                return null;
            }

            return Analyzer.analyzeRawData(rawData, userName);
        } catch (final IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    private HttpURLConnection setConnection() throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) userURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.connect();

        return connection;
    }

    private List<String> fetchRawData(final HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            return readInputStream(connection.getInputStream());
        } else {
            return null;
        }
    }

    private List<String> readInputStream(final InputStream inputStream) throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final List<String> contents = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (isNecessaryInformation(line)) {
                contents.add(line);
            }
        }

        return contents;
    }

    private boolean isNecessaryInformation(final String line) {
        return line.contains("<rect") && !line.contains(getDate());
    }

    private String getDate() {
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).format(calendar.getTime());
    }
}
