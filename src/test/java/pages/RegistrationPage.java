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
       /* WebElement confirmButton = waitUntilClickable(By.xpath("//button[contains(text(),'CONFIRM AND JOIN')]"));
        confirmButton.click();*/
    }

    /*public void agreeTermsAndConditions() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign_up_25")));
        js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
        js.executeScript("arguments[0].click();", checkbox);

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(),'CONFIRM AND JOIN')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        js.executeScript("arguments[0].click();", submitButton);

       // WebElement checkbox = waitUntilClickable(By.id("sign_up_25"));
        /*WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign_up_25")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        js.executeScript("arguments[0].click();", checkbox);

        WebElement submitButton = waitUntilClickable(By.xpath("//button[contains(text(),'CONFIRM AND JOIN')]"));
        js.executeScript("arguments[0].click();", submitButton);
    }*/

    public void clickConfirmAndJoin() {
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input.btn.btn-big.red")));
        confirmButton.click();
    }



    public boolean isRegistrationSubmitted() {
        try {
             wait.until(ExpectedConditions.urlContains("SignUpConfirmation?"));
            System.out.println("URL: " + driver.getCurrentUrl());

            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND')]"))
            );
            return confirmation.isDisplayed();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean isValidationMessageDisplayed(String messageText) {
        List<WebElement> errorMessages = driver.findElements(By.className("field-validation-error"));
        for (WebElement msg : errorMessages) {
            if (msg.getText().contains(messageText)) {
                return true;
            }
        }
        return false;
    }


}


