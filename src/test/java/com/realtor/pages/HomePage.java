package com.realtor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	public void enterSearchText(String searchText, WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement searchBox;
		searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchBox")));
		searchBox.sendKeys(searchText);
	}
	
	public void clickOnSearch(WebDriver driver){
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"homepage-header\"]/div[2]/div/div/div[2]/span/button[2]"));
		searchButton.click();
	}
}
