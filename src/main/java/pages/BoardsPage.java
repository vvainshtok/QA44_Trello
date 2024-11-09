package pages;

import dto.BoardDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BasePage {

    public BoardsPage (WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy (xpath = "//li[@data-testid='create-board-tile']")
    WebElement btnCreateBoard;

    @FindBy (xpath = "//input[@data-testid='create-board-title-input']")
    WebElement inputBoardTitle;

    @FindBy (xpath = "//button[@data-testid='create-board-submit-button']")
    WebElement btnCreateSubmit;

    @FindBy(xpath = "//span[@class='QMKgZFIlTLiEJN']")
    WebElement popUpBoardDeleted;

    @FindBy(xpath = "//button[@data-testid='header-member-menu-button']")
    WebElement btnHeaderProfile;

    @FindBy(xpath = "//a[@data-testid='manage-account-link']")
    WebElement btnManageAccount;

    public BoardsPage typeBoardTitle(BoardDTO board) {
        btnCreateBoard.click();
        inputBoardTitle.sendKeys(board.getBoardTitle());
        return this;
    }

    public PersonalBoardPage clickBtnCreateSubmitPositive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(btnCreateSubmit));
        btnCreateSubmit.click();
        return new PersonalBoardPage(driver);
    }

    public BoardsPage clickBtnCreateSubmitNegative() {
        btnCreateSubmit.click();
        return this;
    }

    public boolean isElementClickableBtnCreateSubmit() {
        return isElementClickable(btnCreateSubmit, 3);
    }

    public boolean isTextPopUpPresent() {
            return isTextInElementPresent(popUpBoardDeleted, "Board deleted.", 5);
    }

    public ProfileAndVisibilityPage goToProfileAndVisibility() {

        btnHeaderProfile.click();
        btnManageAccount.click();
        return new ProfileAndVisibilityPage(driver);
    }


    public PersonalBoardPage clickElement2ListBoards() {
        driver.findElement(By.xpath("//ul[@class='boards-page-board-section-list']/li[2]")).click();
        return new PersonalBoardPage(driver);
    }
}
