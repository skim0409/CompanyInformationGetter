package Stocks;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void createStockList(ArrayList<CompanyInfo> stocks) {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> sym = new ArrayList<>();
        ArrayList<String> mcap = new ArrayList<>();
        final String url = "https://companiesmarketcap.com/usa/largest-companies-in-the-usa-by-market-cap/";
        try {
            final Document doc = Jsoup.connect(url).get();
            for (Element row : doc.select("table.table.marketcap-table.dataTable tr")) {
                if (row.select(".company-name").text().equals("")) {
                    continue;
                } else {
                    final String ticker = row.select(".company-name").text();
                    name.add(ticker);
                }
            }
            for (Element row : doc.select("table.table.marketcap-table.dataTable tr")) {
                if (row.select(".company-code").text().equals("")) {
                    continue;
                } else {
                    final String symbol = row.select(".company-code").text();
                    sym.add(symbol);
                }
            }
            for (Element row : doc.select("table.table.marketcap-table.dataTable tr")) {
                if (row.select("td.td-right:nth-of-type(3)").text().equals("")) {
                    continue;
                } else {
                    final String market = row.select("td.td-right:nth-of-type(3)").text();
                    mcap.add(market);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < name.size(); i++) {
            stocks.add(new CompanyInfo(name.get(i), sym.get(i), mcap.get(i)));
        }
    }

    public static boolean containsStock(ArrayList<CompanyInfo> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public static String getName(ArrayList<CompanyInfo> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return arr.get(i).getName();
            }
        }
        return "";
    }

    public static String getSymbol(ArrayList<CompanyInfo> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return arr.get(i).getSymbol();
            }
        }
        return "";
    }

    public static String getMcap(ArrayList<CompanyInfo> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return arr.get(i).getMarketcap();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<CompanyInfo> arr = new ArrayList<>();
        createStockList(arr);
        System.out.print("Enter the Company's Nama: ");
        String name = input.nextLine();
        if(containsStock(arr, name)) {
            System.out.println("Company Info:");
            System.out.print("Name: " + getName(arr, name) + " | ");
            System.out.print("Symbol: " + getSymbol(arr, name) + " | ");
            System.out.print("Market Cap: " + getMcap(arr, name));
            System.out.println("Thank you for using this program!");
        }




    }


}
