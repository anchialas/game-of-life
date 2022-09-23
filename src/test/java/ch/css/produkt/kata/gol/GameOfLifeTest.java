package ch.css.produkt.kata.gol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private GameOfLife testee;

    @BeforeEach
    void setUp() {
        testee = new GameOfLife();
    }

    private static List<int[][]> provideFriendlySituations() {
        int[][] a = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] b = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] c = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        int[][] d = {
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };
        int[][] e = {
                {0, 1, 1},
                {0, 0, 0},
                {0, 1, 0}
        };
        int[][] f = {
                {0, 1, 1},
                {0, 1, 0},
                {0, 1, 0}
        };
        return List.of(a, b, c, d, e, f);
    }

    @ParameterizedTest
    @MethodSource("provideFriendlySituations")
    void willBeAlive_friendlySituation_true(int[][] world) {
        boolean result = testee.willBeAlive(world);

        assertTrue(result);
    }

    private static List<int[][]> provideDeadlySituations() {
        int[][] a = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] b = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] c = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        int[][] d = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int[][] e = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 1, 0}
        };
        return List.of(a, b, c, d, e);
    }

    @ParameterizedTest
    @MethodSource("provideDeadlySituations")
    void willBeAlive_deadlySituation_false(int[][] world) {
        boolean result = testee.willBeAlive(world);

        assertFalse(result);
    }
}