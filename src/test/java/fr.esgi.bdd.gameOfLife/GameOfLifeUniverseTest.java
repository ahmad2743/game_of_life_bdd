package fr.esgi.bdd.gameOfLife;

import org.junit.Test;

import static fr.esgi.bdd.gameOfLife.GameRules.gameRules;
import static fr.esgi.bdd.gameOfLife.PPoint.P;
import static java.util.Set.of;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.ShortVariable"})
class GameOfLifeUniverseTest {

    @Test
    void equality() {
        final GameOfLifeUniverse<PPoint> u1 = new GameOfLifeUniverse<>(gameRules(of(), of()));
        final GameOfLifeUniverse<PPoint> u2 = new GameOfLifeUniverse<>(gameRules(of(), of()));
        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void inEquality() {
        final GameOfLifeUniverse<PPoint> u1 = new GameOfLifeUniverse<>(gameRules(of(), of(1)));
        final GameOfLifeUniverse<PPoint> u2 = new GameOfLifeUniverse<>(gameRules(of(1), of()));
        assertNotEquals(u1, u2);
        assertNotEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Universe{R 23/3\n[P(0, 1)]}", new GameOfLifeUniverse<>(of(P(0, 1))).toString());
    }
}

