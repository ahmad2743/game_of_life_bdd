package fr.esgi.bdd.gameOfLife;

import org.junit.jupiter.api.Test;

import static fr.esgi.bdd.gameOfLife.PPoint.P;
import static fr.esgi.bdd.gameOfLife.Parser.parseSimplifiedLife1_05;
import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private static void parses(final String spec, final PPoint... cells) {
        assertEquals(new GameOfLifeUniverse(of(cells)), parseSimplifiedLife1_05(spec));
    }

    @Test
    void testParses() {
        assertAll(
                () -> parses(""),
                () -> parses("*", P(0, 0)),
                () -> parses("**", P(0, 0), P(1, 0)),
                () -> parses("*\n*", P(0, 0), P(0, 1)),
                () -> parses("*.*", P(0, 0), P(2, 0))
        );
    }

    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    @Test
    void testInvalid() {
        final Throwable exception = assertThrows(IllegalArgumentException.class, () -> parses("o"));
        assertEquals("Unexpected character 'o' at line 1, column 1", exception.getMessage());
    }
}
