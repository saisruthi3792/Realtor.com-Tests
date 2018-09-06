package com.realtor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailsPage {
  
	public String getDetailsPrice(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement priceOnDetails;
		priceOnDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@itemprop='price']")));
		return priceOnDetails.getText();
	}
	
	public String getDetailsAddress(WebDriver driver){
		return driver.findElement(By.xpath("//h2[@class='address']")).getText();
	}
	
	public void goToSearchResultsPage(WebDriver driver){
		driver.findElement(By.className("full-screen-qv-back")).click();
	}
}
