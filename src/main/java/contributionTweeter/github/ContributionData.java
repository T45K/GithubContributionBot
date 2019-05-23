package contributionTweeter.github;

public class ContributionData {
    private final String userName;
    private final String date;
    private final String numOfYesterdayContribution;
    private final int numOfContinuousContribution;

    ContributionData(final String userName, final String date, final String numOfYesterdayContribution, final int numOfContinuousContribution) {
        this.userName = userName;
        this.date = date;
        this.numOfYesterdayContribution = numOfYesterdayContribution;
        this.numOfContinuousContribution = numOfContinuousContribution;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getNumOfYesterdayContribution() {
        return numOfYesterdayContribution;
    }

    public int getNumOfContinuousContribution() {
        return numOfContinuousContribution;
    }
}
