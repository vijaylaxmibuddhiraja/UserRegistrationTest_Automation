package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.By;
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
        registrationPage.fillRegistrationForm("Samika", "Buddh", "samika@example.com", "sam123", "19/03/1986");
    }

    @When("I agree to the terms and conditions")
    public void i_agree_to_the_terms_and_conditions() {
        WebElement termsCheckbox = driver.findElement(By.id("sign_up_25"));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() throws InterruptedException {
        Assert.assertTrue("Confirmation message not shown",
                registrationPage.isRegistrationSubmitted());
        Thread.sleep(5000);
    }
}

  /*  @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

