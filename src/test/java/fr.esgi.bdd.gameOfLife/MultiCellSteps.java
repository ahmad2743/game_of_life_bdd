package fr.esgi.bdd.gameOfLife;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static fr.esgi.bdd.gameOfLife.Parser.parseSimplifiedLife1_05;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiCellSteps {
    private GameOfLifeUniverse universe;

    @Given("the following setup:")
    public void theFollowingSetup(final String spec) {
        universe = parseSimplifiedLife1_05(spec);
    }

    @When("I evolve the board")
    public void iEvolveTheBoard() {
        universe = universe.next();
    }

    @Then("I should see the following board:")
    public void iShouldSeeTheFollowingBoard(final String spec) {

        assertEquals(parseSimplifiedLife1_05(spec), universe);
    }
}
