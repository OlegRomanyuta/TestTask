package tests;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

public class RealWorldTest extends TestBase {

  @BeforeMethod
  public void setUp() {
    createDriver();
  }

  @AfterMethod
  void tearDown() {
    removeDriver();
  }

  @Test
  @Description(value = "Test checks checks filter by tag functionality on main page")
  public void tagsFilterFunctionalTestWithNotAuthenticatedUser() {
    new MainPage().open()
                  .checkChosenTagIsPresentInEveryRecordOnPage();
  }

  @Test
  @Description(value = "Test authenticate user and checks filter by tag functionality on main page")
  public void tagsFilterFunctionalTestWithAuthenticatedUser() {
    new MainPage().open()
                  .clickSignInButton()
                  .authenticateUser("realworld@mailinator.com", "password")
                  .checkChosenTagIsPresentInEveryRecordOnPage();
  }
}
