package fr.esgi.bdd.gameOfLife;


import org.junit.jupiter.api.Test;

import java.util.Set;

import static fr.esgi.bdd.gameOfLife.GameRules.gameRules;
import static fr.esgi.bdd.gameOfLife.GameRules.CONWAY_RULES;
import static java.util.Set.of;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
class GamesRulesTest {

    private void assertSurvival(final GameRules rules, final Set<Integer> liveNeighbors) {
        assertAll(range(0, 8).mapToObj(neighbors ->
                () -> assertEquals(liveNeighbors.contains(neighbors), rules.survives(neighbors))));
    }

    private void assertBirth(final GameRules rules, final Set<Integer> liveNeighbors) {
        assertAll(range(0, 8).mapToObj(neighbors ->
                () -> assertEquals(liveNeighbors.contains(neighbors), rules.born(neighbors))));
    }

    @Test
    void testConwayRules() {
        assertAll(
                () -> assertEquals("R 23/3", CONWAY_RULES.toString()),
                () -> assertSurvival(CONWAY_RULES, of(2, 3)),
                () -> assertBirth(CONWAY_RULES, of(3))
        );
    }

    @Test
    void equality() {
        final GameRules rules1 = gameRules(of(), of());
        final GameRules rules2 = gameRules(of(), of());
        assertEquals(rules1, rules2);
        assertEquals(rules1.hashCode(), rules2.hashCode());
    }

    @Test
    void inEquality() {
        final GameRules rules1 = gameRules(of(), of(1));
        final GameRules rules2 = gameRules(of(1), of());
        assertNotEquals(rules1, rules2);
        assertNotEquals(rules1.hashCode(), rules2.hashCode());
    }
}
