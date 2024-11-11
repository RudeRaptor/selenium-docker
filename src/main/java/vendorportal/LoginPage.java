package vendorportal;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {


    @FindBy(id = "username") private WebElement usernameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(id = "login") private WebElement loginBtn;
    @FindBy(css = "label[for='customCheck']") private WebElement checkBoxField;
    @FindBy(css = ".h4") private WebElement welcomeMsg;





    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.welcomeMsg));
        return this.welcomeMsg.isDisplayed();

    }

    public void goToUrl(String url){
        this.driver.get(url);
    }

    public void login(String username, String password){
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.checkBoxField.click();
        this.loginBtn.click();
    }


}
