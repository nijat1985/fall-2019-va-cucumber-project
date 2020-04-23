package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersTableStepDefs {
    UsersPage usersPage = new UsersPage();
    @When("I search for {string}")
    public void i_search_for(String searchString) {
        usersPage.search.sendKeys(searchString);
        BrowserUtils.wait(2);

    }

    @Then("table should contain rows with {string}")
    public void table_should_contain_rows_with(String expectedString) {
        int size = usersPage.allUserIds.size();
        for (int i = 0; i < size; i++) {
            String id = usersPage.allUserIds.get(i).getText().toLowerCase();
            String name = usersPage.allFullNames.get(i).getText().toLowerCase();
            String email = usersPage.allEmails.get(i).getText().toLowerCase();

            System.out.println("ROW: " + (i+1));
            System.out.println(id + "\t" + name + "\t" + email);

            boolean found = id.contains(expectedString) ||
                            name.contains(expectedString) ||
                            email.contains(expectedString);
            assertTrue(found);
        }
    }

    @Then("table should have following column names:")
    public void table_should_have_following_column_names(List<String> expectedColumnNames) {
       assertEquals(expectedColumnNames,BrowserUtils.getElementsText(usersPage.columnNames));
    }

    @Then("Each user id should be unique")
    public void each_user_id_should_be_unique() {
        usersPage.getShowRecords().selectByVisibleText("500");
        BrowserUtils.wait(2);

        List<String> list = BrowserUtils.getElementsText(usersPage.allUserIds);
        System.out.println("list = " + list);

        Set<String> set = new HashSet<>(list);
        System.out.println("set = " + set);

        assertEquals(list.size(),set.size());

    }


}
