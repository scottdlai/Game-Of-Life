package application;

import cell.CellFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Main Class to launch this Application.
 * 
 * @author Scott Lai
 */
public class Main extends Application {

	/** Game of Life object. */
	private static Game game;

	/** Factory to make Cell. */
	private static CellFactory factory = new CellFactory("SQUARE");

	/** Content of the start scene. */
	private static Scene content;
	
	@Override
	public void start(Stage window) {

		content = new StartScene().content();
		
		window.setTitle("Game of Life");

		window.setScene(content);

		window.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			String input = event.getText();

			if (input.equalsIgnoreCase("1")) {
				factory.setMode("SQUARE");
			} else if (input.equalsIgnoreCase("2")) {
				factory.setMode("HEX");
			} else {
				return;
			}
			game = new Game(factory);
			content = game.content();
			window.setScene(content);
		});

		window.show();
	}
		
	/**
	 * Drives the program.
	 * @param args unused
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
