package cell;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import utility.Setting;

/**
 * Class to represent a Hexagonal Cell inside the Game of Life.
 * 
 * @author Scott Lai
 */
public class Hex extends Cell {
    
    /**
     * Constructs a Hexagonal Cell object with the specified coordinate.
     * 
     * @param row       {@code int} row 0-based row number, i.e. y-coordinate
     * @param column    {@code int} 0-based column number, i.e. x-coordinate
     */
    Hex(int row, int column) {
        super(row, column);
    }

    @Override
    protected Shape createShape() {
        Polygon temp = new Polygon();
        double x = column * Setting.HEX_WIDTH + (row % 2) * Setting.N + Setting.X_OFFSET;
        double y = row * Setting.HEX_HEIGHT * 0.75 + Setting.Y_OFFSET;
        
        temp.getPoints().addAll(
            x, y,
            x, y + Setting.R,
            x + Setting.N, y + Setting.R * 1.5,
            x + Setting.HEX_WIDTH, y + Setting.R,
            x + Setting.HEX_WIDTH, y,
            x + Setting.N, y - Setting.R * 0.5
        );

        return temp;
    }

    @Override
    public int[][] adjacent() {
        
        int[][] temp = row % 2 == 1 ? 
                        new int[][] {
                            {-1, 0}, {-1, 1},
                            {0, -1}, {0, 1},
                            {1, 0}, {1, 1}
                        } : 
                        new int[][] {
                            {-1, -1}, {-1, 0},
                            {0, -1}, {0, 1},
                            {1, -1}, {1, 0} 
                        };

        return temp;
    }

}