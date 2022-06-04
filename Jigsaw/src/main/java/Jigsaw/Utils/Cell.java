package Jigsaw.Utils;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * A cell class to generate the figures.
 */
public class Cell {
    /**
     * A color example.
     */
    Color color;

    public Cell() {
        cell = new boolean[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cell[i][j] = false;
            }
        }
        generator();
    }

    /**
     * Cells array.
     */
    boolean[][] cell;

    /**
     * The color getter.
     *
     * @return Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * The array setter.
     *
     * @return Array of cells.
     */
    public boolean[][] getCell() {
        return cell;
    }

    /**
     * Generates the field.
     */
    void generator() {
        Random random = new Random();
        int col = random.nextInt(4);
        switch (col) {
            case 0 -> color = Color.CADETBLUE;
            case 1 -> color = Color.DARKSEAGREEN;
            case 2 -> color = Color.DEEPPINK;
            case 3 -> color = Color.ORANGE;
            default -> color = Color.CORAL;
        }
        int figure = random.nextInt(9) + 1;
        switch (figure) {
            case 1 -> cell[1][1] = true;
            case 2 -> generateLine();
            case 3 -> generateLittleL();
            case 4 -> generateLittleT();
            case 5 -> generateBigT();
            case 6 -> generateBigL();
            case 7 -> generateZ();
            case 8 -> generateRusGFirst();
            case 9 -> generateRusGSecond();
        }
    }

    /**
     * Generates a line.
     */
    void generateLine() {
        Random random = new Random();
        int direction = random.nextInt(2);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[2][1] = true;
            }
            case 1 -> {
                cell[1][0] = true;
                cell[1][1] = true;
                cell[1][2] = true;
            }
        }
    }

    /**
     * Generates a Russian G-letter analogy that looks in another direction.
     */
    void generateRusGSecond() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[2][1] = true;
                cell[0][0] = true;
            }
            case 1 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[2][1] = true;
                cell[2][2] = true;
            }
            case 2 -> {
                cell[1][2] = true;
                cell[1][1] = true;
                cell[1][0] = true;
                cell[0][2] = true;
            }
            case 3 -> {
                cell[1][2] = true;
                cell[1][1] = true;
                cell[1][0] = true;
                cell[2][0] = true;
            }
        }
    }

    /**
     * Generates a Russian G-letter analogy.
     */
    void generateRusGFirst() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[2][1] = true;
                cell[0][2] = true;
            }
            case 1 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[2][1] = true;
                cell[2][0] = true;
            }
            case 2 -> {
                cell[1][2] = true;
                cell[1][1] = true;
                cell[1][0] = true;
                cell[0][0] = true;
            }
            case 3 -> {
                cell[1][2] = true;
                cell[1][1] = true;
                cell[1][0] = true;
                cell[2][2] = true;
            }
        }
    }

    /**
     * Generates a Z-like figure.
     */
    void generateZ() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[2][0] = true;
                cell[2][1] = true;
                cell[1][1] = true;
                cell[1][2] = true;
            }
            case 1 -> {
                cell[2][2] = true;
                cell[2][1] = true;
                cell[1][1] = true;
                cell[1][0] = true;
            }
            case 2 -> {
                cell[2][1] = true;
                cell[1][1] = true;
                cell[1][0] = true;
                cell[0][0] = true;
            }
            case 3 -> {
                cell[2][1] = true;
                cell[1][1] = true;
                cell[1][2] = true;
                cell[0][2] = true;
            }
        }
    }

    /**
     * Generates an L-like figure with 1-brick leg.
     */
    void generateLittleL() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[0][2] = true;
                cell[1][2] = true;
            }
            case 1 -> {
                cell[0][2] = true;
                cell[1][1] = true;
                cell[1][2] = true;
            }
            case 2 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[1][2] = true;
            }
            case 3 -> {
                cell[0][1] = true;
                cell[1][1] = true;
                cell[0][2] = true;
            }
        }
    }

    /**
     * Generates an L-like figure with 2-bricks leg.
     */
    void generateBigL() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][0] = true;
                cell[0][1] = true;
                cell[0][2] = true;
                cell[1][2] = true;
                cell[2][2] = true;
            }
            case 1 -> {
                cell[0][2] = true;
                cell[1][2] = true;
                cell[2][2] = true;
                cell[2][1] = true;
                cell[2][0] = true;
            }
            case 2 -> {
                cell[0][0] = true;
                cell[1][0] = true;
                cell[2][0] = true;
                cell[2][2] = true;
                cell[2][1] = true;
            }
            case 3 -> {
                cell[0][0] = true;
                cell[1][0] = true;
                cell[2][0] = true;
                cell[0][2] = true;
                cell[0][1] = true;
            }
        }
    }

    /**
     * Generates an T-like figure with 1-brick leg.
     */
    void generateLittleT() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[0][2] = true;
                cell[0][0] = true;
                cell[1][1] = true;
            }
            case 1 -> {
                cell[0][2] = true;
                cell[1][2] = true;
                cell[2][2] = true;
                cell[1][1] = true;
            }
            case 2 -> {
                cell[1][1] = true;
                cell[2][0] = true;
                cell[2][1] = true;
                cell[2][2] = true;
            }
            case 3 -> {
                cell[1][1] = true;
                cell[0][0] = true;
                cell[1][0] = true;
                cell[2][0] = true;
            }
        }
    }

    /**
     * Generates an T-like figure with 2-bricks leg.
     */
    void generateBigT() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0 -> {
                cell[0][1] = true;
                cell[0][2] = true;
                cell[0][0] = true;
                cell[1][1] = true;
                cell[2][1] = true;
            }
            case 1 -> {
                cell[0][2] = true;
                cell[1][2] = true;
                cell[2][2] = true;
                cell[1][1] = true;
                cell[1][0] = true;
            }
            case 2 -> {
                cell[1][1] = true;
                cell[2][0] = true;
                cell[2][1] = true;
                cell[2][2] = true;
                cell[0][1] = true;
            }
            case 3 -> {
                cell[1][1] = true;
                cell[0][0] = true;
                cell[1][0] = true;
                cell[2][0] = true;
                cell[1][2] = true;
            }
        }
    }
}
