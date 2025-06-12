package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public void fillRegistrationForm(String firstName, String lastName, String email, String password, String confirmPassword, String dateofbirth) {
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
        driver.findElement(By.id("dp")).sendKeys(dateofbirth);

    }

    /*private WebElement waitUntilClickable(By locator) {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }*/

   /* private WebElement waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }*/


    public void termsAndConditionsAccepted() {
        //WebElement checkbox = waitUntilClickable(By.id("sign_up_25"));
        // WebElement checkbox =  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='sign_up_25']")));
        WebElement label = driver.findElement(By.cssSelector("label[for='sign_up_25']"));

        if (!label.isSelected()) {
            label.click();
        }
    }

   /* public void clickConfirmAndJoin() {
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input.btn.btn-big.red")));
        confirmButton.click();
    }*/

    public void clickConfirmAndJoin() {
        WebElement confirmButton = waitUntilClickable(By.cssSelector("input.btn.btn-big.red"));
        confirmButton.click();
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void ageConfirmation(){
        WebElement ageCheck = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
        ageCheck.click();
    }

    public void ethicsAndConduct() {
        WebElement checkbox = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
         checkbox.click();
    }

    public void isJoinConfirmed(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement join = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[name='join'][value='CONFIRM AND JOIN']")));
        join.click();
    }

    public boolean isRegistrationSubmitted() throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.urlContains("SignUpConfirmation?"));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".signup-confirmation"))
        );
        return confirmation.isDisplayed();
    }

    /*public boolean isRegistrationValid() {
        List<WebElement> errors = driver.findElements(By.cssSelector(".field-validation-error"));
        for(WebElement error : errors) {
            if (error.isDisplayed()) {
                System.out.println("Error shows " + error.getText());
                return true;
            }
        }
            return false;


    }*/

    public boolean isLastNameDisplayed(String expectedMessage) {
        WebElement error = driver.findElement(By.cssSelector("[data-valmsg-for='Surname']"));
        return error.getText().trim().contains(expectedMessage);
    }

    public boolean isPasswordDisplayed(String expectedMessage) {
        WebElement error = driver.findElement(By.cssSelector("[data-valmsg-for='ConfirmPassword']"));
        return error.getText().trim().contains(expectedMessage);
    }


   public boolean isTermsDisplayed(String expectedMessage) {
    WebElement terms = driver.findElement(By.cssSelector("span[data-valmsg-for='TermsAccept']"));
    return terms.getText().trim().contains(expectedMessage);
    }
}

