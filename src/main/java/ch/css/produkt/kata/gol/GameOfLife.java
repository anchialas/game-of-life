package ch.css.produkt.kata.gol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GameOfLife {
    private static final Logger LOG = LogManager.getLogger();

    private final Scanner consoleInput;

    public static void main(String... args) {
        int[][] initialGeneration = getInitialGeneration();
        GameOfLife gol = new GameOfLife();
        gol.runProgram(initialGeneration);
    }

    private GameOfLife() {
        consoleInput = new Scanner(System.in);
    }

    private void runProgram(int[][] initialGeneration) {
        writeOutput(initialGeneration);

        int[][] currentGeneration = initialGeneration;
        while (!"q".equalsIgnoreCase(readInput())) {
            clearOutput();
            int[][] nextGeneration = calculateNextGeneration(currentGeneration);
            writeOutput(nextGeneration);
            currentGeneration = nextGeneration;
        }
    }

    private String readInput() {
        return consoleInput.next();
    }

    private void writeOutput(String text) {
        System.out.println(text);
    }

    private void writeOutput(int[][] arr) {
        writeOutput(toString(arr));
    }

    private void clearOutput() {
        writeOutput("\\033[H\\033[2J");
    }
    
    private static int[][] getInitialGeneration() {
        return new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
    }

    public int[][] calculateNextGeneration(int[][] currentGeneration) {
        int[][] nextGeneration = new int[currentGeneration.length][currentGeneration[0].length];
        for (int x = 0; x < currentGeneration.length; x++) {
            for (int y = 0; y < currentGeneration[x].length; y++) {
                LOG.info("getLocalArray({},{})", x, y);
                int[][] localSpace = getLocalArray(currentGeneration, x, y);
                int nextValue = willBeAlive(localSpace) ? 1 : 0;
                LOG.info("nextValue: {}", nextValue);
                nextGeneration[x][y] = nextValue;
            }
        }
        return nextGeneration;
    }

    private int[][] getLocalArray(int[][] world, int posX, int posY) {
        int[][] localArr = new int[3][3];
        for (int x = 0; x <= 2; x++) {
            int posXWorld = posX + x - 1;
            if (posXWorld >= 0 && posXWorld < world.length) {
                for (int y = 0; y <= 2; y++) {
                    int posYWorld = posY + y - 1;
                    if (posYWorld >= 0 && posYWorld < world[posXWorld].length) {
                        localArr[x][y] = world[posXWorld][posYWorld];
                    }
                }
            }
        }
        LOG.info("calculated LocalArray: {}", toString(localArr));
        return localArr;
    }


    boolean willBeAlive(int[][] localSpace) {
        int numberOfAliveNeighbours = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if ((x != 1 || y != 1) && localSpace[x][y] != 0) {
                    ++numberOfAliveNeighbours;
                }
            }
        }

        if (localSpace[1][1] == 0) {
            return numberOfAliveNeighbours == 3;
        }

        return numberOfAliveNeighbours >= 2 && numberOfAliveNeighbours < 4;
    }

    public String toString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] xArr : arr) {
            sb.append('\n');
            for (int value : xArr) {
                sb.append(value == 0 ? "· " : "# ");
            }
        }
        return sb.toString();
    }


}
