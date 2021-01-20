package search;

import org.example.AppConfig;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSearchTest {
    @Test
    public void simpleSearchWithOneKeyWord(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());

        WebDriver driver = new ChromeDriver();

        driver.get(AppConfig.getSiteUrl());

        String searchKeyword = "vase";

        driver.findElement(By.id("search")).sendKeys(searchKeyword + Keys.ENTER);
        System.out.println("Pressed Enter in search field");

        List<WebElement> productNames = driver.findElements(By.cssSelector("h2.product-name a"));

        System.out.println("Stored " + productNames.size() + " product names");

        for (WebElement productName : productNames){

            assertThat("Some of the products' names do not contain the searched keyword.",
                    productName.getText(), containsString(searchKeyword.toUpperCase()));
        }
    }
}