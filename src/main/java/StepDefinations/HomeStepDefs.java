package StepDefinations;

import Libraries.Utilities;
import io.cucumber.java8.En;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
/**
 * created by kallepuk on 10/08/2017
 */
public class HomeStepDefs extends Utilities implements En {

    public HomeStepDefs() {

        Then("^I should see sign-in in header section right$",() -> assertTrue("Move Work Station tile not displayed", isElementDisplayed(googleHomePage.signIn)));

        Then("^I search for \"([^\"]*)\"$" ,(String arg1)->{
            enterTextInEdit(googleHomePage.searchEdit, arg1);
            clickElement(googleHomePage.searchButton);
        });

        Then("^I should see \"([^\"]*)\" results$", (String arg0) -> {
            waitForElementVisibility(googleHomePage.allResults);
            assertThat("String Not Found", googleHomePage.resultLinks.get(0).getText(), is(containsString(arg0)));
            fnScreenshot(getDriver());
        });
        Given("^I am navigate to \"([^\"]*)\"$", (String arg0) -> {
            getPage(arg0);
            waitForElementVisibility(googleHomePage.signIn);
        });

    }

}
