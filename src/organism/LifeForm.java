package organism;

import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;
import world.World;

/**
 * Abstract class to represent a LifeForm, i.e. Plant Eaters and Plants in the 
 * Game of Life.
 * 
 * @author Scott Lai
 */
public abstract class LifeForm {

    /** Color of the current LifeForm. */
    protected final Color color;

    /** The Cell that this LifeForm is living on. */
    protected Cell occupiedCell;

    /** Maximum number of turns this LifeForm can take. */
    protected final int maxHP;

    /** Number of turns left before this LifeForm dies. */
    protected int hp;

    /**
     * Construct a LifeForm by defining its {@code Color} and {@code Cell}.
     * 
     * @param color {@code Color} color of this LifeForm
     * 
     * @param spawnCell {@code Cell} the Cell in which this LifeForm spawns on
     */
    public LifeForm(Color color, Cell spawnCell, int maxHP) {
        this.color = color;
        this.occupiedCell = spawnCell;
        hp = this.maxHP = maxHP;
    }

    /**
     * Returns if the instance of a class can be "eaten" by the specified 
     * LifeForm.
     * 
     * @param life {@code LifeForm} to check if this LifeForm can eat
     * 
     * @return {@code true} if the current class can be eaten by the specified 
     * {@code LifeForm}; {@code false} otherwise
     */
    public abstract boolean canEat(LifeForm life);

    /**
     * Returns if this LifeForm can breed with the specified LifeForm.
     * 
     * @param partner {@code LifeForm} potential mating partner
     * 
     * @return {@code true} if this LifeForm can breed with the specified 
     * LifeForm; {@code false} otherwise
     */
    public abstract boolean canBreedWith(LifeForm partner);

    /**
     * Returns if this LifeForm can breed given the specified list of adjacent
     * empty, canBreedWith LifeForm, and Food Cells.
     * 
     * @param empty {@code List<Cell>} List of adjacent empty Cells
     * @param canBreed {@code List<Cell>} List of adjacent Cells that contain
     * LifeForm this LifeForm can breed with
     * 
     * @return the Cell in which the new born is spawned;
     */
    protected abstract boolean breedCondition(List<Cell> empty, 
        List<Cell> canBreed, List<Cell> food);

    /**
     * Spawns a new instance of this type of LifeForm on the specified dest Cell.
     * 
     * @param dest {@code Cell} cell to spawn on
     */
    protected abstract void spawn(Cell dest);

    /**
     * Method for a LifeForm to perform at the start of the turn.
     * A turn consists of first breeding then moving.
     * 
     * @param world {@code World} the World that this LifeForm is living in
     * 
     * @return {@code true} if this LifeForm is able to perform at the start of
     * each turn, i.e. not starved or be able to spread seed; {@code false} 
     * otherwise
     */
    public boolean takeTurn(World world) {
        if (starved()) {
            die();
            return false;
        }

        loseHP();

        List<Cell> breedCells = world.getAdjacentBreedable(occupiedCell);
        List<Cell> emptyCells = world.getAdjacentEmpty(occupiedCell);
        List<Cell> foodCells = world.getAdjacentEdible(occupiedCell);

        breed(emptyCells, breedCells, foodCells);
        
        List<Cell> foodOrEmpty = world.getAdjacentEdibleOrEmpty(occupiedCell);

        move(foodOrEmpty);

        return true;
    }

    /**
     * Breeds and returns if this LifeForm breeds successfully.
     * 
     * @param empty {@code List<Cell>} list of adjacent empty cells
     * @param canBreed {@code List<Cell>} list of adjacent cells with
     * LifeForm that this LifeForm can breed with
     * @param food {@code List<Cell>} list of adjacent cells with 
     * LifeForm that this LifeForm can eat
     * 
     * @return {@code true} if this LifeForm breeds successfully; {@code false}
     * otherwise
     */
    protected boolean breed(List<Cell> empty, List<Cell> canBreed, List<Cell> food) {
        
        if (!breedCondition(empty, canBreed, food))
            return false;
        
        Cell dest = World.randomCell(empty);

        spawn(dest);

        return true;
    }

    /**
     * Moving part of a turn.
     * 
     * @param foodOrEmpty {@code List<Cell>} List of Cells that are empty or 
     * contain LifeForm that this LifeForm can eat
     * 
     * @return {@code true} if this LifeForm moves successfully; {@code false}
     * otherwise
     */
    protected boolean move(List<Cell> foodOrEmpty) {
        if (foodOrEmpty.isEmpty()) {
            return false;
        }

        Cell dest = World.randomCell(foodOrEmpty);

        eat(dest.occupant());
        
        moveTo(dest);

        return true;
    }

    
    /**
     * Eats the specified LifeForm and returns if that LifeForm has been eaten
     * by this LifeForm successfully. 
     * 
     * @param life {@code LifeForm} life to be eaten by this LifeForm
     * 
     * @return {@code true} if the specified LifeForm has been eaten by this
     * LifeForm; {@code false} otherwise
     */
    private boolean eat(LifeForm life) {
        if (!canEat(life)) {
            return false;
        }

        life.die();

        restoreHP();

        return true;    
    }

    /**
     * Helper method to moves this LifeForm to the dest Cell.
     * 
     * @param dest {@code Cell} destination Cell
     */
    private boolean moveTo(Cell dest) {
        if (!dest.isEmpty())
            return false;
        
        dest.setOccupant(this);

        occupiedCell.kill();

        occupiedCell = dest;

        return true;
    }

    /**
     * Gets called when this lifeForm dies.
     */
    private final void die() {
        occupiedCell.kill(); // Destroy this LifeForm reference in Cell
    }

    /**
     * Returns the color of this LifeForm.
     * 
     * @return {@code Color} color of this LifeForm
     */
    public final Color color() {
        return color;
    }

    /**
     * Returns if this Herbivore is starving, i.e. {@code hp == 0}.
     * 
     * @return {@code true} if this herbivore is starving; {@code false} otherwise
     */
    protected final boolean starved() {
        return hp == 0;
    }

    /**
     * Removes 1 hp of this LifeForm.
     */
    private void loseHP() {
        hp--;
    }

    /**
     * Restores this LifeForm's hp to full.
     */
    private void restoreHP() {
        hp = maxHP;
    }

}
