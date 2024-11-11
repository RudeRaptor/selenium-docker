package vendorportal;

import com.Amine.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboradPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboradPage.class);
    @FindBy(id = "monthly-earning") private WebElement monthlyField;
    @FindBy(id = "annual-earning") private WebElement annualField;
    @FindBy(id = "profit-margin") private WebElement profitMarginField;
    @FindBy(id = "available-inventory") private WebElement inventoryField;
    @FindBy(css = "input[type='search']") private WebElement searchField;
    @FindBy(css = "#dataTable_info") private WebElement searchResultsField;
    @FindBy(css = ".img-profile") private WebElement userProfilPicture;
    @FindBy(linkText= "Logout") private WebElement logoutField;
    @FindBy(css= "a.btn-primary") private WebElement logoutPopUpButton;

    public DashboradPage(WebDriver driver) {
        super(driver);
    }



    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyField));
        return this.monthlyField.isDisplayed();
    }

    public String getMonthlyFieldText() {
        return this.monthlyField.getText();
    }
    public String getAnnualFieldText() {
        return this.annualField.getText();
    }
    public String getProfitFieldText() {
        return this.profitMarginField.getText();
    }
    public String getInventoryFieldText() {
        return this.inventoryField.getText();
    }
    public void searchOrderHistory(String keyword) {
        this.searchField.sendKeys(keyword);
    }
    public int getNumberOfEntries() {
        String result = this.searchResultsField.getText();
        String[] arr = result.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Result displayed: {} entries", count);
        return count;
    }

    public void logoutFromJohn() {

        this.userProfilPicture.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutField));
        this.logoutField.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutPopUpButton));
        this.logoutPopUpButton.click();
    }


}
