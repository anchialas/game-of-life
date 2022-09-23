package ch.css.produkt.kata.gol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private GameOfLife testee;

    @BeforeEach
    void setUp() {
        testee = new GameOfLife();
    }

    @Test
    void willBeAlive_tc1() {
        int[][] world = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        boolean result = testee.willBeAlive(world);

        assertFalse(result);
    }

    @Test
    void willBeAlive_tc2() {
        int[][] world = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        boolean result = testee.willBeAlive(world);

        assertTrue(result);
    }

    @Test
    void willBeAlive_overpopulation_death() {
        int[][] world = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };

        boolean result = testee.willBeAlive(world);

        assertFalse(result);
    }
}