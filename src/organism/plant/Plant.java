package organism.plant;

import java.util.List;

import cell.Cell;
import javafx.scene.paint.Color;
import organism.LifeForm;
import organism.herbivore.HerbivoreEdible;
import organism.omnivore.OmnivoreEdible;

/**
 * Class to represent a Plant in the Game of Life.
 * 
 * @author Scott Lai
 */
public class Plant extends LifeForm implements HerbivoreEdible, OmnivoreEdible {

    /** Maximum number of turns a Plant can take without eating. */
    private static final int MAX_HP = Integer.MAX_VALUE;

    /**
     * Constructs a Plant.
     * 
     * @param spawnCell {@code Cell} the Cell in which this Plant spawns on
     */
    public Plant(Cell spawnCell) {
        super(Color.GREEN, spawnCell, MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm life) {
        return false;
    }

    @Override
    protected void spawn(Cell dest) {
        dest.setOccupant(new Plant(dest));
    }

    @Override
    /**
     * A plant cannot move.
     * 
     * @return {@code false}
     */
    protected boolean move(List<Cell> edibleEmptyCells) {
        return false;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    protected boolean breedCondition(List<Cell> empty, List<Cell> canBreed,
        List<Cell> food) {
        
        return  canBreed.size() >= 2 && empty.size() >= 3 && food.size() == 0;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean canBreedWith(LifeForm partner) {
        return partner instanceof Plant;
    }
    
}
