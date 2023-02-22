package PageObjects.BestBuy;

import org.openqa.selenium.By;

public class BBDashboard {
    private final String url = "https://www.bestbuy.com/?intl=nosplash";
    private final By searchInput = By.id("gh-search-input");
    private final By searchBtn = By.xpath("//button[@title=\"submit search\"]");

    public String getUrl() {
        return url;
    }

    public By getSearchInput() {
        return searchInput;
    }

    public By getSearchBtn() {
        return searchBtn;
    }
}
