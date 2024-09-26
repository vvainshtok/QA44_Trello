package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String text, int time) {

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean isElementClickable(WebElement element, int time) {
        try {
            WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
