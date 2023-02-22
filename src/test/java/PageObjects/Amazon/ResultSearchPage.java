package PageObjects.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultSearchPage {
    private WebDriver driver;
    private String productName;
    private By locator;
    public ResultSearchPage(String productName, WebDriver driver) {
        this.driver = driver;
        this.productName = productName;
        locator = By.xpath("//div[@class=\"a-section a-spacing-small a-spacing-top-small\"]//span[contains(text(),\""+productName+"\")]/ancestor::div[@data-csa-c-type]");
    }

    public List<WebElement> getProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }
    public WebElement getTheElem(List<WebElement> list) {
        WebElement elementMax = list.get(0);
        By locator = By.xpath(".//span[@class=\"a-size-base s-underline-text\"]");
        String val = elementMax.findElement(locator).getText();
        int max = getNum(val);
        for (int i = 1;i<list.size();i++) {
            WebElement tempElem = list.get(i);
            String tempVal = tempElem.findElement(locator).getText();
            int tempNum = getNum(tempVal);
            if (max<tempNum) {
                max = tempNum;
                elementMax = tempElem;
            }
        }
        return elementMax;
    }
    public Double getPrice(WebElement element) {
        By locator = By.xpath(".//span[@class=\"a-price\"]/span[@class=\"a-offscreen\"]");
        String val = element.findElement(locator).getAttribute("textContent");
        String priceStr = val;
        double price = Double.parseDouble(priceStr.replaceAll("[^\\d.]", ""));
        return price;
    }

    public int getNum(String val) {
        String str = val;
        int num = Integer.parseInt(str.replaceAll("[^0-9]", ""));
        return num;
    }
}
