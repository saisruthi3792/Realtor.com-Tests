package com.realtor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	
	public String getNoOfSearchResults(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement searchResult;
		searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-result-count")));
		return searchResult.getText();
	}
	
	public String getPriceOfResultOnSearch(WebDriver driver, int index){
		WebElement indexResultPrice = driver.findElement(By.xpath("(//span[@class='data-price'])["+index+"]"));
		return indexResultPrice.getText();
	}
	
	public String getAddressOnSearch(WebDriver driver, int index){
		WebElement indexResultAddress = driver.findElement(By.xpath("(//span[@class='listing-street-address'])["+index+"]"));
		return indexResultAddress.getText();
	}
	
	public void clickOnSearchResult(WebDriver driver, int index){
		driver.findElement(By.xpath("(//div[@class='address ellipsis'])["+index +"]")).click();
	}
}
