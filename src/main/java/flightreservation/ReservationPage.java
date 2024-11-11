package flightreservation;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ReservationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(ReservationPage.class);

    @FindBy(css = "form:nth-child(5) >div:nth-child(1) > div:nth-child(1) > div:nth-child(2)> div:last-child")
    private WebElement reservationTax;
    @FindBy(css = "form:nth-child(5) >div:nth-child(1) > div:nth-child(1) > div:nth-child(3)> div:first-child")
    private WebElement reservationTotalText;
    @FindBy(css = "form:nth-child(5) >div:nth-child(1) > div:nth-child(1) > div:nth-child(3)> div:last-child")
    private WebElement reservationTotalPrice;



    public ReservationPage(WebDriver driver) {
        super(driver);
    }


    public String getPrice(){
        String taxText = this.reservationTax.getText();
        String priceText = this.reservationTotalPrice.getText();
        log.info("Flight confirmation amount tax: {}", taxText);
        log.info("Flight confirmation total price: {}", priceText);
        return this.reservationTotalPrice.getText();
    }




    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.reservationTotalPrice));
        return this.reservationTotalPrice.isDisplayed();
    }
}
