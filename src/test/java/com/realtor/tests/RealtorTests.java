package com.realtor.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.realtor.pages.DetailsPage;
import com.realtor.pages.HomePage;
import com.realtor.pages.SearchResultsPage;

import junit.framework.Assert;

public class RealtorTests {

	WebDriver driver;

	/*
	 * This method runs once before all the tests in the suite. The driver path
	 * and the url are taken as parameters from the xml. To run it from another
	 * system, the driverPath parameter has to be changed in the testng.xml
	 * file.
	 */
	@BeforeSuite
	@Parameters({ "driverPath", "url" })
	public void setUp(String driverPath, String url) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	/*
	 * This test first searches for houses in a place. Checks if the search
	 * results are greater than zero and fails the test if it is zero. It then
	 * clicks on houses at various indices and compares the values with the
	 * values on the details page. The test fails if any value does not match.
	 */
	@Test(dataProvider = "All_Data")
	public void searchAndCompareFlow(String searchText, String indices) {
		HomePage search = new HomePage();
		SearchResultsPage result = new SearchResultsPage();
		DetailsPage details = new DetailsPage();
		try {
			search.enterSearchText(searchText, driver);
			search.clickOnSearch(driver);
			String searchResultText = result.getNoOfSearchResults(driver);
			String[] arr = searchResultText.split(" ");
			int noOfResults = 0;
			if (arr.length == 2) {
				noOfResults = Integer.parseInt(arr[0].trim().replaceAll(",", ""));
			}
			if (!(noOfResults > 0)) {
				Assert.fail("Number of results for the search query " + searchText + " is not greater than zero");
			}
			String[] indexArr = indices.split(",");
			for (int i = 0; i < indexArr.length; i++) {
				int index = Integer.parseInt(indexArr[i]);
				String priceOnSearchString = result.getPriceOfResultOnSearch(driver, index);
				int priceOnSearchInt = Integer.parseInt(priceOnSearchString.replaceAll("[\\$ | ,]", "").trim());
				String addressOnSearch = result.getAddressOnSearch(driver, index).trim();
				result.clickOnSearchResult(driver, index);
				String priceOnDetailsString = details.getDetailsPrice(driver);
				int priceOnDetailsInt = Integer.parseInt(priceOnDetailsString.replaceAll("[\\$ | ,]", "").trim());
				String[] addressOnDetails = details.getDetailsAddress(driver).trim().split(",");
				details.goToSearchResultsPage(driver);
				if (!(priceOnSearchInt == priceOnDetailsInt && addressOnSearch.equals(addressOnDetails[0]))) {
					Assert.fail(
							"The values on the search results page do not match with the values on the details page for the item at index "
									+ index + ".");
				}
			}
		} catch (Exception e) {
			Assert.fail("Falied due to " + e.getMessage());
		}

	}

	/*
	 * This method is for building the dataset. The place to be searched for and
	 * the indices of items to be clicked on and compared with the details page
	 * is passed in to the test. Example datataset: { { "MorganTown, WV","2,4,5"
	 * }, {"Fremont, CA", "1"} } The indices are supposed to be given as comma
	 * separated values without any spaces This data can also be given as an
	 * input from the excel sheet where one column will have the place to be
	 * searched for and another column will have the indices corresponding to
	 * each place.
	 */
	@DataProvider(name = "All_Data")
	public static Object[][] dataProvider() {
		return new Object[][] { { "MorganTown, WV", "2" } };
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
