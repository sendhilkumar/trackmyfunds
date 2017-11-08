package tracker.server;

public class PortfolioSnapshot {
    private final PortfolioValue todayPortfolioValue;
    private final PortfolioValue priorDayPortfolioValue;
    private final PortfolioValueOneDayDelta portfolioValueOneDayDelta;

    public PortfolioSnapshot(PortfolioValue todayPortfolioValue, PortfolioValue priorDayPortfolioValue,
                             PortfolioValueOneDayDelta portfolioValueOneDayDelta) {

        this.todayPortfolioValue = todayPortfolioValue;
        this.priorDayPortfolioValue = priorDayPortfolioValue;
        this.portfolioValueOneDayDelta = portfolioValueOneDayDelta;
    }

    public PortfolioValue getTodayPortfolioValue() {
        return todayPortfolioValue;
    }

    public PortfolioValue getPriorDayPortfolioValue() {
        return priorDayPortfolioValue;
    }

    public PortfolioValueOneDayDelta getPortfolioValueOneDayDelta() {
        return portfolioValueOneDayDelta;
    }
}
