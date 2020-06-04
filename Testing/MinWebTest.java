import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class MinWebTest {

	@Test public void t0() {
		// execute the test <x = 0, y = 0, z = 0, submitButton = click> and check the output message is correct
	System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
	WebDriver wd = new FirefoxDriver(); // launch the browser
	
	// edit the next line to enter the location of "min.html" on your file system
	wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
	WebElement we = wd.findElement(By.id("x"));
	we.sendKeys("0"); // enter a representative for x
	we = wd.findElement(By.id("y"));
	we.sendKeys("0"); // enter a representative for y
	we = wd.findElement(By.id("z"));
	we.sendKeys("0"); // enter a representative for z
	we = wd.findElement(By.id("computeButton"));
	we.click(); //click the button
	WebElement result = wd.findElement(By.id("result"));
	String output = result.getText(); // read the output text
	assertEquals("min(0, 0, 0) = 0", output);
	wd.quit(); // close the browser window
		}
	
	@Test public void t1() {
		System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
		WebDriver wd = new FirefoxDriver(); // launch the browser
		
		// edit the next line to enter the location of "min.html" on your file system
		wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
		WebElement we = wd.findElement(By.id("x"));
		we.sendKeys("0"); // enter a representative for x
		we = wd.findElement(By.id("y"));
		we.sendKeys("0"); // enter a representative for y
		we = wd.findElement(By.id("z"));
		we.sendKeys("1"); // enter a representative for z
		we = wd.findElement(By.id("computeButton"));
		we.click(); //click the button
		WebElement result = wd.findElement(By.id("result"));
		String output = result.getText(); // read the output text
		assertEquals("min(0, 0, 1) = 0", output);
		wd.quit(); // close the browser window
	}
	
	@Test public void t2() {
		System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
		WebDriver wd = new FirefoxDriver(); // launch the browser
		
		// edit the next line to enter the location of "min.html" on your file system
		wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
		WebElement we = wd.findElement(By.id("x"));
		we.sendKeys("-1"); // enter a representative for x
		we = wd.findElement(By.id("y"));
		we.sendKeys("1"); // enter a representative for y
		we = wd.findElement(By.id("z"));
		we.sendKeys("1"); // enter a representative for z
		//we = wd.findElement(By.id("computeButton"));
		//we.click(); //click the button
		WebElement result = wd.findElement(By.id("result"));
		String output = result.getText(); // read the output text
		assertEquals("", output);
		wd.quit(); // close the browser window
	}
		
	@Test public void t3() {
		System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
		WebDriver wd = new FirefoxDriver(); // launch the browser
			
		// edit the next line to enter the location of "min.html" on your file system
		wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
		WebElement we = wd.findElement(By.id("x"));
		we.sendKeys("1"); // enter a representative for x
		we = wd.findElement(By.id("y"));
		we.sendKeys("1"); // enter a representative for y
		we = wd.findElement(By.id("z"));
		we.sendKeys("-1"); // enter a representative for z
		we = wd.findElement(By.id("computeButton"));
		we.click(); //click the button
		WebElement result = wd.findElement(By.id("result"));
		String output = result.getText(); // read the output text
		assertEquals("min(1, 1, -1) = -1", output);
		wd.quit(); // close the browser window
	}
			
	@Test public void t4() {
		System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
		WebDriver wd = new FirefoxDriver(); // launch the browser
			
		// edit the next line to enter the location of "min.html" on your file system
		wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
		WebElement we = wd.findElement(By.id("x"));
		we.sendKeys("1"); // enter a representative for x
		we = wd.findElement(By.id("y"));
		we.sendKeys("0"); // enter a representative for y
		we = wd.findElement(By.id("z"));
		we.sendKeys("-1"); // enter a representative for z
		//we = wd.findElement(By.id("computeButton"));
		//we.click(); //click the button
		WebElement result = wd.findElement(By.id("result"));
		String output = result.getText(); // read the output text
		assertEquals("", output);
		wd.quit(); // close the browser window
	}
			
				
	@Test public void t5() {
		System.setProperty("webdriver.gecko.driver","src/geckodriver.exe");
		WebDriver wd = new FirefoxDriver(); // launch the browser
			
		// edit the next line to enter the location of "min.html" on your file system
		wd.get("file:///c:/Users/Ugly/eclipse-workspace/tutorial%206.2/src/min.html");
		WebElement we = wd.findElement(By.id("x"));
		we.sendKeys("0"); // enter a representative for x
		we = wd.findElement(By.id("y"));
		we.sendKeys("-1"); // enter a representative for y
		we = wd.findElement(By.id("z"));
		we.sendKeys("-1"); // enter a representative for z
		we = wd.findElement(By.id("computeButton"));
		we.click(); //click the button
		WebElement result = wd.findElement(By.id("result"));
		String output = result.getText(); // read the output text
		assertEquals("min(0, -1, -1) = -1", output);
		wd.quit(); // close the browser window
	}
				
		
	

}
