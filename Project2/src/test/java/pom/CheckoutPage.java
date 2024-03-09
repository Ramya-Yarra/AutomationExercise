package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void proceedToCheckout() {
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
        proceedToCheckoutButton.click();
    }
    
    public void registerAndCheckout() {
    	WebElement registerLoginLink = driver.findElement(By.linkText("Register / Login"));
        registerLoginLink.click();
    } 
    private By deliveryAddressTitleLocator = By.xpath("//ul[@id='address_delivery']//h3[@class='page-subheading']");
    private By deliveryAddressLocator = By.id("address_delivery");

    private By billingAddressTitleLocator = By.xpath("//ul[@id='address_invoice']//h3[@class='page-subheading']");
    private By billingAddressLocator = By.id("address_invoice");
    
    private By reviewOrderHeadingLocator = By.cssSelector("h2.heading");
    
    private By cartTableLocator = By.cssSelector("table.table-condensed");
    private By productRowsLocator = By.cssSelector("tbody tr[id^='product-']");
    
    private By orderMsgTextAreaLocator = By.name("message");
    
    private By placeOrderButtonLocator = By.cssSelector("a.btn.btn-default.check_out");
    
    public WebElement getDeliveryAddressTitleElement() {
        return driver.findElement(deliveryAddressTitleLocator);
    }

    public WebElement getDeliveryAddressElement() {
        return driver.findElement(deliveryAddressLocator);
    }
    
    public void DeliveryAddressElement() {
    	WebElement DeliveryAddress = driver.findElement(By.id("address_delivery"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DeliveryAddress);
        Actions actions = new Actions(driver);
        actions.moveToElement(DeliveryAddress).perform();
    }

    public WebElement getBillingAddressTitleElement() {
        return driver.findElement(billingAddressTitleLocator);
    }

    public WebElement getBillingAddressElement() {
        return driver.findElement(billingAddressLocator);
    }
    
    public void BillingAddressElement() {
    	WebElement BillingAddress = driver.findElement(By.id("address_invoice"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", BillingAddress);
        Actions actions = new Actions(driver);
        actions.moveToElement(BillingAddress).perform();
    }

    public WebElement getReviewOrderHeadingElement() {
        return driver.findElement(reviewOrderHeadingLocator);
    }

    public boolean isReviewOrderHeadingDisplayed() {
        WebElement reviewOrderHeading = getReviewOrderHeadingElement();
        return reviewOrderHeading.isDisplayed();
    }

    public WebElement getCartTableElement() {
        return driver.findElement(cartTableLocator);
    }

    public List<WebElement> getProductRows() {
        return driver.findElements(productRowsLocator);
    }

    public boolean verifyProductDetails(String productName, String category, String price, String quantity, String total) {
        List<WebElement> productRows = getProductRows();

        for (WebElement row : productRows) {
            String actualProductName = row.findElement(By.cssSelector("td.cart_description h4 a")).getText();
            String actualCategory = row.findElement(By.cssSelector("td.cart_description p")).getText();
            String actualPrice = row.findElement(By.cssSelector("td.cart_price p")).getText();
            String actualQuantity = row.findElement(By.cssSelector("td.cart_quantity button")).getText();
            String actualTotal = row.findElement(By.cssSelector("td.cart_total p.cart_total_price")).getText();

            if (actualProductName.equals(productName)
                    && actualCategory.equals(category)
                    && actualPrice.equals(price)
                    && actualQuantity.equals(quantity)
                    && actualTotal.equals(total)) {
                return true;
            } else {
            	System.out.println("Mismatch detected:");
                System.out.println("Expected Product Name: " + productName);
                System.out.println("Actual Product Name: " + actualProductName);
                System.out.println("Expected Category: " + category);
                System.out.println("Actual Category: " + actualCategory);
                System.out.println("Expected Price: " + price);
                System.out.println("Actual Price: " + actualPrice);
                System.out.println("Expected Quantity: " + quantity);
                System.out.println("Actual Quantity: " + actualQuantity);
                System.out.println("Expected Total: " + total);
                System.out.println("Actual Total: " + actualTotal);
                
            }
        }

        return false;
    }
    public WebElement getOrderMsgTextAreaElement() {
        return driver.findElement(orderMsgTextAreaLocator);
    }
    public void enterOrderComment(String comment) {
        WebElement orderMsgTextArea = getOrderMsgTextAreaElement();
        orderMsgTextArea.clear();
        orderMsgTextArea.sendKeys(comment);
    }

    public WebElement getPlaceOrderButtonElement() {
        return driver.findElement(placeOrderButtonLocator);
    }

    public void clickPlaceOrderButton() {
        WebElement placeOrderButton = getPlaceOrderButtonElement();
        placeOrderButton.click();
    }
}

