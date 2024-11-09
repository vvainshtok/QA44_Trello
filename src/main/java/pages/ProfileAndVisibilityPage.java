package pages;

import dto.UserDTO;
import interfaces.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileAndVisibilityPage extends BasePage implements Path {

    public ProfileAndVisibilityPage (WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement profilePhoto;
    @FindBy(xpath = "//button[@data-testid='change-avatar']")
    WebElement changeProfilePhoto;
   // @FindBy(xpath = "//button[@data-testid='upload-button']")
   // WebElement uploadPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnUpload;
    @FindBy(id="image-input")
    WebElement inputUploadPhoto;
    @FindBy(xpath = "//div[@class='css-1748k3u']")
    WebElement popUpMessageAvatarAdded;
    @FindBy(xpath = "//span[@class='css-1fd0fe1']")
    WebElement messageWrongFileFormat;



    public ProfileAndVisibilityPage changeAvatar(String fileName) {

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Actions actions = new Actions(driver);
        actions.moveToElement(profilePhoto).pause(2000).click().perform();
        clickWait(changeProfilePhoto, 3);
       // changeProfilePhoto.click();
       // pause(3000);
        File file = new File(PHOTOS_PATH + fileName);
        String filePath = file.getAbsolutePath();
        inputUploadPhoto.sendKeys(filePath);
        clickWait(btnUpload, 3);
        return this;
    }

    public boolean isTextInElementPresent_AvatarAdded() {
        return isTextInElementPresent(popUpMessageAvatarAdded,"uploaded your new avatar", 7);
    }

    public boolean isTextInElementPresent_WrongFileFormat() {
        return isTextInElementPresent(messageWrongFileFormat, "Upload a photo", 7);

    }
}
