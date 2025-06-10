package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openRegistrationPage() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password, String dateofbirth) {
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(password);
        driver.findElement(By.id("dp")).sendKeys(dateofbirth);

    }

    private WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void termsAndConditionsAccepted() {
        WebElement termsCheckbox = waitUntilClickable(By.id("sign_up_25"));
        if (termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
        WebElement confirmButton = waitUntilClickable(By.xpath("//button[contains(text(),'CONFIRM AND JOIN')]"));
        confirmButton.click();
    }



    public boolean isRegistrationSubmitted() {
        try {
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND')]"))
            );
            return confirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


