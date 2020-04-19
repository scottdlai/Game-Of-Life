package world;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import cell.Cell;
import cell.CellFactory;
import javafx.scene.layout.AnchorPane;
import organism.LifeForm;
import utility.RandomGenerator;

/**
 * Class to represent a World of the Game of Life.
 * @author Scott Lai
 */
public class World {
    
    /** The world that holds the Cells. */
    protected Cell[][] world;

    /** GridPane to display the world to the screen. */
    protected AnchorPane root;

    /**
     * Helper function to initialize the world.
     * 
     * @param rowNumber {@code int} number of Cells in this world
     */
    private static Cell[][] initworld(CellFactory factory) {

        Cell[][] temp = new Cell[factory.row()][factory.column()];

        RandomGenerator.reset();

        for (int r = 0; r < temp.length; r++) {
            for (int c = 0; c < temp[r].length; c++) {
                temp[r][c] = factory.makeCell(r, c);
            }
        }

        // Reset RandomGenerator
        RandomGenerator.reset();

        return temp;
    }

    /**
     * Returns a random Cell in the specified List of Cells.
     * 
     * @param cells {@code List<Cell>} list of Cells
     * 
     * @return a random Cell in the List
     */
    public static Cell randomCell(List<Cell> cells) {
        return cells.get(RandomGenerator.next(cells.size()));
    }

    /**
     * Constructs a world object.
     * 
     * @param size {@code int} number of rows in this world
     * @param size {@code int} number of columns in this world
     */
    public World(CellFactory factory) {
        root = new AnchorPane();

        world = initworld(factory);

        for (Cell[] row : world)
            for (Cell cell : row)
                root.getChildren().add(cell.shape());
    }

    /**
     * Simulates a day in this World.
     */
    public final void simulate() {
        List<LifeForm> lifes = new ArrayList<LifeForm>();

        for (Cell[] row : world)
            for (Cell cell : row)
                if (!cell.isEmpty())
                    lifes.add(cell.occupant());

        for (LifeForm organism : lifes)
            organism.takeTurn(World.this);
    }

    /**
     * Returns this World's representation as a 2-D array of Cell.
     * 
     * @return {@code Cell[][]} this World's representation as a 2-D of Cell
     */
    public final Cell[][] world() {
        return world;
    }

    /**
     * Returns if the specified coordinate is within the boundary of this world.
     * 
     * @param row {@code int} 0-based row number, i.e. x-coordinate
     * @param column {@code int} 0-based column number, i.e. y-coordinate
     * 
     * @return {@code true} if the specified coordinate is within the boundary
     * of this world; {@code false} otherwise 
     */
    private boolean inBoundary(int row, int column) {
        return row >= 0 && row < world.length 
            && column >= 0 && column < world[row].length;
    }

    /**
     * Returns the GridPane of this World.
     * 
     * @return {@code GridPane} root of this World 
     */
    public final AnchorPane root() {
        return root;
    }

    /**
     * Returns a List of surrounding cells of the specified Cell that are 
     * occupied by a LifeForm that can be eaten by the specified Cell's LifeForm
     * or are empty.
     * 
     * @param cell {@code Cell} to find surrouding Cells
     * 
     * @return {@code List<Cell>} list of surrounding cells that are occupied by
     * a LifeForm that can be eaten by the specified eater or are empty
     */
    public final List<Cell> getAdjacentEdibleOrEmpty(Cell cell) {
        return getAdjacentCells(cell, t -> t.isEmpty() || cell.canEat(t));
    }

    /**
     * Returns a List of surrounding cells of the specified Cell that are 
     * occupied by a LifeForm that can be eaten by the specified Cell's 
     * LifeForm.
     * 
     * @param cell {@code Cell} to find surrounding Cells
     * 
     * @return {@code List<Cell>} list of surrounding cells that are occupied by
     * a LifeForm that can be eaten by the specified eater or are empty
     */
    public final List<Cell> getAdjacentEdible(Cell cell) {
        return getAdjacentCells(cell, t -> cell.canEat(t));
    }

    /**
     * Returns a List of surrounding cells that are empty.
     * 
     * @param cell {@code Cell} to find surrouding Cells
     * 
     * @return {@code List<Cell>} list of surrounding cells that are empty
     */
    public final List<Cell> getAdjacentEmpty(Cell cell) {

        return getAdjacentCells(cell, t -> t.isEmpty());
    }

    /**
     * Returns a List of surrounding cells that are occupied by a LifeForm that
     * can breed with the LifeForm that are occupying the specified Cell.
     * 
     * @param cell {@code Cell} to find surrouding Cells
     * 
     * @return {@code List<Cell>} list of surrounding cells that are occupied by
     * a LifeForm that can breed with the LifeForm that are occupying the 
     * specified Cell.
     */
    public final List<Cell> getAdjacentBreedable(Cell cell) {

        return getAdjacentCells(cell, t -> cell.canBreed(t));
    }
    
    /**
     * Returns a List of adjacent Cells to the specified Cell with the specified
     * condition.
     * 
     * @param cell {@code Cell} the Cell to find adjacent Cells
     * @param condition {@code Predicate<Cell>} the condition
     * 
     * @return {@code List<Cell>} list of adjacent Cells with the specified
     * condition
     */
    protected List<Cell> getAdjacentCells(Cell cell, Predicate<Cell> condition) {
        
        List<Cell> surround = new ArrayList<Cell>();
            
        int row = cell.row();
        int column = cell.column();

        int[][] adjacentMatrix = cell.adjacent();

        for (int[] point : adjacentMatrix) {
            if (inBoundary(row + point[0], column + point[1])) {

                Cell temp = world[row + point[0]][column + point[1]];

                if (condition.test(temp))
                    surround.add(temp);
            }
        }

        return surround;
    }

}
