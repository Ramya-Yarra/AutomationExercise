package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    @FindBy(css = "li a[href='/login']")
    private WebElement signupLoginButton;

    @FindBy(xpath = "//h2[contains(text(), 'Login to your account')]")
    private WebElement loginToYourAccount;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailField;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/button")
    private WebElement loginButton;

    @FindBy(xpath = "//li/a[contains(text(), 'Logged in as')]/b")
    private WebElement loggedInAsUsername;

    @FindBy(css = "li a[href='/delete_account']")
    private WebElement deleteAccountButton;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
    private WebElement accountDeleted;
    
    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/p")
    private WebElement loginError;
    
    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        setZoomLevel(driver, 0.65);
        PageFactory.initElements(driver, this);
    }

    public void clickOnSignupLoginButton() {
        signupLoginButton.click();
    }

    public void verifyLoginToYourAccountIsVisible() {
        loginToYourAccount.isDisplayed();
    }
    
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void verifyLoggedInAsUsernameIsVisible() {
        loggedInAsUsername.isDisplayed();
    }

    public void clickOnDeleteAccountButton() {
        deleteAccountButton.click();
    }

    public void verifyAccountDeletedIsVisible() {
        accountDeleted.isDisplayed();
    }
    
    public boolean isErrorVisible(String errorMessage) {
        return loginError.isDisplayed() && loginError.getText().equals(errorMessage);
    }
    
    public LoginPage clickOnLogoutButton() {
        logoutButton.click();
        return new LoginPage(driver);
    }
    
    private static void setZoomLevel(WebDriver driver, double zoomLevel) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "document.body.style.transform = 'scale(' + arguments[0] + ')';document.body.style.transformOrigin = '0.50';";
        jsExecutor.executeScript(script, zoomLevel);
	}
    
    public void clickTestCasesLink() {
        WebElement element1 = driver.findElement(By.linkText("Test Cases"));
        element1.click();
    }

    public boolean isBoldElementDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement boldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(), 'Test Cases')]")));
        return boldElement.isDisplayed();
    }
}
