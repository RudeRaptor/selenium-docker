package flightreservation;


import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegistrationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(RegistrationPage.class);


    @FindBy(id = "firstName") private WebElement firstNameField;
    @FindBy(id = "lastName") private WebElement lastNameField;
    @FindBy(id = "email") private WebElement emailField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(name = "street") private WebElement streetField;
    @FindBy(name = "city") private WebElement cityField;
    @FindBy(name = "zip") private WebElement zipField;
    @FindBy(id = "inputState") private WebElement stateField;
    @FindBy(id = "register-btn") private WebElement registerbutton;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameField));
        return this.firstNameField.isDisplayed();
    }

    public void goToFlightUrl(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName) {

        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password) {

        this.emailField.sendKeys(email);
        this.passwordField.sendKeys(password);
    }

    public void enterUserAddress(String street, String city, String zip) {

        this.streetField.sendKeys(street);
        this.cityField.sendKeys(city);
        this.zipField.sendKeys(zip);

    }

    public void enterUserState(String state) {
        Select selectstate = new Select(this.stateField);
        selectstate.selectByVisibleText(state);

    }

    public void registering() {

        Select selectState = new Select(this.stateField);
        String chosenState = selectState.getFirstSelectedOption().getText();
        log.info("Chosen state: {}", chosenState);
        this.registerbutton.click();
    }














}
