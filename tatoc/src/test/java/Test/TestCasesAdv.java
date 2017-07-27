package Test;

import java.io.File;
import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;



	public class TestCasesAdv {

		public static specReader y3 = new specReader();

		WebDriver driver;
		
		String st="";
		
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
		
		//@Test(priority = 2)
		void choose() throws IOException, InterruptedException {
			driver.get("http://10.0.1.86/tatoc");
			//Thread.sleep(2000);
			// driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;
			// System.out.println("Hello");
			WebElement element = specReader.getElement(driver, "acourse");
			// System.out.println("bfdbfg");
			//System.out.println("Title: "+driver.getTitle());
	    	Assert.assertEquals("Welcome - T.A.T.O.C",driver.getTitle());	    	
	    	Reporter.log("*****************************************", true);

			Reporter.log("\n[Test passed] : Course Selected\n", true);
			
			Reporter.log("*****************************************", true);

			element.click();
			
			 
			

		}

		//@Test(priority = 3)
		void goto_menu() throws IOException, InterruptedException {
			
			WebElement element = specReader.getElement(driver, "menu2");
			Actions action = new Actions(driver);
	    	action.moveToElement(element).perform();
	    	WebElement element1 = specReader.getElement(driver, "gotoNext");

	    	Assert.assertEquals("Hover Menu - Advanced Course - T.A.T.O.C",driver.getTitle());	    	
	    	Reporter.log("*****************************************", true);

			Reporter.log("\n[Test passed] : Successfully Hovered on Menu 2 and Clicked on its Go-To\n", true);
			
			Reporter.log("*****************************************", true);

		
	    	element1.click();
	    	
			
			
			
		}
		
		
		
		
		
		
	//	@Test(priority = 4)
		void query_gate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException
		{
			
			  
			String symbol = specReader.getElement(driver, "symbol").getText();
			
			//System.out.println("Symbol is: "+symbol);
			
			String dbUrl = "jdbc:mysql://10.0.1.86:3306/tatoc";					

				//Database Username		
				String username = "tatocuser";	
              
				//Database Password		
				String password = "tatoc01";	
				
				
				
				//Query to Execute		
				String query = "select *  from credentials";	
                
         	    //Load mysql jdbc driver		
           	    Class.forName("com.mysql.jdbc.Driver");			
           
           		//Create Connection to DB		
           	 Connection con = null;

            	 con = DriverManager.getConnection(dbUrl,username,password);
         // System.out.println(con);
          		//Create Statement Object		
        	   Statement stmt = con.createStatement();					
       
       			// Execute the SQL Query. Store results in ResultSet		
         		ResultSet rs= stmt.executeQuery(query);							
         		// While Loop to iterate through all data and print results	
         		String name="";
         		String passKey="";
				while (rs.next()){
					//System.out.println(rs.getString(2));
					if(symbol.startsWith(rs.getString(2)))
			        		{ name = rs.getString(2);								        
                              passKey = rs.getString(3);					                               
                            // System. out.println(name+"  "+passKey);		
                     break;
			        		}	}
				rs.close();
      			 // closing DB Connection		
      			con.close();
      			
      			Assert.assertEquals("Query Gate - Advanced Course - T.A.T.O.C",driver.getTitle());
      			Reporter.log("*****************************************", true);

    			Reporter.log("\n[Test passed] : Successfully created the JDBC and Entered the details\n", true);
    			
    			Reporter.log("*****************************************", true);

      			
    			WebElement element = specReader.getElement(driver, "name");
    			element.sendKeys(name);
    			WebElement element1 = specReader.getElement(driver, "passkey");
    			element1.sendKeys(passKey);
    			WebElement element2 = specReader.getElement(driver, "proceed");
    			element2.click();
    						
		}
		
		//@Test(priority = 5)
		void videoPlaying()
		{
			Assert.assertEquals("Video Player - Advanced Course - T.A.T.O.C",driver.getTitle());
  			Reporter.log("*****************************************", true);

			Reporter.log("\n[Test passed] : The User is on the Ooyala Video Player module of the T.A.T.O.C\n", true);
			
			Reporter.log("*****************************************", true);

		}
		
		
		@Test(priority = 6)
		void RestService_obtainSessionID() throws IOException
		{
			
			driver.get("http://10.0.1.86/tatoc/advanced/rest/#");
			WebElement element = specReader.getElement(driver, "session");
			String s =element.getText().toString();
			//System.out.println(s);
			 int i = s.lastIndexOf( ' ' );
			st = s.substring(i+1);
			//System.out.println(st);
			Assert.assertEquals("Restful - Advanced Course - T.A.T.O.C",driver.getTitle());
  			Reporter.log("*****************************************", true);

			Reporter.log("\n[Test passed] : The User is on the Restful module of the T.A.T.O.C\n", true);
			
			Reporter.log("*****************************************", true);
			

		}
		
		@Test(priority = 7)
		void RestService_generateToken() throws IOException
		{
			
			JsonPath response= RestAssured.given().get("http://10.0.1.86/tatoc/advanced/rest/service/token/"+st).jsonPath();
			String res = response.getString("token");
			//JSONObject ob= new JSONObject();
			//ob.put("id",st);
			/*
			 * LinkedHashMap<String,String> lhm = new LinkedHashMap<String,String>();
			lhm.put("id", st);
			lhm.put("signature", res);
			lhm.put("allow_access", "1");
			JSONObject ob= new JSONObject();
			ob.putAll( lhm );
*/
			RestAssured.given().parameters("id", st,"signature",res,"allow_access","1").post("http://10.0.1.86/tatoc/advanced/rest/service/register");
			WebElement element = specReader.getElement(driver, "proceedfinal");
			Assert.assertEquals("Restful - Advanced Course - T.A.T.O.C",driver.getTitle());
  			Reporter.log("*****************************************", true);

			Reporter.log("\n[Test passed] : The User successfully registered\n", true);
			
			Reporter.log("*****************************************", true);
			element.click();
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		@Test(priority = 8)
		void RestService_FileHandling() throws IOException, InterruptedException
		{
			String s="",st[] = new String [2];
			WebElement element = specReader.getElement(driver, "download");
			element.click();
			Assert.assertEquals("File Handle - Advanced Course - T.A.T.O.C",driver.getTitle());
  			Reporter.log("*****************************************", true);

			Reporter.log("\n The User is on the File Handling Page of T.A.T.O.C\n", true);
			
			Reporter.log("*****************************************", true);
			
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
Thread.sleep(4000);
			String filePath = "C:\\Users\\avijitkumar\\Downloads\\file_handle_test.dat";
			File file = new File(filePath);
			Scanner scnr = new Scanner(file);
			while(scnr.hasNextLine())
			{
			   String line = scnr.nextLine();
			   st = line.split(":");
			   if(st[0].equalsIgnoreCase("Signature"))
			   {
				   s= st[1].trim();
			   //System.out.println(s);

			   break;
			}
			}
			WebElement element1 = specReader.getElement(driver, "sign");
			element1.sendKeys(s);
			WebElement element2 = specReader.getElement(driver, "proceedend");
			element2.click();
			Assert.assertEquals("End - T.A.T.O.C",driver.getTitle());
  			Reporter.log("*****************************************", true);

			Reporter.log("\n The User is on the File Handling Page of T.A.T.O.C\n", true);
			
			Reporter.log("*****************************************", true);
			Thread.sleep(4000);

			 driver.close();

		}
		
		
		
		
		
		
	}

