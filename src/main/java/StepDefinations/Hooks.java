package StepDefinations;

import Libraries.Utilities;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Hooks extends Utilities implements En {

    public static Scenario myScenario;

    public Hooks() {

        Before((Scenario scenario) -> {
            InputStream input = new FileInputStream("src/main/resources/test.properties");
            Properties properties = new Properties();
            properties.load(input);
            Setup(properties.getProperty("browser"));
            myScenario = scenario;
        });
        After((Scenario scenario) -> {
            if(scenario.isFailed()) {
                writeToLog("Current Page URL is " + getDriver().getCurrentUrl());
                fnScreenshot(getDriver());
            }
            tearDown();
        });
    }
}
