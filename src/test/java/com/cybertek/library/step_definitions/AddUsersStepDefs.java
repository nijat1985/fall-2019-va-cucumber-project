package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddUsersStepDefs {
    UsersPage usersPage = new UsersPage();

    @Given("I click on Add Users")
    public void i_click_on_Add_Users() {
        usersPage.addUsers.click();
    }


    @Then("dialog fields must have matching placeholder")
    public void dialog_fields_must_have_matching_placeholder(Map<String,String> fields) {
        for (String key: fields.keySet()){
            System.out.println("key = " + key);
            System.out.println("value = " + fields.get(key));
            System.out.println();
        }

        String expectedFullname = fields.get("fullname");
        String actualFullName = usersPage.fullName.getAttribute("placeholder");
        assertEquals("Full Name placeholder value did not match", expectedFullname,actualFullName);

        String expectedEmail = fields.get("email");
        String actualEmail = usersPage.email.getAttribute("placeholder");
        assertEquals("Email placeholder value did not match",expectedEmail,actualEmail);

        String expectedPassword = fields.get("password");
        String actualPassword = usersPage.password.getAttribute("placeholder");
        assertEquals("Password placeholder value did not match",expectedPassword,actualPassword);

        String actualAddress = usersPage.address.getAttribute("placeholder");
        assertEquals("Address placeholder must be empty","",actualAddress);
        assertTrue("Address placeholder must be empty",usersPage.address.getAttribute("placeholder").isEmpty());
    }

    @Then("table should contain this data")
    public void table_should_contain_this_data(Map<String,String> user) {
        System.out.println(user.entrySet());

        String id = user.get("User ID");
        String name = user.get("Full Name");
        String email = user.get("Email");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("email = " + email);

        //get all rows. verify that at least one of the rows contain all of the user info
        List<WebElement> allRows = usersPage.allRows;
        List<String> allRowsTxt = BrowserUtils.getElementsText(allRows);
        boolean found = false;
        for(String row : allRowsTxt){
            System.out.println("row = " + row);
            found = row.contains(id) && row.contains(name) && row.contains(email);
            if (found){
                break;
            }
        }
        assertTrue(user + "was not found",found);





    }

}
