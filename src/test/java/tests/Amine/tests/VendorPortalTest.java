package tests.Amine.tests;


import com.Amine.Abstract.AbstractTest;
import org.testng.Assert;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Amine.tests.model.VendorPortalTestData;
import util.Config;
import util.Constants;
import util.JsonUtil;
import vendorportal.DashboradPage;
import vendorportal.LoginPage;



public class VendorPortalTest extends AbstractTest {

    private LoginPage loginpage;
    private DashboradPage dashboradpage;
    private VendorPortalTestData vendorportaltestdata;

    @BeforeMethod
    @Parameters("testDataPath")
    public void setUpObjects(String testDataPath) {

        this.loginpage = new LoginPage(driver);
        this.dashboradpage = new DashboradPage(driver);
        this.vendorportaltestdata = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }


    @Test
    public void loginTest(){

        loginpage.goToUrl(Config.get(Constants.VENDORPORTAL_URL));
        Assert.assertTrue(loginpage.isAt());
        loginpage.login(vendorportaltestdata.username(), vendorportaltestdata.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest(){

        Assert.assertTrue(dashboradpage.isAt());

        Assert.assertEquals(dashboradpage.getMonthlyFieldText(),vendorportaltestdata.MonthlyField());
        Assert.assertEquals(dashboradpage.getAnnualFieldText(),vendorportaltestdata.AnnualField());
        Assert.assertEquals(dashboradpage.getProfitFieldText(),vendorportaltestdata.ProfitField());
        Assert.assertEquals(dashboradpage.getInventoryFieldText(),vendorportaltestdata.InventoryField());

        dashboradpage.searchOrderHistory(vendorportaltestdata.keyword());
        Assert.assertEquals(dashboradpage.getNumberOfEntries(), vendorportaltestdata.NumberOfEntriesFound());
    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logoutTest(){
        dashboradpage.logoutFromJohn();
        Assert.assertTrue(loginpage.isAt());
    }










}
