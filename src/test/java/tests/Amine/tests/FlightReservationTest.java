package tests.Amine.tests;
import com.Amine.Abstract.AbstractTest;

import flightreservation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.Amine.tests.model.FlightReservationTestData;
import util.Config;
import util.Constants;
import util.JsonUtil;



public class FlightReservationTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);

    private RegistrationPage registrationpage;
    private FlightSearchPage flightsearch;
    private FlightSelectionPage flightselection;
    private ReservationPage reservation;
    private RegistrationConfirmationPage reservationconfirm;
    private FlightReservationTestData flightreservationtestdata;


    @BeforeTest
    @Parameters({"testDataPath"})
    public void setUpObjects(String testDataPath) {

        if (testDataPath == null) {
            log.warn("testDataPath parameter is missing, using default.");
        }
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized");
        }

        this.registrationpage = new RegistrationPage(driver);
        this.flightsearch = new FlightSearchPage(driver);
        this.flightselection = new FlightSelectionPage(driver);
        this.reservation = new ReservationPage(driver);
        this.reservationconfirm = new RegistrationConfirmationPage(driver);
        this.flightreservationtestdata = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);



    }


    @Test
    public void userRegistrationTest() throws InterruptedException {

        registrationpage.goToFlightUrl(Config.get(Constants.FLIGHTRESERVATION_URL));
        Assert.assertTrue(registrationpage.isAt());
        registrationpage.enterUserDetails(flightreservationtestdata.firstName(), flightreservationtestdata.LastName());
        registrationpage.enterUserCredentials(flightreservationtestdata.email(), flightreservationtestdata.password());
        registrationpage.enterUserAddress(
                flightreservationtestdata.street(),
                flightreservationtestdata.city(),
                flightreservationtestdata.zip());
        Thread.sleep(30);
        registrationpage.enterUserState(flightreservationtestdata.state());
        registrationpage.registering();
    }


    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){

        Assert.assertTrue(reservationconfirm.isAt());
        reservationconfirm.goToFlightSearch();
    }


    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() throws InterruptedException {


        Assert.assertTrue(flightsearch.isAt());
        flightsearch.selectPassengers(flightreservationtestdata.passengersCount());
        flightsearch.selectDestination(flightreservationtestdata.destination());
        flightsearch.confirmSearch();
        Thread.sleep(20);

    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){


        Assert.assertTrue(flightselection.isAt());
        flightselection.SelectFlight();
        flightselection.confirmFlights();

    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void reservationTest(){


        Assert.assertTrue(reservation.isAt());
        Assert.assertEquals(reservation.getPrice(), flightreservationtestdata.expectedPrice());




    }





}
