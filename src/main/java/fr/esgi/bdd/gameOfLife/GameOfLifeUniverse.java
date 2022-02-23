/*
*
* rules of Conway's Game of Life
* The universe of the _Game of Life_ is an infinite, two-dimensional orthogonal grid of square cells.
* Each cell is in one of two possible states:
* Alive aka populated
* Dead aka unpopulated
* Every cell interacts with its eight neighbors.
* The neighbors are the cells that are horizontally, vertically, or diagonally adjacent.
* At each step in time, the following transitions occur:
* 1. Underpopulation: Any live cell with fewer than 2 live neighbors dies.
* 1. Survival: Any live cell with 2 or 3 live neighbors survives on to the next generation.
* 1. Overpopulation Any live cell with more than 3 live neighbors dies.
* 1. Reproduction (birth): Any dead cell with exactly 3 live neighbors becomes a live cell.
*
*/


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






















