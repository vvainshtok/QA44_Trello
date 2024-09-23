package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
        .email("vvainshtok67@gmail.com")
        .password("gfyxbo0A!")
        .build();

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);


    }
}
