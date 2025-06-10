package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;


public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vijaylaxmi Buddhiraj\\IdeaProjects\\UserRegistrationTest_Automation\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        registrationPage.openRegistrationPage();
    }

    @When("I fill in all the details")
    public void i_fill_in_all_the_details() {
        registrationPage.fillRegistrationForm("Samika", "Buddh", "samika@example.com", "sam123", "sam123", "19/03/1986");
    }

    @When("I agree to the terms and conditions")
    public void i_agree_to_the_terms_and_conditions() {
        registrationPage.termsAndConditionsAccepted();

    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() throws InterruptedException {
        Assert.assertTrue("Confirmation message not shown",
                registrationPage.isRegistrationSubmitted());
        Thread.sleep(5000);
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String buttonText) {
        registrationPage.clickConfirmAndJoin();
    }

    @When("I fill in all the details except the last name")
    public void iFillInAllTheDetailsExceptTheLastName() {
        registrationPage.fillRegistrationForm("Samika", "", "samika@example.com", "sam123", "sam123", "19/03/1986");
    }

    @Then("I should see an error message for missing last name")
    public void iShouldSeeAnErrorMessageForMissingLastName() {
        Assert.assertTrue(registrationPage.isValidationMessageDisplayed("Last Name is required"));
    }

    @When("I fill in the details with mismatched passwords")
    public void iFillInTheDetailsWithMismatchedPasswords() {
        registrationPage.fillRegistrationForm("Samika", "", "samika@example.com", "sam123", "sam456", "19/03/1986");
    }

    @Then("I should see a message with mismatch password error")
    public void iShouldSeeAMessageWithMismatchPasswordError() {
        Assert.assertTrue("Message with mismatch password is not shown",
                registrationPage.isValidationMessageDisplayed("Password did not match"));
    }

    @And("I do not agree with the terms and conditions")
    public void iDoNotAgreeWithTheTermsAndConditions() {
        WebElement label = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
        if (label.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].checked = false;", label);
        }

    }

    @Then("I should see an error message for not accepting the terms")
    public void iShouldSeeAnErrorMessageForNotAcceptingTheTerms() {
        Assert.assertTrue("Terms and conditions are not shown",
                registrationPage.isValidationMessageDisplayed("You must confirm that you have read and accepted our Terms and Conditions"));
    }


    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

