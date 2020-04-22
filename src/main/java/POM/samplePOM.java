package POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class samplePOM {
	static WebDriver driver;
	static JavascriptExecutor js = (JavascriptExecutor) driver;
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	public samplePOM(WebDriver driver){

        this.driver = driver;

    }
	
	public static void  highlightelement(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
	}
	public static void clickAddButton(){

          driver.findElement(By.linkText("Add a new computer")).click();

       }
	public static void  clickFindButton()
	{

     driver.findElement(By.xpath("//*[@id='searchsubmit']")).click();

       }

	
	public static void clearSearchBox()
	{

         driver.findElement(By.xpath("//*[@id='searchbox']")).clear();

       }
	public static void setSearchBox(String setVal)
	{

         driver.findElement(By.xpath("//*[@id='searchbox']")).sendKeys(setVal);

       }
	 
	public static String getFindButton1()
	{

        return    driver.findElement((By.xpath("homePageUserName"))).getText();

       }
	
	
	public static void clickcancel()
	{

      
		driver.findElement(By.xpath("//*[@id=\"main\"]/form[1]/div/a")).click();

       }
	
	
	 public static void clearAll() 
	 {
		 
		 //driver.findElement(By.xpath("//*[@id='name']")).clear();
		 driver.findElement(By.xpath("//*[@id='introduced']")).clear();
		 driver.findElement(By.xpath("//*[@id='discontinued']")).clear();
		 
	 }
	 public static WebElement  setComputerName(){

	//       WebElement compName= driver.findElement(By.xpath("//*[@id='name']")).sendKeys(strcomputerName);
	       WebElement compName= driver.findElement(By.xpath("//*[@id='name']"));
	       return compName;
	 }
	
	 public static WebElement setintroducedDate(){

	    return     driver.findElement(By.xpath("//*[@id='introduced']"));

	 }
	 public static WebElement getintroducedDate(){

		    return     driver.findElement(By.xpath("//*[@id='introduced']"));

		 }
		
	 public static  WebElement  getDiscontinuedDate(){

	      return   driver.findElement(By.xpath("//*[@id='discontinued']"));

	 }
	
	 
	 public static  WebElement  setDiscontinuedDate(){

	      return   driver.findElement(By.xpath("//*[@id='discontinued']"));

	 }
	

	 public static void selectCompanyName(String strselCompanyName) throws InterruptedException{

	     //   driver.findElement(By.xpath("//*[@id='name']")).sendKeys(strselCompanyName);
//	        WebElement select = driver.findElement(By.id("name"));
//	        List<WebElement> options = select.findElements(By.tagName("Male"));
//	        for (WebElement option : options) {
//	        if("Germany".equals(option.getText()))
//	        option.click();
        Select dropdown = new Select(driver.findElement(By.id("company")));
//	       
          
            Thread.sleep(1000);
	     //   dropdown.selectByVisibleText("strselCompanyName");
	        dropdown.selectByIndex(3);
	    
	        }

	//*[@id="pagination"]/ul/li[3]/a 
	 
	 
	 public static void nextButton(){

	        driver.findElement(By.xpath("//*[@id=\"main\"]/form/div/input")).click();

	 }
	 public static void prevButton(){

	        driver.findElement(By.xpath("//*[@id=\"pagination\"]/ul/li[1]/a")).click();

	 }
	 
	 
	 public static void partButton(){

	        driver.findElement(By.xpath("//*[@id=\"pagination\"]/ul/li[2]/a")).click();

	 }
	
	 
	 public static void createComputer(){

	        driver.findElement(By.xpath("//*[@id='main']/form/div/input")).click();

	 }
	
	 public static void cancelAddaComputer(){

	        driver.findElement(By.xpath("//*[@id=\"main\"]/form/div/a")).click();

	 }
	 
	 public static void selectanyComputerType(){

	        driver.findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[7]/td[1]/a")).click();

	 }
	
	 public static void clickDelete(){

	      //  driver.findElement(By.xpath("///*[@id='main']/form[2]/input")).click();
	      //  driver.findElement(By.className("btn danger")).click();
		 driver.findElement(By.xpath("//*[@id='main']/form[2]/input")).click();
	 }
	  
	 public static void selectforDelete(){

	      driver.findElement(By.xpath("//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
	        

	 }
	 
	
	 
	 public static void selectforupdate(){

	      driver.findElement(By.xpath("//*[@id=\"main\"]/form[1]/div/input")).click();
	        
	 }
	 public static String  verifyupdatedelete(){

	    return   driver.findElement(By.xpath("//*[@id='main']/div[1]")).getText();
	 }
	
}
