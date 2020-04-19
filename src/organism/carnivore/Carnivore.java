package organism.carnivore;

import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;
import organism.LifeForm;
import organism.omnivore.OmnivoreEdible;

/**
 * Class to represent a Carnivore in the game of life.
 * 
 * @author Scott Lai
 */
public class Carnivore extends LifeForm implements OmnivoreEdible {

    /** Maximum number of turns a Herbivore can take without eating. */
    private static final int MAX_HP = 5;

    /**
     * Constructs a Carnivore.
     * 
     * @param spawnCell {@code Cell} the Cell in which this Carnivore spawns on.
     */
    public Carnivore(Cell spawnCell) {
        super(Color.RED, spawnCell, MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm life) {
        return life instanceof CarnivoreEdible;
    }

    @Override
    public boolean canBreedWith(LifeForm partner) {
        return partner instanceof Carnivore;
    }

    @Override
    protected boolean breedCondition(List<Cell> empty, List<Cell> canBreed,
        List<Cell> food) {

        return canBreed.size() >= 1 && empty.size() >= 3 && food.size() == 2;
    }

    @Override
    protected void spawn(Cell dest) {
        dest.setOccupant(new Carnivore(dest));
    }

}
