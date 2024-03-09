package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	WebDriver driver;

    @FindBy(name = "name_on_card")
    private WebElement cardName;

    @FindBy(name = "card_number")
    private WebElement cardNumber;
    
    @FindBy(name = "cvc")
    private WebElement cardCVV;
    
    @FindBy(name = "expiry_month")
    private WebElement cardExpirymonth;

    @FindBy(name = "expiry_year")
    private WebElement cardExpiryyear;
    
    @FindBy(id = "submit")
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//h2[@class='title text-center' and contains(., 'Order Placed!')]")
    private WebElement orderPlacedMessageElement;
    
    @FindBy(css = "a[class='btn btn-default check_out']")
    private WebElement downloadInvoiceButton;
    
    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;
    
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNameOnCard(String name) {
    	cardName.sendKeys(name);
    }

    public void enterCardNumber(String number) {
    	cardNumber.sendKeys(number);
    }

    public void enterCVC(String cvc) {
    	cardCVV.sendKeys(cvc);
    }

    public void enterExpiryMonth(String month) {
    	cardExpirymonth.sendKeys(month);
    }

    public void enterExpiryYear(String year) {
    	cardExpiryyear.sendKeys(year);
    }

    public void clickSubmitButton() {
    	placeOrderButton.click();
    }
    
    public boolean isOrderPlacedMessageDisplayed() {
        return orderPlacedMessageElement.isDisplayed();
    }
    
    public void downloadInvoiceButtonClick() {
        downloadInvoiceButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
