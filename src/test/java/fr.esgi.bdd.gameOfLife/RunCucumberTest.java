package fr.esgi.bdd.gameOfLife;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "src/test/resources/features"
)
@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.platform.engine.Cucumber
public class RunCucumberTest {
}