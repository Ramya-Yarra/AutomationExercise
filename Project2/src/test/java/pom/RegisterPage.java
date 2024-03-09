package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	private WebDriver driver;

        public RegisterPage(WebDriver driver) {
            this.driver = driver;
        }
        
        public void clickSignUpLoginButton() {
            WebElement signUpLoginButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"));
            signUpLoginButton.click();
        }

        public void clickDeleteAccountButton() {
            WebElement deleteAccountButton = driver.findElement(By.cssSelector("li a[href='/delete_account']"));
            deleteAccountButton.click();
        }

        public boolean isAccountDeletedVisible() {
            WebElement accountDeletedElement = driver.findElement(By.cssSelector(".title.text-center[data-qa='account-deleted']"));
            return accountDeletedElement.isDisplayed();
        }
        
        public String getLoggedInText() {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(), 'Logged in as')]/b")));
            return loggedInElement.getText().trim();
        }

        public boolean isNewUserSignupVisible() {
            WebElement newUserSignupElement = driver.findElement(By.xpath("//h2[contains(text(), 'New User Signup!')]"));
            return newUserSignupElement.isDisplayed();
        }

        public void enterNameAndEmail(String name, String email) {
            WebElement nameInput = driver.findElement(By.name("name"));
            WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));

            nameInput.sendKeys(name);
            emailInput.sendKeys(email);
        }

        public void clickSignUpButton() {
            WebElement signUpButton = driver.findElement(By.cssSelector("button[data-qa='signup-button'].btn.btn-default"));
            signUpButton.click();
        }

        public boolean isEnterAccountInfoVisible() {
        	WebElement enterAccountInfoElement = driver.findElement(By.cssSelector("h2.title.text-center[style='color: #FE980F;'] b"));
            return enterAccountInfoElement.isDisplayed();
        }

        public void fillAccountInformation(String title, String password, String dob) {
        	WebElement titleRadioButton = driver.findElement(By.id(title.equalsIgnoreCase("Mr") ? "id_gender1" : "id_gender2"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement daysDropdown = driver.findElement(By.id("days"));
            WebElement monthsDropdown = driver.findElement(By.id("months"));
            WebElement yearsDropdown = driver.findElement(By.id("years"));

            titleRadioButton.click();
            passwordInput.sendKeys(password);
            String[] dobArray = dob.split("/");
            daysDropdown.sendKeys(dobArray[0]);
            monthsDropdown.sendKeys(dobArray[1]);
            yearsDropdown.sendKeys(dobArray[2]);
        }

        public void selectNewsletterCheckbox() {
            WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newsletterCheckbox);
            Actions actions = new Actions(driver);
            actions.moveToElement(newsletterCheckbox).click().perform();
        }

        public void selectSpecialOffersCheckbox() {
            WebElement specialOffersCheckbox = driver.findElement(By.id("optin"));
            specialOffersCheckbox.click();
        }

        public void fillAdditionalDetails(String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        	WebElement firstNameInput = driver.findElement(By.id("first_name"));
            WebElement lastNameInput = driver.findElement(By.id("last_name"));
            WebElement companyInput = driver.findElement(By.id("company"));
            WebElement address1Input = driver.findElement(By.id("address1"));
            WebElement address2Input = driver.findElement(By.id("address2"));
            WebElement countryDropdown = driver.findElement(By.id("country"));
            WebElement stateInput = driver.findElement(By.id("state"));
            WebElement cityInput = driver.findElement(By.id("city"));
            WebElement zipcodeInput = driver.findElement(By.id("zipcode"));
            WebElement mobileNumberInput = driver.findElement(By.id("mobile_number"));

            firstNameInput.sendKeys(firstName);
            lastNameInput.sendKeys(lastName);
            companyInput.sendKeys(company);
            address1Input.sendKeys(address1);
            address2Input.sendKeys(address2);
            countryDropdown.sendKeys(country);
            stateInput.sendKeys(state);
            cityInput.sendKeys(city);
            zipcodeInput.sendKeys(zipcode);
            mobileNumberInput.sendKeys(mobileNumber);
        }

        public void clickCreateAccountButton() {
            WebElement createAccountButton = driver.findElement(By.cssSelector("button[data-qa='create-account'].btn.btn-default"));
            createAccountButton.click();
        }

        public boolean isAccountCreatedMessageVisible() {
            WebElement accountCreatedMessage = driver.findElement(By.cssSelector("h2[data-qa='account-created']"));
            return accountCreatedMessage.isDisplayed();
        }
        
        public void clickContinueButton() {
            WebElement continueButton = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
            continueButton.click();
        }

        public boolean isErrorVisible(String errorMessage) {
            WebElement errorElement = driver.findElement(By.xpath("//p[text()='" + errorMessage + "']"));
            return errorElement.isDisplayed();
        }
}
