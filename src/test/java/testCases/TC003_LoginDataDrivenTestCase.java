package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
    Positive Test Case : valid credential -> Login success -> test passed -> logout
                         invalid credential -> login unsuccessful -> test passed

    Negative Test Case : valid credential -> Login unsuccessful -> test failed
                         invalid credential -> login success -> test failed -> logout
*/
public class TC003_LoginDataDrivenTestCase extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") //getting Data PRovider from different class
    public void verify_loginDDT(String email, String pwd, String expResult){

        logger.info("*** Starting TC003_LoginDataDrivenTestCase ***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            MyAccountPage myacc = new MyAccountPage(driver);
            boolean targetPage = myacc.isMyAccountPageExist();

            if (expResult.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    myacc.ClickLogout();
                    myacc.ClickLogoutContinue();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }

            }

            if (expResult.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    myacc.ClickLogout();
                    myacc.ClickLogoutContinue();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("*** Finished TC003_LoginDataDrivenTestCase ***");
    }


}
