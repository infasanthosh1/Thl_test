package Libraries;

import StepDefinations.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.fail;

public class Utilities extends DriverFactory {
    public static String WORK_ORDER_NUMBER = null;
    public static String ACTIVATE_TICKET_NUMBER = null;

    public static void writeToLog(String message){
        Hooks.myScenario.log(message);
    }

    public static void fnScreenshot(WebDriver driver) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Hooks.myScenario.attach(screenshot, "image/png",Hooks.myScenario.getName());
        }catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    public static Boolean verifyText(WebElement element, String text) {
        try {
            return element.getText().equalsIgnoreCase(text);
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public static void waitAndClick(WebElement element) throws ElementNotSelectableException {
        WebDriverWait wait = new WebDriverWait(getDriver() , 20);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

    }

    public static void waitForElementVisibility(WebElement element) throws ElementNotVisibleException {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static Boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }

    public static void clickElement(WebElement element) throws NoSuchElementException {
        element.click();
    }

    public static void moveToElement(WebElement element) throws NoSuchElementException {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build().perform();
    }

    public void enterTextInEdit(WebElement element, String text) throws NoSuchElementException {
        //element.clear();
        element.sendKeys(text);
    }

    public void switchFrame(WebElement element){
        getDriver().switchTo().frame(element);

    }

    public Boolean isAlertPresent() throws NoAlertPresentException {
        getDriver().switchTo().alert();
        return true;
    }

    public void switchToDefault(){
        getDriver().switchTo().defaultContent();
    }

    public void doubleClickElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).doubleClick().build().perform();
    }

    public static Boolean verifyAttributeValue(WebElement element, String attribute, String expectedValue) {
        try {
            clickElement(element);
            return element.getAttribute(attribute).equalsIgnoreCase(expectedValue);
        } catch (NoSuchElementException e) {
            return false;
    }
    }

    public String getAttributeValue(WebElement element, String attribute){
        clickElement(element);
        return element.getAttribute(attribute);
    }

    public void selectDropDownListValue(WebElement element, String listValue){
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(listValue);
        if(!dropdown.getFirstSelectedOption().getText().contains(listValue)) fail("Unable to select given value" +dropdown.getFirstSelectedOption().getText());
    }

    public void wait(int Seconds){
        try {
            Thread.sleep(Seconds* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
