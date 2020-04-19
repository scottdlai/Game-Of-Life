package cell;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import utility.Setting;

/**
 * Class to represent a Square Cell in the Game of Life.
 * 
 * @author Scott Lai
 */
public class Square extends Cell {
    
    /**
     * Constructs a Square Cell object with the specified coordinate.
     * 
     * @param row       {@code int} row 0-based row number, i.e. y-coordinate
     * @param column    {@code int} 0-based column number, i.e. x-coordinate
     */
    Square(int row, int column) {
        super(row, column);
    }

    @Override
    protected Shape createShape() {
        double x = column * Setting.SQUARE_SIZE;
        double y = row * Setting.SQUARE_SIZE;

        return new Rectangle(x, y, Setting.SQUARE_SIZE, Setting.SQUARE_SIZE);
    }

    @Override
    public int[][] adjacent() {
        int[][] temp = new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        return temp;
    }

}