package PageObjects.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {
    private final String url = "https://www.amazon.com/";
    private final By searchInput = By.id("twotabsearchtextbox");
    private final By searchSubmit = By.id("nav-search-submit-button");

    public String getUrl() {
        return url;
    }

    public By getSearchInput() {
        return searchInput;
    }

    public By getSearchSubmit() {
        return searchSubmit;
    }
}
