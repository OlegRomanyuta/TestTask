package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class SignInPage {

  private SelenideElement emailInput() { return $x("//input[@type='email']"); }
  private SelenideElement passwordInput() { return $x("//input[@type='password']"); }
  private SelenideElement submitButton() { return $x("//button[@type='submit']"); }

  public MainPage authenticateUser(String email, String password) {
    emailInput().sendKeys(email);
    passwordInput().sendKeys(password);
    submitButton().click();
    return page(MainPage.class);
  }

}
