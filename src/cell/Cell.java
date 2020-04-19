package cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import organism.LifeForm;
import organism.carnivore.Carnivore;
import organism.herbivore.Herbivore;
import organism.omnivore.Omnivore;
import organism.plant.Plant;
import utility.RandomGenerator;
import utility.Setting;


/**
 * Class to represent a Cell of a world in the Setting of Life.
 */
public abstract class Cell {

    /** Row number of this Cell. */
    protected final int row;

    /** Column number of this Cell. */
    protected final int column;

    /** LifeForm that is currently on this cell. */
    protected LifeForm occupant;

    /** Color of this Cell. */
    protected Color color;

    /** Shape of this Cell. */
    protected Shape shape;

    /**
     * Shape of this Cell. 
     * 
     * @return the shape of this Cell
     */
    protected abstract Shape createShape();
    
    /**
     * Returns a list of coordinate difference that this Cell is adjacent to.
     * 
     * @return {@code int[][2]} adjacency matrix that this Cell is adjacent 
     * to, {@code int[][0]} shows the row difference and {@code int[][1]} shows
     * the column difference
     */
    public abstract int[][] adjacent();


    /**
     * Constructs a Cell object with the specified coordinate.
     * 
     * @param row       {@code int} row 0-based row number, i.e. y-coordinate
     * @param column    {@code int} 0-based column number, i.e. x-coordinate
     */
    Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.occupant = spawn(); // Populate this Cell

        shape = createShape();

        setColor();

        decorate();
    }

    /**
     * Helper method to spawns a random LifeForm on the specified Cell with the
     * the chance of 15% for the Herbivore, 20% for the Plant, and 65% of 
     * nothing getting spawned.
     * 
     * @param cell {@code Cell} Cell for a LifeForm to spawn on
     * 
     * @return {@code LifeForm} a random LifeForm; {@code null} if nothing is 
     * spawned
     */
    private LifeForm spawn() {
        int value = RandomGenerator.next(Setting.SPAWN_VALUE);

        if (value >= Setting.HERBIVORE_VALUE)
            return new Herbivore(this);
        else if (value >= Setting.PLANT_VALUE)
            return new Plant(this);
        else if (value >= Setting.CARNIVORE_VALUE)
            return new Carnivore(this);
        else if (value >= Setting.OMNIVORE_VALUE)
            return new Omnivore(this);
        else
            return null;
    }
    
    /**
     * Helper method to decorates the shape of this Cell.
     */
    protected final void decorate() {
        shape.setStrokeType(StrokeType.INSIDE);
        shape.setStrokeWidth(0.5);
        shape.setStroke(Color.BLACK);
    }

    /**
     * Helper method to set the color of the shape of this Cell.
     */
    protected final void setColor() {
        color = isEmpty() ? Color.WHITE : occupant.color();

        shape.setFill(color);
    }

    /**
     * Returns the row number, i.e. y-coordinate of this Cell.
     * 
     * @return {@code int} the row number of this Cell
     */
    public final int row() {
        return row;
    }

    /**
     * Returns the column number, i.e. x-coordinate of this Cell.
     * 
     * @return {@code int} the column number of this cell
     */
    public final int column() {
        return column;
    }

    /**
     * Returns the LifeForm that is occupying this Cell.
     * 
     * @return {@code LifeForm} the LifeForm that is occupying this cell.
     */
    public final LifeForm occupant() {
        return occupant;
    }

    /**
     * Sets the specified LifeForm to this Cell.
     * 
     * @param newLife {@code LifeForm} LifeForm to occupy this Cell
     */
    public final void setOccupant(LifeForm newLife) {

        if (!isEmpty())
            throw new IllegalArgumentException("Cell is not empty!");
            
        occupant = newLife;

        setColor();
    }

    /**
     * Returns if this Cell is an empty Cell.
     * 
     * @return {@code true} if this Cell is an empty cell; {@code false} 
     *         otherwise
     */
    public final boolean isEmpty() {
        return occupant == null;
    }

    /**
     * Kills the LifeForm on this Cell.
     */
    public final void kill() {
        occupant = null;

        setColor();
    }

    /**
     * Returns the shape of this Cell.
     * 
     * @return {@code Shape} shape of this Cell
     */
    public final Shape shape() {
        return this.shape;
    }

    /**
     * Returns if the LifeForm that is occupying this Cell can eat the LifeForm
     * that is occupying on the specified Cell.
     * 
     * @param dest {@code Cell} the Cell to check if the LifeForm on this Cell
     * can eat the LifeForm
     * 
     * @return {@code true} if the LifeForm that is occupying this Cell can eat 
     * the LifeForm that is occupying on the specified Cell; {@code false} 
     * otherwise
     */
    public final boolean canEat(Cell dest) {
        return this.occupant.canEat(dest.occupant);
    }

    /**
     * Returns if the LifeForm that is occupying this Cell can breed with the
     * LifeForm that is occupying on the specified Cell.
     * 
     * @param dest {@code Cell} the Cell to check if the LifeForm on this Cell
     * can breed with the LifeForm
     * 
     * @return {@code true} if the LifeForm that is occupying this Cell can 
     * breed with the LifeForm that is occupying on the specified Cell; 
     * {@code false} otherwise
     */
    public final boolean canBreed(Cell dest) {
        return this.occupant.canBreedWith(dest.occupant);
    }

}
