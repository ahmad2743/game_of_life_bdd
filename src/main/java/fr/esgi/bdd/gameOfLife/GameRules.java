package fr.esgi.bdd.gameOfLife;

import java.util.Set;

import static java.util.Set.of;

    @SuppressWarnings("squid:S1214") // Rules is the best place for the ConwayRules constant.
    public interface GameRules {
        GameRules CONWAY_RULES = gameRules(of(2, 3), of(3));

        static GameRules gameRules(final Set<Integer> liveNeighborsForSurvival, final Set<Integer> liveNeighborsForBirth) {
            return new DefaultRules(liveNeighborsForSurvival, liveNeighborsForBirth);
        }

        boolean survives(int liveNeighbors);

        boolean born(int liveNeighbors);
    }

