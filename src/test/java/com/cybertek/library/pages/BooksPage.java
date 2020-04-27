package com.cybertek.library.pages;

import com.cybertek.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BooksPage extends PageBase{
    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(css = "[href='tpl/add-book.html']")
    public WebElement addBook;

    @FindBy(name = "name")
    public WebElement bookName;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(xpath = "//input[@name='author']")
    public WebElement author;

    public WebElement editBook(String book) {
        String xpath = "//td[3][.='"+book+"']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}
