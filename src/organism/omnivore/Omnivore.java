package organism.omnivore;

import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;
import organism.LifeForm;
import organism.carnivore.CarnivoreEdible;

/**
 * Class to represent an Omnivore in the Game of Life.
 * 
 * @author Scott Lai
 */
public class Omnivore extends LifeForm implements CarnivoreEdible {

    /** Maximum number of turns a Herbivore can take without eating. */
    private static final int MAX_HP = 5;

    /**
     * Constructs an Omnivore.
     * 
     * @param spawnCell {@code Cell} the Cell in which this Omnivore spawns on.
     */
    public Omnivore(Cell spawnCell) {
        super(Color.BLUE, spawnCell, MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm life) {
        return life instanceof OmnivoreEdible;
    }

    @Override
    public boolean canBreedWith(LifeForm partner) {
        return partner instanceof Omnivore;
    }

    @Override
    protected boolean breedCondition(List<Cell> empty, List<Cell> canBreed,
        List<Cell> food) {
        
        return canBreed.size() >= 1 && empty.size() >= 3 && food.size() == 1;
    }

    @Override
    protected void spawn(Cell dest) {
        dest.setOccupant(new Omnivore(dest));
    }

}
