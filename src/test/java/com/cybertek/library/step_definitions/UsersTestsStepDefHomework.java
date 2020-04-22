package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.DashboardPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.pages.PageBase;
import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.cybertek.library.utilities.ConfigurationReader;
import com.cybertek.library.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class UsersTestsStepDefHomework {
    UsersPage usersPage = new UsersPage();
    Faker fakeData = new Faker();
    String emailToCheck;
    String groupToCheck;
    String statusToCheck;
    String fullNameToCheck;
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    Random random = new Random();

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


    @Given("I access {string} page as a {string}")
    public void i_access_page_as_a(String page, String user) {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
        switch (user){
            case "librarian":
                loginPage.login(ConfigurationReader.getProperty("librarian_email"),
                        ConfigurationReader.getProperty("librarian_password"));
                break;
            case "student":
                loginPage.login(ConfigurationReader.getProperty("student_email"),
                        ConfigurationReader.getProperty("student_password"));
                break;
        }

        dashboardPage.users.click();

    }

    @Then("User group default value should be {string}")
    public void user_group_default_value_should_be(String userGroup) {
        Assert.assertEquals(usersPage.getUserGroupFilter().getFirstSelectedOption().getText().toLowerCase(),userGroup.toLowerCase());
    }

    @Then("user group should have following options:")
    public void user_group_should_have_following_options(List<String> userGroupFilter) {
        Assert.assertEquals(BrowserUtils.getElementsText(usersPage.getUserGroupFilter().getOptions()),userGroupFilter);
    }

    @When("I select User group {string}")
    public void i_select_User_group(String userGroupFilter) {
        switch (userGroupFilter.toLowerCase()){
            case "all":
                usersPage.getUserGroupFilter().selectByIndex(0);
                break;
            case "librarian":
                usersPage.getUserGroupFilter().selectByIndex(1);
                break;
            case "student":
                usersPage.getUserGroupFilter().selectByIndex(2);
                break;
        }
    }

    @Then("Groups columns in user table should only contain {string}")
    public void groups_columns_in_user_table_should_only_contain(String userGroupFilter) {
        int size = usersPage.getShowRecords().getOptions().size();
        BrowserUtils.wait(3);
        usersPage.getShowRecords().selectByIndex(size - 1);
        switch (userGroupFilter.toLowerCase()){
            case "librarian":
                BrowserUtils.wait(2);
                List<String> groupTextList = BrowserUtils.getElementsText(usersPage.groupList);
                for (int i = 0; i < groupTextList.size(); i++) {
                    Assert.assertEquals(groupTextList.get(i).toLowerCase(),userGroupFilter.toLowerCase());
                }
                break;
            case "student":
                BrowserUtils.wait(2);
                groupTextList = BrowserUtils.getElementsText(usersPage.groupList);
                for (int i = 0; i < groupTextList.size(); i++) {
                    Assert.assertEquals(groupTextList.get(i).toLowerCase(),userGroupFilter.toLowerCase());
                }
                break;
        }
    }


    @When("I search for any valid email")
    public void i_search_for_any_valid_email() {
        List<String> emailList = BrowserUtils.getElementsText(usersPage.allEmails);
        String randomEmail = emailList.get(random.nextInt(emailList.size()));
        usersPage.search.sendKeys(randomEmail);
    }

    @When("I search for any invalid email")
    public void i_search_for_any_invalid_email() {
        String invalidEmail = fakeData.name().firstName() + fakeData.number().digits(3) + "&" + fakeData.funnyName().name();
        usersPage.search.sendKeys(invalidEmail);
    }

    @Then("the users table must display message {string}")
    public void the_users_table_must_display_message(String message) {
        Assert.assertEquals(usersPage.emptyTableMessage.getText(),message);
    }

    @Then("users table should be sorted by {string} in {string} order")
    public void users_table_should_be_sorted_by_in_order(String columnName, String order) {
        switch (columnName.toLowerCase()){
            case "user id":
                switch (order.toLowerCase()){
                    case "ascending":
                        BrowserUtils.wait(2);
                        Assert.assertTrue(BrowserUtils.isSortedAscendingOrder(usersPage.allUserIds));
                        break;
                    case "descending":
                        BrowserUtils.wait(2);
                        Assert.assertTrue(BrowserUtils.isSortedDescendingOrder(usersPage.allUserIds));
                        break;
                }
                break;

            case "email":
                switch (order.toLowerCase()){
                    case "ascending":
                        BrowserUtils.wait(2);
                        Assert.assertTrue(BrowserUtils.isSortedAscendingOrder(usersPage.allEmails));
                        break;
                    case "descending":
                        BrowserUtils.wait(2);
                        Assert.assertTrue(BrowserUtils.isSortedDescendingOrder(usersPage.allEmails));
                        break;
                }
                break;
        }
    }

    @When("I click on the {string} column")
    public void i_click_on_the_column(String columnName) {
        switch (columnName.toLowerCase()){
            case "user id":
                BrowserUtils.wait(2);
                usersPage.userIdColumn.click();
                break;
            case "email":
                BrowserUtils.wait(2);
                usersPage.emailColumn.click();
                break;
        }
    }



}
