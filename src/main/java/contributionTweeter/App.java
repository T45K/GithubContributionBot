package contributionTweeter;

import contributionTweeter.github.ContributionData;
import contributionTweeter.github.GitHubUser;
import contributionTweeter.twitter.Tweeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static int TIME_OF_ONE_DAY = 1000 * 60 * 60 * 24;

    public static void main(final String[] args) throws IOException {
        final Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get(args[0])));

        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final String userName = properties.getProperty("userName");
                final GitHubUser user;

                try {
                    user = new GitHubUser(userName);
                } catch (
                        final MalformedURLException e) {
                    logger.error("Specified user does not exist");
                    return;
                }

                final ContributionData contributionData = user.fetchContributionData();

                if (contributionData == null) {
                    logger.error("connection was failed");
                    return;
                }

                final Tweeter tweeter = new Tweeter(properties);
                tweeter.tweet(contributionData);
            }
        };

        final Date today = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final String format = simpleDateFormat.format(calendar.getTime());

        if (format.endsWith("00:00:00")) {
            timer.schedule(task, TIME_OF_ONE_DAY);
        } else {
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            timer.schedule(task, calendar.getTime(), TIME_OF_ONE_DAY);
        }
    }
}
