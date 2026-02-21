package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login(){
        logger.info("*** Starting TC002_LoginTest ***");

        try {
            //HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount");
            hp.clickLogin();
            logger.info("Clicked on Login");

            //Login
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            logger.info("Entered on Login details");
            lp.clickLogin();
            logger.info("Clicked on Login");

            //MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExist();

            Assert.assertEquals(targetPage, true, "Login Failed");
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("*** Finished TC002_LoginTest ***");
    }
}
