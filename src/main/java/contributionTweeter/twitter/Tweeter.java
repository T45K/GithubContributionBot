package contributionTweeter.twitter;

import contributionTweeter.github.ContributionData;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.Properties;

public class Tweeter {
    private final Twitter twitter;

    public Tweeter(final Properties properties) {
        final String apiKey = properties.getProperty("apiKey");
        final String apiSecret = properties.getProperty("apiSecret");
        final String token = properties.getProperty("token");
        final String tokenSecret = properties.getProperty("tokenSecret");

        this.twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(apiKey, apiSecret);
        final AccessToken accessToken = new AccessToken(token, tokenSecret);
        twitter.setOAuthAccessToken(accessToken);
    }

    public void tweet(final ContributionData data) {
        try {
            this.twitter.updateStatus(getContents(data));
        } catch (final TwitterException e) {
            e.printStackTrace();
        }
    }

    private String getContents(final ContributionData data) {
        return data.getDate()
                + "のコントリビューション数: "
                + data.getNumOfYesterdayContribution()
                + "\n"
                + "連続コントリビューション日数: "
                + data.getNumOfContinuousContribution()
                + "\n"
                + "https://github.com/"
                + data.getUserName();
    }
}
