package flightreservation;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationConfirmationPage extends AbstractPage {


    @FindBy(id = "go-to-flights-search") private WebElement goToFlightsSearchBtn;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchBtn));
        return this.goToFlightsSearchBtn.isDisplayed();
    }

    public void goToFlightSearch() {

        this.goToFlightsSearchBtn.click();

    }



















}
