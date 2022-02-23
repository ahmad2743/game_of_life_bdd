package fr.esgi.bdd.gameOfLife;


import javax.annotation.processing.Generated;
import java.util.Set;
import java.util.stream.Stream;

import static fr.esgi.bdd.gameOfLife.GameRules.CONWAY_RULES;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

@SuppressWarnings({"java:S119","ClassTypeParameterName", "PMD.GenericsNaming"})
record GameOfLifeUniverse<Cell extends Point<Cell>>(@Generated({}) GameRules rules, @Generated({}) Set<Cell> life) {
    @SafeVarargs
    GameOfLifeUniverse(final GameRules rules, final Cell... life) {
        this(rules, Set.of(life));
    }

    GameOfLifeUniverse(final Set<Cell> life) {
        this(CONWAY_RULES, life);
    }

    GameOfLifeUniverse<Cell> next() {
        return new GameOfLifeUniverse<>(rules, cellsOfNextGeneration());
    }

    private Set<Cell> cellsOfNextGeneration() {
        return concat(survivingCells(), bornCells()).collect(toSet());
    }

    private Stream<Cell> survivingCells() {
        return life
                .stream()
                .filter(this::survives);
    }

    private Stream<Cell> bornCells() {
        return deadNeighborsOfLivingCells()
                .filter(this::born);
    }

    private Stream<Cell> deadNeighborsOfLivingCells() {
        return life
                .stream()
                .flatMap(this::deadNeighbors)
                .distinct();
    }

    private boolean survives(final Cell cell) {
        return rules.survives(countLiveNeighbors(cell));
    }

    private boolean born(final Cell cell) {
        return rules.born(countLiveNeighbors(cell));
    }

    private int countLiveNeighbors(final Cell cell) {
        return (int) liveNeighbors(cell).count();
    }

    private Stream<Cell> liveNeighbors(final Cell cell) {
        return cell.neighbors(this::isAlive);
    }

    private Stream<Cell> deadNeighbors(final Cell cell) {
        return cell.neighbors(this::isDead);
    }

    private boolean isAlive(final Cell cell) {
        return life.contains(cell);
    }

    private boolean isDead(final Cell cell) {
        return !isAlive(cell);
    }

    @Override
    public String toString() {
        return "Universe{" + rules + "\n" + life + '}';
    }
}






















/*

public class GameOfLife {
        public static void main(String[] args)
        {
            int M = 10, N = 10;

            // Intiliazing the grid.
            int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };

            // Displaying the grid
            System.out.println("Original Generation");
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if (grid[i][j] == 0)
                        System.out.print(".");
                    else
                        System.out.print("*");
                }
                System.out.println();
            }
            System.out.println();
            nextGeneration(grid, M, N);
        }

        // Function to print next generation
        static void nextGeneration(int grid[][], int M, int N)
        {
            int[][] future = new int[M][N];

            // Loop through every cell
            for (int l = 1; l < M - 1; l++)
            {
                for (int m = 1; m < N - 1; m++)
                {
                    // finding no Of Neighbours that are alive
                    int aliveNeighbours = 0;
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            aliveNeighbours += grid[l + i][m + j];

                    // The cell needs to be subtracted from
                    // its neighbours as it was counted before
                    aliveNeighbours -= grid[l][m];

                    // Implementing the Rules of Life

                    // Cell is lonely and dies
                    if ((grid[l][m] == 1) && (aliveNeighbours < 2))
                        future[l][m] = 0;

                        // Cell dies due to over population
                    else if ((grid[l][m] == 1) && (aliveNeighbours > 3))
                        future[l][m] = 0;

                        // A new cell is born
                    else if ((grid[l][m] == 0) && (aliveNeighbours == 3))
                        future[l][m] = 1;

                        // Remains the same
                    else
                        future[l][m] = grid[l][m];
                }
            }

            System.out.println("Next Generation");
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if (future[i][j] == 0)
                        System.out.print(".");
                    else
                        System.out.print("*");
                }
                System.out.println();
            }
        }
    }

*/
