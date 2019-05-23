package contributionTweeter.github;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GitHubUserTest {
    @Test
    public void test() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final GitHubUser gitHubUser = new GitHubUser("T45K");
        final Method setConnection = GitHubUser.class.getDeclaredMethod("setConnection");
        setConnection.setAccessible(true);
        final Method fetchRawData = GitHubUser.class.getDeclaredMethod("fetchRawData", HttpURLConnection.class);
        fetchRawData.setAccessible(true);
        final HttpURLConnection connection = (HttpURLConnection) setConnection.invoke(gitHubUser);
        final List<String> data = (List<String>) fetchRawData.invoke(gitHubUser, connection);
        System.out.println(String.join("\n",data));
    }

    @Test
    public void test2() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final GitHubUser gitHubUser = new GitHubUser("T45K");
        final Method setConnection = GitHubUser.class.getDeclaredMethod("setConnection");
        setConnection.setAccessible(true);
        final Method fetchRawData = GitHubUser.class.getDeclaredMethod("fetchRawData", HttpURLConnection.class);
        fetchRawData.setAccessible(true);
        final HttpURLConnection connection = (HttpURLConnection) setConnection.invoke(gitHubUser);
        final List<String> data = (List<String>) fetchRawData.invoke(gitHubUser, connection);
        System.out.println(Analyzer.analyzeRawData(data, "T45K").getDate());
        System.out.println(Analyzer.analyzeRawData(data, "T45K").getNumOfContinuousContribution());
        System.out.println(Analyzer.analyzeRawData(data, "T45K").getNumOfYesterdayContribution());
    }

    @Test
    public void test3(){
        final Date date = new Date();
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);

        instance.add(Calendar.DATE, 1);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.HOUR_OF_DAY,0);
        System.out.println(instance.getTime());
    }
}
