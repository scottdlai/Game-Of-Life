package application;

import cell.CellFactory;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utility.Setting;
import world.World;

/**
 * Class to represent a Game of Life.
 * 
 * @author Scott Lai
 */
public class Game {

    /** Current World of this Game of Life. */
    private World world;

    /** Content on the screen of this game */
    private Scene content;

    /**
     * Constructs a Game of Life by creating a World.
     */
    public Game(CellFactory factory) {
        
        world = new World(factory);
        
        content = new Scene(world.root(), Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);

        content.setOnMouseClicked(event -> world.simulate());

        content.setOnKeyPressed(event -> {
            KeyCode input = event.getCode();
            if (input == KeyCode.RIGHT || input == KeyCode.LEFT || 
                input == KeyCode.UP || input == KeyCode.DOWN)
                world.simulate();
        });
    }

    /**
     * Returns the content on the screen of this Game.
     * 
     * @return {@code Scene} the content on the screen
     */
    public Scene content() {
        return content;
    }

}
