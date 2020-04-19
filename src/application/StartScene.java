package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utility.Setting;

/**
 * Class to represent a Starting screen.
 * 
 * @author Scott Lai
 */
public class StartScene {

    /** Instruction for Square. */
    private Text text1 = new Text("PRESS 1 FOR SQUARE");

    /** Instruction for Hex */
    private Text text2 = new Text("PRESS 2 FOR HEX");

    /** Vbox to group the text together. */
    private VBox box = new VBox();

    /** Content for the Scene. */
    private Scene content;

    /**
     * Constructs a Starting Scene.
     */
    public StartScene() {
        text1.setFont(new Font(32));
        text2.setFont(new Font(32));

        box.getChildren().addAll(text1, text2);
        box.setSpacing(10);

        content = new Scene(box, Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);
    }

    /**
     * Returns the content of this Start Scene.
     * @return
     */
    public Scene content() {
        return content;
    }
}