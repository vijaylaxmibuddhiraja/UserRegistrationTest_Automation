package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import pages.RegistrationPage;


public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    /*@Before
    public void setup()  {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
    }*/

    @Before
    public void setup()  {
        System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
    }

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        registrationPage.openRegistrationPage();
    }

    @When("I fill in all the details")
    public void iFillInAllTheDetails() {
        registrationPage.fillRegistrationForm("Samika", "Buddh", "samika" +System.currentTimeMillis() + "@example.com", "sam123", "sam123", "19/03/1986");
    }

    @When("I agree to the terms and conditions")
    public void iAgreeToTheTermsAndConditions() {
        registrationPage.termsAndConditionsAccepted();
    }

    @When("I fill in all the details except the last name")
    public void iFillInAllTheDetailsExceptTheLastName() {
        registrationPage.fillRegistrationForm("Samika", "", "samika19@example.com" , "sam123", "sam123", "19/03/1986");
    }


    @And("I should see a confirmation that I am over {int} years")
    public void iShouldSeeAConfirmationThatIAmOverYears(int age) {
       registrationPage.ageConfirmation();
    }

    @And("I accept the code of ethics and conduct")
    public void iAcceptTheCodeOfEthicsAndConduct() {
        registrationPage.ethicsAndConduct();
    }


    @And("I click on the {string} button")
    public void iClickOnTheButton(String buttonText) {
        registrationPage.clickConfirmAndJoin();

    }

    @And("I see it is redirecting to join page")
    public void iSeeItIsRedirectingToJoinPage() {
      registrationPage.isJoinConfirmed();
    }


    @Then("I should see a confirmation message")
    public void iShouldSeeAConfirmationMessage() throws InterruptedException {
        Assert.assertTrue("Confirmation message not shown",
                registrationPage.isRegistrationSubmitted()
        );

    }

    @Then("I should see an error message for missing last name")
    public void iShouldSeeAnErrorMessageForMissingLastName() {
        Assert.assertTrue("Expected error message not shown",
                registrationPage.isLastNameDisplayed("Last Name is required"));
    }

    /*@When("I fill in all the details except the last name")
    public void iFillInAllTheDetailsExceptTheLastName() {
        driver.findElement(By.id("member_firstname")).sendKeys("Samika");
        // driver.findElement(By.id("member_lastname")).sendKeys("Singh");
        driver.findElement(By.id("member_emailaddress")).sendKeys("samika@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("sam123");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("sam123");
        // ...continue filling other required fields
    }*/


    @When("I fill in the details with mismatched passwords")
    public void iFillInTheDetailsWithMismatchedPasswords() {
        registrationPage.fillRegistrationForm("Samika", "", "samika@example.com", "sam123", "sam456", "19/03/1986");
    }

    @Then("I should see a message with mismatch password error")
    public void iShouldSeeAMessageWithMismatchPasswordError() {
        Assert.assertTrue("Message with mismatch password is not shown",
                registrationPage.isPasswordDisplayed("Password did not match"));
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
                registrationPage.isTermsDisplayed("You must confirm that you have read and accepted our Terms and Conditions"));
    }




   /* @After
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        if (driver != null) {
            driver.quit();
        }
    }*/


    }





