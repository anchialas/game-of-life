package ch.css.produkt.kata.gol;

public class GameOfLife {

    public static void main(String... args) {
        System.out.println("Hello World!");
    }

    public boolean willBeAlive(int[][] localSpace) {
        int numberOfAliveNeighbours = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if ((x != 1 || y != 1) && localSpace[x][y] != 0) {
                    ++numberOfAliveNeighbours;
                }
            }
        }

        return numberOfAliveNeighbours >= 2 && numberOfAliveNeighbours < 4;
    }

}
