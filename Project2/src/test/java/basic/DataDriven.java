package basic;

import org.testng.annotations.DataProvider;

public class DataDriven {
	 @DataProvider(name = "loginCredentials")
	    public Object[][] getLoginCredentials() {
	        return new Object[][]{
	                {"Yarra@gmail.com", "Password"}
	        };
	    }
	 @DataProvider(name = "invalidLoginCredentials")
     public Object[][] getInvalidLoginCredentials() {
         return new Object[][]{
                 {"invalidemail@gmail.com", "invalidpassword"},
         };
	 }
	 @DataProvider(name = "signUpCredentials")
     public Object[][] getSignUpCredentials() {
         return new Object[][]{
                 {"ramya", "YramyaNS@gmail.com"},
         };
	 }
	 @DataProvider(name = "WriteYourReview")
     public Object[][] getWriteYourReview() {
         return new Object[][]{
                 {"Ramya", "Yarra@gmail.com", "Is this Blue Top"},
         };
	 }
	 @DataProvider(name = "RegisterUserWithExistingEmail")
	    public Object[][] getRegisterUserWithExistingEmail() {
	        return new Object[][]{
	                {"Ramya", "Yarra@gmail.com"}
	        };
	    }
	 @DataProvider(name = "ContactUsForm")
	    public Object[][] getContactUsForm() {
	        return new Object[][]{
	                {"Ramya Yarra", "Yarra@gmail.com", "Test Subject", "This is a test message.", "D:\\Testing\\Selenium\\Projects\\ContactForm.txt"}
	        };
	    }
}
