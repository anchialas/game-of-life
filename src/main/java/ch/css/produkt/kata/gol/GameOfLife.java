package ch.css.produkt.kata.gol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameOfLife {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String... args) {
        System.out.println("Hello World!");
    }

    public int[][] calculateNextGeneration(int[][] currentGeneration) {
        int[][] nextGeneration = new int[currentGeneration.length][currentGeneration[0].length];
        for (int x = 0; x < currentGeneration.length; x++) {
            for (int y = 0; y < currentGeneration[x].length; y++) {
                int[][] localSpace = getLocalArray(currentGeneration, x ,y);
                int nextValue = willBeAlive(localSpace) ? 1 :0;
                nextGeneration[x][y] = nextValue;
            }
        }
        return nextGeneration;
    }

    private int[][] getLocalArray(int[][] world, int posX, int posY) {
        int[][] localArr = new int[3][3];
        for (int x = 0; x <= 2; x++) {
            int posXWorld = posX + x -1;
            if (posXWorld >= 0 && posXWorld < world.length) {
                for (int y = 0; y <= 2; y++) {
                    int posYWorld = posY + y -1;
                    if (posYWorld >= 0 && posYWorld < world[posXWorld].length) {
                        localArr[x][y] = world[posXWorld][posXWorld];
                    }
                }
            }
        }
        return localArr;
    }


    boolean willBeAlive(int[][] localSpace) {
        LOG.info("localSpace: {}", toString(localSpace));
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
                sb.append(value == 0 ? "O " : "X ");
            }
        }
        return sb.toString();
    }


}
