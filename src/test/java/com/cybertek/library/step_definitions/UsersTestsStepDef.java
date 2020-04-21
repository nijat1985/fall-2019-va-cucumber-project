package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class UsersTestsStepDef {
    UsersPage usersPage = new UsersPage();
    Faker fakeData = new Faker();
    String emailToCheck;
    String groupToCheck;
    String statusToCheck;
    String fullNameToCheck;

    @Given("I click on Add User")
    public void i_click_on_Add_User() {
        usersPage.addUser.click();
    }

    @Then("start date should be today's date")
    public void start_date_should_be_today_s_date() {
        String startDate = usersPage.start_date.getAttribute("value");
        String currentDate = LocalDate.now().toString();
        Assert.assertEquals(startDate,currentDate);
    }

    @Then("end date should be one month from today")
    public void end_date_should_be_one_month_from_today() {
        String endDate = usersPage.end_date.getAttribute("value");
        String startDate = usersPage.start_date.getAttribute("value");
        Assert.assertEquals(BrowserUtils.checkMonth(startDate,endDate),1);
    }

    @Given("I enter new user information with random email")
    public void i_enter_new_user_information_with_random_email() {
        usersPage.fullName.sendKeys(fakeData.name().fullName());
        usersPage.password.sendKeys(fakeData.name().firstName().substring(0,2).toUpperCase() + fakeData.number().digits(6));
        emailToCheck = fakeData.funnyName().name().replace(" ","").toLowerCase()+"@gmail.com";
        usersPage.email.sendKeys(emailToCheck);
    }

    @When("I click the Close link")
    public void i_click_the_Close_link() {
        usersPage.close.click();
    }

    @Then("the users table should not contain user with that email")
    public void the_users_table_should_not_contain_user_with_that_email() {
        BrowserUtils.wait(2);
        usersPage.search.sendKeys(emailToCheck);
        BrowserUtils.wait(2);
        List<WebElement> emailList = usersPage.allEmails;
        Assert.assertEquals(emailList.size(),0);
    }

    @When("I save new user information with random email")
    public void i_save_new_user_information_with_random_email() {
        fullNameToCheck = fakeData.name().fullName();
        usersPage.fullName.sendKeys(fullNameToCheck);
        usersPage.password.sendKeys(fakeData.name().firstName().substring(0,2).toUpperCase() + fakeData.number().digits(6));
        emailToCheck = fakeData.funnyName().name().replace(" ","").toLowerCase()+"@gmail.com";
        usersPage.email.sendKeys(emailToCheck);
        groupToCheck = usersPage.getUserGroup().getFirstSelectedOption().getText();
        statusToCheck = usersPage.getStatus().getFirstSelectedOption().getText();
        BrowserUtils.wait(2);
        usersPage.saveChanges.click();
    }

    @When("the users table must contain the correct user information")
    public void the_users_table_must_contain_the_correct_user_information() {
        BrowserUtils.wait(2);
        usersPage.search.sendKeys(fullNameToCheck);
        BrowserUtils.wait(2);
        String fullNameInTable = usersPage.allFullNames.get(0).getText();
        String emailInTable = usersPage.allEmails.get(0).getText();
        String groupInTable = usersPage.groupList.get(0).getText();
        String statusInTable = usersPage.statusList.get(0).getText();
        Assert.assertEquals(fullNameInTable,fullNameToCheck);
        Assert.assertEquals(emailInTable,emailToCheck);
        Assert.assertEquals(groupInTable, groupToCheck);
        Assert.assertEquals(statusInTable, statusToCheck);
    }
}
