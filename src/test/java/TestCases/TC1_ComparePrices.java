package TestCases;

import PageObjects.Amazon.Dashboard;
import PageObjects.Amazon.ResultSearchPage;
import PageObjects.BestBuy.BBDashboard;
import PageObjects.BestBuy.BBSearchPage;
import Tools.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC1_ComparePrices extends BaseClass {
    private Double AmazonPrice;
    private Double BBPrice;
    @Test(priority = 0)
    public void AmazonPriceTest() {
        Dashboard dashboard = new Dashboard();
        navigateTo(dashboard.getUrl());
        String product = "SAMSUNG Galaxy S22";
        getElement(dashboard.getSearchInput()).sendKeys(product);
        getElement(dashboard.getSearchSubmit()).submit();
        ResultSearchPage result = new ResultSearchPage(product,driver);
        List<WebElement> ls = result.getProducts();
        WebElement element = result.getTheElem(ls);
        Double price = result.getPrice(element);

        System.out.println(price);
        AmazonPrice = price;

    }
    @Test(priority = 1)
    public void BBPriceTest() throws InterruptedException {
        BBDashboard dashboard = new BBDashboard();
        navigateTo(dashboard.getUrl());
        String productName = "Samsung Galaxy S22";
        getElement(dashboard.getSearchInput()).sendKeys(productName);
        getElement(dashboard.getSearchBtn()).submit();

        BBSearchPage searchPage = new BBSearchPage(driver,"S22");
        List<WebElement> ls = searchPage.getProducts();
        WebElement element = searchPage.getMostDiscussed(ls);
        Double price = searchPage.getPrice(element);
        System.out.println(price);
        BBPrice = price;
    }
    @Test(priority = 2)
    public void ComparePrices() {
        Assert.assertTrue(AmazonPrice<BBPrice);
    }
}
