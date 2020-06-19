package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

  private SelenideElement loader() { return $x("//div[@class='article-preview'][@ng-hide]"); }
  private SelenideElement signInButton() { return $x("//a[@href='#/login']"); }
  private SelenideElement tagOverTable() { return $(".ng-binding.active"); }
  private ElementsCollection recordsOnPage() { return $$x("//div[@class='article-preview']"); }
  private ElementsCollection tagsInRecord(int recordNumber) { return $$x("(//div[@class='article-preview'])[ '" + recordNumber + "']//ul[@class='tag-list']/li"); }
  private ElementsCollection tagsInTagBlock() { return $$(".tag-list a"); }

  @Step("Open main page")
  public MainPage open() {
    Selenide.open("");
    return this;
  }

  @Step("Select tag in filter and check its presence in each record on page")
  public MainPage checkChosenTagIsPresentInEveryRecordOnPage() {
    tagsInTagBlock().shouldHave(sizeGreaterThan(0));
    tagsInTagBlock().forEach (tag -> {
        String chosenTagText = tag.text();
        tag.click();
        loader().shouldNotBe(visible);
        tagOverTable().shouldHave(exactText(chosenTagText));
        for (int recordNumber = 1; recordNumber <= recordsOnPage().size(); recordNumber++) {
              tagsInRecord(recordNumber).findBy(exactText(chosenTagText)).shouldHave(exactText(chosenTagText));
        }
    });
    return this;
  }

  @Step("Click sign in button and go to main page")
  public SignInPage clickSignInButton() {
    signInButton().click();
    return page(SignInPage.class);
  }
}

