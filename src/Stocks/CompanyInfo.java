package Stocks;

public class CompanyInfo {

    String name;
    String symbol;
    String marketcap;

    public CompanyInfo(String name, String symbol, String marketcap) {
        this.name = name;
        this.symbol = symbol;
        this.marketcap = marketcap;
    }

    public String getName() {
        return this.name;
    }
    public String getSymbol() {
        return this.symbol;
    }
    public String getMarketcap() {
        return this.marketcap;
    }

    public static void main(String[] args) {

    }
    

}
