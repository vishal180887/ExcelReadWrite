package PO;

import base.crm.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FreeCrmHomePO extends BasePage {

    private WebDriver driver;


    public FreeCrmHomePO(WebDriver driver){
        super(driver);

    }

    public By signUp=By.xpath("//span[text()=' sign up']");


}
