package flightreservation;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightSearchPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightSearchPage.class);


    @FindBy(id = "passengers") WebElement passengerSelector;
    @FindBy(id = "arrive-in") WebElement ArriveInSelector;
    @FindBy(id = "search-flights") WebElement SearchFlightBtn;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }


    public void selectPassengers(String noOfPassengers) {
        Select passengers = new Select(passengerSelector);
        passengers.selectByValue(noOfPassengers);

    }



    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelector));
        return this.passengerSelector.isDisplayed();

    }


    public void selectDestination(String Destination) {

        Select destinationPassenger = new Select(ArriveInSelector);
        destinationPassenger.selectByValue(Destination);
        String chosenDestination = destinationPassenger.getFirstSelectedOption().getText();
        log.info("Chosen destination: {}", chosenDestination);


    }


    public void confirmSearch() {

        this.SearchFlightBtn.click();
    }
}
