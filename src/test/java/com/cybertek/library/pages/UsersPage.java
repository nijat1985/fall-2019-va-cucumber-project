package com.cybertek.library.pages;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UsersPage extends PageBase {
    @FindBy(name = "tbl_users_length")
    public WebElement showRecords;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm']")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='start_date']")
    public WebElement start_date;

    @FindBy(xpath = "//input[@name='end_date']")
    public WebElement end_date;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullName;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChanges;

    @FindBy(xpath = "//button[@type='cancel']")
    public WebElement close;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public List<WebElement> allUserIds;

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    public List<WebElement> allFullNames;

    @FindBy(xpath = "//table/tbody/tr/td[4]")
    public List<WebElement> allEmails;

    @FindBy(xpath = "//table/tbody/tr/td[5]")
    public List<WebElement> groupList;

    @FindBy(xpath = "//table/tbody/tr/td[6]")
    public List<WebElement> statusList;

    @FindBy(id = "user_group_id")
    public WebElement userGroup;

    @FindBy(id = "status")
    public WebElement status;

    @FindBy(tagName = "th")
    public List<WebElement> columnNames;

    @FindBy(id = "user_groups")
    public WebElement user_groups_filter;

    public Select getUserGroupFilter(){
        return new Select(user_groups_filter);
    }

    public Select getStatus(){
        return new Select(status);
    }

    public Select getUserGroup(){
        return new Select(userGroup);
    }

    public Select getShowRecords(){
        return new Select(showRecords);
    }



}
