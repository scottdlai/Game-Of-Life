package organism.herbivore;

import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;
import organism.LifeForm;
import organism.carnivore.CarnivoreEdible;
import organism.omnivore.OmnivoreEdible;

/**
 * Class to represent a Plant Eater Object in the Game of Life.
 * 
 * @author Scott Lai
 */
public class Herbivore extends LifeForm implements OmnivoreEdible, CarnivoreEdible {

    /** Maximum number of turns a Herbivore can take without eating. */
    private static final int MAX_HP = 5;

    /**
     * Construct a Herbivore.
     * 
     * @param spawnCell {@code Cell} the Cell in which this Herbivore spawns on
     */
    public Herbivore(Cell spawnCell) {
        super(Color.YELLOW, spawnCell, MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm life) {
        return life instanceof HerbivoreEdible;
    }

    @Override
    protected boolean breedCondition(List<Cell> empty, List<Cell> canBreed, List<Cell> food) {
        return canBreed.size() >= 1 && empty.size() >= 2 && food.size() >= 2;
    }

    @Override
    public boolean canBreedWith(LifeForm partner) {
        return partner instanceof Herbivore;
    }

    @Override
    protected void spawn(Cell dest) {
        dest.setOccupant(new Herbivore(dest));
    }

}
