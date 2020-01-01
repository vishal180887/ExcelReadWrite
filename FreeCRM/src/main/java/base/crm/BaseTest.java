package base.crm;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;


    /*public BaseTest(WebDriver driver){
        this.driver=driver;
    }*/


    public static ExtentHtmlReporter htmlReporter;
    public ExtentTest test;
    public static ExtentReports extent;

    @Parameters({ "browserName", "driverPath" })
    @BeforeClass
    public void beforeClass( @Optional("chrome") String browserName,@Optional("C:\\Drivers\\chromedriver_78\\chromedriver.exe") String driverPath) throws Exception  {

        loadDrivers(browserName, driverPath);

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Report\\report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Developer", "Vishal Pandya");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60l, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(50l, TimeUnit.SECONDS);

    }

    @Parameters({"url"})
    @BeforeMethod
    public void beforeMethod(@Optional("https://www.freecrm.com") String url) throws Exception {
        driver.get(url);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws Exception{
        try
        {
            if(result.getStatus()==ITestResult.FAILURE)
            {
                String screenShotPath= capture(driver, "screenShotName");
                test.log(Status.FAIL, result.getThrowable());
                test.log(Status.FAIL, "Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

            }
            else if(result.getStatus()== ITestResult.SUCCESS)
            {
                test.log(Status.PASS, this.getClass().getSimpleName() + " Test Case Success");
            }
            else if(result.getStatus()==ITestResult.SKIP) {
                test.log(Status.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
            }
            extent.flush();
            driver.quit();

        }
        catch(Throwable t)
        {
            test.log(Status.ERROR,t.fillInStackTrace());
        }
    }


    @AfterClass
    public void afterClass() {
        // reporter.endReport();
        extent.flush();
        extent.close();

    }

 private void loadDrivers(String browserName, String driverPath){
     if(browserName.equalsIgnoreCase("chrome")){
         System.setProperty("webdriver.chrome.driver",driverPath);
         driver=new ChromeDriver();
     }else if(browserName.equalsIgnoreCase("firefox")){
         System.setProperty("webdriver.gecko.driver", driverPath);
         driver= new FirefoxDriver();
     }

 }

 private  String capture(WebDriver driver,String screenShotName) throws IOException
 {
     TakesScreenshot ts = (TakesScreenshot)driver;
     File source = ts.getScreenshotAs(OutputType.FILE);
     String dest = System.getProperty("user.dir") +"./Report/Screenshots/"+screenShotName+".png";
     File destination = new File(dest);
     FileUtils.copyFile(source, destination);

     return dest;
 }

}
