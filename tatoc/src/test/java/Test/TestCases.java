package Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie;

public class TestCases {

	public static specReader y3 = new specReader();

	WebDriver driver;

	@Test(priority = 1)
	void driver() {
		String driverPath;

		driverPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : Browser successfully launched\n", true);
		
		Reporter.log("*****************************************", true);


	}

	@Test(priority = 2)
	void choose() throws IOException, InterruptedException {
		driver.get("http://10.0.1.86/tatoc");
		Thread.sleep(2000);
		// driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;
		// System.out.println("Hello");
		WebElement element = specReader.getElement(driver, "bcourse");
		// System.out.println("bfdbfg");
		Assert.assertEquals("Welcome - T.A.T.O.C",driver.getTitle());	    	

		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : Course Selected\n", true);
		
		Reporter.log("*****************************************", true);

		element.click();
    	

	}

	@Test(priority = 3)
	void findGreen() throws IOException {
		WebElement element = specReader.getElement(driver, "greenbox");
		Assert.assertEquals("Grid Gate - Basic Course - T.A.T.O.C",driver.getTitle());	    	

		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : The required GreenBox Selected\n", true);
		
		Reporter.log("*****************************************", true);

		element.click();
    	

	}

	@Test(priority = 4)
	void findMatch() throws IOException, InterruptedException {
		String s = "", s1 = "";
		while (true) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");

			WebElement element = specReader.getElement(driver, "matchbox");
			s = element.getAttribute("class");
			// System.out.println("Ist click "+element.getAttribute("class"));
			driver.switchTo().frame("child");
			WebElement element1 = specReader.getElement(driver, "matchbox");
			// System.out.println("IInd click"+element1.getAttribute("class"));
			s1 = element1.getAttribute("class");
			if (s.equalsIgnoreCase(s1)) {
				break;
			}
			driver.switchTo().defaultContent();

			driver.switchTo().frame("main");
			WebElement element2 = specReader.getElement(driver, "repaint");
			element2.click();

		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		WebElement element3 = specReader.getElement(driver, "proceed");
		Assert.assertEquals("Frame Dungeon - Basic Course - T.A.T.O.C",driver.getTitle());	    	

		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : Both the Boxes have same color\n", true);
		
		Reporter.log("*****************************************", true);
		element3.click();
    	

	}

	@Test(priority = 5)
	void dragAndDrop() throws IOException {

		WebElement Atom = driver.findElement(By.xpath(".//*[@id='dragbox']"));
		driver.switchTo().defaultContent();
		WebElement Event = driver.findElement(By.xpath(".//*[@id='dropbox']"));

		// Create Actions object passing in a WebDriver object
		Actions builder = new Actions(driver);

		// Chain some calls together and call build
		Action dragAndDrop = builder.clickAndHold(Atom).moveToElement(Event).release(Event).build();

		// Perform the actions
		dragAndDrop.perform();
		WebElement element3 = specReader.getElement(driver, "proceed");

		Assert.assertEquals("Drag - Basic Course - T.A.T.O.C",driver.getTitle());	    	

		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : The Drag And Drop Successfully completed\n", true);
		
		Reporter.log("*****************************************", true);

		element3.click();
		
	}

	@Test(priority = 6)
	void enterData() throws IOException, InterruptedException {
		String parentWindow = driver.getWindowHandle();
		WebElement element4 = specReader.getElement(driver, "launch");

		element4.click();

		// driver.switchTo().window(parentWindow);

		Set<String> handles = driver.getWindowHandles();// To handle multiple
														// windows
		String firstWinHandle = driver.getWindowHandle();
		handles.remove(firstWinHandle);
		String winHandle = handles.iterator().next();
		if (winHandle != firstWinHandle) {
			String secondWinHandle = winHandle;

			driver.switchTo().window(secondWinHandle);
			Reporter.log("*****************************************", true);

			Reporter.log("\nThe User is on the PopUp Window\n", true);
			
			Reporter.log("*****************************************", true);

			WebElement element5 = specReader.getElement(driver, "data");

			element5.sendKeys("User_1");
			WebElement element6 = specReader.getElement(driver, "submit");

			element6.click();
		}
		
		driver.switchTo().window(parentWindow);
		Reporter.log("*****************************************", true);

		Reporter.log("\nThe User is on the Parent Window\n", true);
		
		Reporter.log("*****************************************", true);

		WebElement element7 = specReader.getElement(driver, "proceed");

		Assert.assertEquals("Windows - Basic Course - T.A.T.O.C",driver.getTitle());	    	

		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : The User successfully submitted the data\n", true);
		
		Reporter.log("*****************************************", true);
		element7.click();
		


	}

	@Test(priority = 7)
	void generateToken() throws IOException {
		WebElement element8 = specReader.getElement(driver, "genToken");

		element8.click();
		
		WebElement element9 = specReader.getElement(driver, "token");

		String s = element9.getText();
		Reporter.log("*****************************************", true);

		Reporter.log("\nToken Generated and Stored Successfully\n", true);
		
		Reporter.log("*****************************************", true);

		//System.out.println("Token is:" + s);
		String token = s.split(": ")[1];
		//System.out.println(token);
		Cookie cookie = new Cookie("Token", token);
		driver.manage().addCookie(cookie);
		Reporter.log("*****************************************", true);

		Reporter.log("\nThe Cookie is Successfully created and Added\n", true);
		
		Reporter.log("*****************************************", true);

		WebElement element10 = specReader.getElement(driver, "proceed");
		element10.click();
		Reporter.log("*****************************************", true);

		Reporter.log("\n[Test passed] : The User successfully generated the token and created the cookie \n", true);
		
		Reporter.log("*****************************************", true);

		driver.close();
		
		System.out.println("The Basic Obstacle Course completed.......");
	}

}
