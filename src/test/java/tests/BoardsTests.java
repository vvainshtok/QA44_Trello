package tests;

import dataproviders.DataProviderBoards;
import dto.BoardDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.PersonalBoardPage;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import static pages.BasePage.pause;

@Listeners(TestNGListener.class)

public class BoardsTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("vvainshtok67@gmail.com")
            .password("gfyxbo0A!")
            .build();

    BoardsPage boardsPage = new BoardsPage(getDriver());

    @BeforeMethod
    public void loginBeforeBoards() {
        HomePage homePage = new HomePage(getDriver());
        boardsPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);
    }


    @Test
    public void createBoardPositive(Method method) {
        // int i = (int) ((System.currentTimeMillis()/1000)/3600);
        int i = new Random().nextInt(1000) + 1000;
        BoardDTO board = BoardDTO.builder()
                .boardTitle("QA44-" + i)
                .build();

        logger.info(method.getName() + " starts with board title --> " + board.getBoardTitle());

        Assert.assertTrue(boardsPage.typeBoardTitle(board)
                .clickBtnCreateSubmitPositive()
             .isTextinElementPresentNameBoard(board.getBoardTitle()));
    }

    @Test
    public void createBoardNegative() {
        BoardDTO board = BoardDTO.builder()
                .boardTitle("   ")
                .build();

        Assert.assertFalse(boardsPage.typeBoardTitle(board)
                .clickBtnCreateSubmitNegative()
                .isElementClickableBtnCreateSubmit(), "element is clickable");
    }

    @Test(dataProvider = "DPFile_deleteBoardPositiveTest", dataProviderClass = DataProviderBoards.class)
    public void deleteBoardPositiveTest(BoardDTO board) {
        PersonalBoardPage personalBoardPage = boardsPage
                .typeBoardTitle(board)
                .clickBtnCreateSubmitPositive();

        if (personalBoardPage.isTextinElementPresentNameBoard(board.getBoardTitle())) {
            Assert.assertTrue(personalBoardPage.deleteBoard(board)
                    .isTextPopUpPresent());
        } else {
            Assert.fail("board isn't created");
        }
    }

    @Test
    public void deleteAllBoards() { // не работает, Мария обещала разобраться
        pause(3);
        List<WebElement> listBoards = getDriver().findElements(
                By.xpath("//li[@class='boards-page-board-section-list-item']"));
        System.out.println("Size of list ---> " + listBoards.size());
        for (int i = 0; i < listBoards.size()-2; i++) {
            boardsPage.clickElement2ListBoards().deleteBoard();
            pause(5);
        }
    }
}
