package fr.esgi.bdd.gameOfLife;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static fr.esgi.bdd.gameOfLife.PPoint.P;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;



    final class Parser {
        private final Set<Point> cells = new HashSet<>();
        private int line = 1;
        private int column = 1;
        private final Map<Character, Runnable> syntax = Map.of(
                '\n', () -> {
                    line++;
                    column = 0;
                },
                '*', () -> cells.add(P(column - 1, line - 1)),
                '.', () -> {
                }
        );

        private Parser() {
        }

        @SuppressWarnings({
                "checkstyle:ParameterName",
                "PMD.FormalParameterNamingConventions",
                "PMD.MethodNamingConventions",
        })
        static GameOfLifeUniverse parseSimplifiedLife1_05(final String life1_05) {
            return new Parser().parseSimplifiedLife1_05Impl(life1_05);
        }

        @SuppressWarnings({
                "checkstyle:ParameterName",
                "PMD.FormalParameterNamingConventions",
                "PMD.MethodNamingConventions",
        })
        private GameOfLifeUniverse parseSimplifiedLife1_05Impl(final String life1_05) {
            for (final char c : life1_05.toCharArray()) {
                syntax.getOrDefault(c, () -> syntaxError(c)).run();
                column++;
            }
            return new GameOfLifeUniverse(unmodifiableSet(cells));
        }

        @SuppressWarnings({
                "PMD.ShortVariable",
        })
        private void syntaxError(final char c) {
            throw new IllegalArgumentException(format("Unexpected character '%s' at line %d, column %d", c, line, column));
        }
    }

