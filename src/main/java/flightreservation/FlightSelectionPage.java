package flightreservation;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight") private List<WebElement> DepFlightOpt;
    @FindBy(name = "arrival-flight") private List<WebElement> ArriveFlightOpt;
    @FindBy(id = "confirm-flights") private WebElement ConfirmationFlight;



    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    public void SelectFlight(){

        int random = ThreadLocalRandom.current().nextInt(0, DepFlightOpt.size());
        this.DepFlightOpt.get(random).click();
        this.ArriveFlightOpt.get(random).click();
    }

    public void confirmFlights(){
        this.ConfirmationFlight.click();
    }








    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.ConfirmationFlight));
        return this.ConfirmationFlight.isDisplayed();

    }
}
