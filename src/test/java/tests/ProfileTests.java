package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import manager.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfileAndVisibilityPage;

public class ProfileTests extends ApplicationManager {

    // UserDTO user = UserDTO.builder()
    //        .email("vvainshtok67@gmail.com")
    //        .password("gfyxbo0A!")
    //        .build();

    ProfileAndVisibilityPage profileAndVisibilityPage;

    @BeforeMethod
    public void loginBeforeProfile() {
        HomePage homePage = new HomePage(getDriver());
        profileAndVisibilityPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user)
                .goToProfileAndVisibility();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void changeProfilePhotoPositiveTest() {
        Assert.assertTrue(profileAndVisibilityPage
                .changeAvatar("wrong_img.png")
                .isTextInElementPresent_AvatarAdded());
    }

    @Test
    public void changeProfilePhotoNegativeTest_wrongFileFormat() {
        Assert.assertTrue(profileAndVisibilityPage
                .changeAvatar("wrong_file.log")
                .isTextInElementPresent_WrongFileFormat());
    }
}
