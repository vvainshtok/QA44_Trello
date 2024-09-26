package pages;

import dto.UserDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@data-testid='username']")
    WebElement inputEmail;

    @FindBy(id = "login-submit")
    WebElement btnContinue;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//span[text()='Log in']/..")
    WebElement btnLoginSubmit;


    public LoginPage typeEmail(UserDTO user) {
        inputEmail.sendKeys(user.getEmail());
        btnContinue.click();
        return this;
    }

    public BoardsPage typePassword(UserDTO user) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(inputPassword))
                .sendKeys(user.getPassword());
        btnLoginSubmit.click();
        return new BoardsPage(driver);
    }

}
