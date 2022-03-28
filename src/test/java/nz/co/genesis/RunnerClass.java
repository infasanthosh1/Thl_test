package nz.co.genesis;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "classpath:features",
        glue = "StepDefinations",
        plugin = {"json:target/cucumber.json"},
        publish = true,
        tags = "@SmokeTest"//{"@Default","~@ForgotPassword","~@MyAccountFeature","~@Signup"} "~@AddAttachmentToExistingTicket"
)
public class RunnerClass {
}