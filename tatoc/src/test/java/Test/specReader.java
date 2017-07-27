package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;import junit.framework.Assert;



public class specReader 
{
	/*public static void main(String[] args)throws IOException
	{ 
		String a;
	//WebElement a;
		//Scanner sc = new Scanner(System.in);
		
		String name="password";
	//	System.out.println("Enter the Element Name");
		//name= sc.next();
	    a = getElement(name);
	    System.out.println(a);
				}*/
 
	
	//static	String  getElement(String s)
	public static	WebElement  getElement(WebDriver driver, String s)throws IOException
	{
		Boolean value;
		 FileInputStream fis = null;
	        BufferedReader reader = null;
	        String[] result = null;
	        //String wb="";
	        WebElement wb= null;
	        String locatorsPath="";
	        
	       // locatorsPath=System.getProperty("user.dir")+File.separator+"resources"+File.separator+"locators.spec";// ------ for locators.spec of BASIC COURSE
	        locatorsPath=System.getProperty("user.dir")+File.separator+"resources"+File.separator+"Advlocators.spec";
	        try {
	            fis = new FileInputStream(locatorsPath);
	            reader = new BufferedReader(new InputStreamReader(fis));
	          
	            //System.out.println("Reading File line by line using BufferedReader");
	          
	            String line = reader.readLine();
	            while(line != null){
	            	if(line.contains(s))
	            	{
	            	 //result = line.split(" ");
	            	 result = line.split("%");

	            	 break;
	                }
	                line = reader.readLine();

	            } 
	            wb = create_path(driver,result);
	          
	        } catch (FileNotFoundException ex) {
	            //Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	           // Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
	          
	        } finally {
	            try {
	                reader.close();
	                fis.close();
	            } catch (IOException ex) {
	               // Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        return wb;
		
	}
	
	//static String create_path(String a[])
 	static WebElement create_path(WebDriver driver,String a[])
	{
		WebElement element= null;
		//String element="",
		String b;
		b=a[1];
		//System.out.println(	a[2]);
		switch(b)
		{
		case "id" : 
			  element =  driver.findElement(By.id(a[2]));
			  break;
		case "xpath" :
			  element =  driver.findElement(By.xpath(a[2]));
			  break;
		case "css" :
			  element =  driver.findElement(By.cssSelector(a[2]));

			  
		}
		return element;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}