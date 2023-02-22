package PageObjects.BestBuy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BBSearchPage {
    private WebDriver driver;
    private String productName = "";
    private By product;
    public BBSearchPage(WebDriver driver, String productName) {
        this.driver = driver;
        this.productName = productName;
        product = By.xpath("//*[contains(text(),\""+productName+"\")]/ancestor::div[@class=\"list-item lv\"]");
    }
    public List<WebElement> getProducts() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(product));
        Thread.sleep(5000);
        return driver.findElements(product);
    }
    public WebElement getMostDiscussed(List<WebElement> ls) {
        By locator = By.xpath(".//span[@class=\"c-reviews \"]");
        WebElement elementMax = ls.get(0);
        String val = elementMax.findElement(locator).getAttribute("textContent");
        int numMax = getNum(val);
        for (int i =0;i< ls.size();i++) {
            WebElement tempElement = ls.get(i);
            String tempVal = tempElement.findElement(locator).getAttribute("textContent");
            int tempNum = getNum(tempVal);
            if (numMax<tempNum) {
                elementMax = tempElement;
                numMax = tempNum;
            }
        }
        return elementMax;
    }

    public Double getPrice(WebElement element) {
        By locator = By.xpath(".//div[@class=\"priceView-hero-price priceView-customer-price\"]/span[@aria-hidden]");
        String val = element.findElement(locator).getAttribute("textContent");
        String priceStr = val;
        double price = Double.parseDouble(priceStr.replaceAll("[^\\d.]", ""));
        return price;
    }

    public int getNum(String val) {
        String str = val;
        if (val.equals("Not Yet Reviewed")) {
            return 0;
        }
        int num = Integer.parseInt(str.replaceAll("[^0-9]", ""));
        return num;
    }

}
