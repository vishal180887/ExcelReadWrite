package LOGINTS;

import PO.FreeCrmHomePO;
import base.crm.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTC extends BaseTest {


    public WebDriver driver;
/*

    public LoginTC(WebDriver driver){
     this.driver=driver;
*/




    FreeCrmHomePO freeCrmHomePO;

    @Test
    public void firstTest(){
        freeCrmHomePO=new FreeCrmHomePO(driver);
        test= extent.createTest("Verify First Test");
        //Assert.assertEquals(driver.getTitle(), "Free CRM #1 cloud software for any business large or small");
        driver.findElement(freeCrmHomePO.signUp).click();
        test.log(Status.PASS, "Verified First Test Successfully");

    }

    //@Test
    public void secondtTest(){

        test= extent.createTest("Verify second Test");
        Assert.assertEquals("A", "B");
        test.log(Status.PASS, "Verified second Test Successfully");
    }
}
