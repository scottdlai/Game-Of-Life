package utility;

/**
 * Classes to represent this Game of Life's settings. It includes several useful
 * constants including the Window's height and width as well as percentage of 
 * each LifeForm in this Game.
 * 
 * @author Scott Lai
 */
public class Setting {
    
    /** Percentage of Herbivore in this world. */
    private static final int HERBIVORE_PERCENT = 20; // 20

    /** Percentage of Plant in this world. */
    private static final int PLANT_PERCENT = 20; // 20

    /** Percentage of Carnivore in this world. */
    private static final int CARNIVORE_PERCENT = 10; // 10

    /** Percentage of Omnivore in this world. */
    private static final int OMNIVORE_PERCENT = 5; // 5

    /** Total value to generate LifeForm. */
    public static final int SPAWN_VALUE = 100;

    /** Spawn value of Herbivore. */
    public static final int HERBIVORE_VALUE = SPAWN_VALUE - HERBIVORE_PERCENT;

    /** Spawn value of Plant. */
    public static final int PLANT_VALUE = HERBIVORE_VALUE - PLANT_PERCENT;

    /** Spawn value of a Carnivore. */
    public static final int CARNIVORE_VALUE = PLANT_VALUE - CARNIVORE_PERCENT;

    /** Spawn value of an Omnivore. */
    public static final int OMNIVORE_VALUE = CARNIVORE_VALUE - OMNIVORE_PERCENT;

    /** Width of a square Cell. */
    public static final int SQUARE_SIZE = 20;

    /** Window's Width of this Game of Life. */
    public static final int WINDOW_WIDTH = 800;

    /** Window's Height of this Game of Life. */
    public static final int WINDOW_HEIGHT = 600;

    /** Number of Rows in the square world of this Game of Life. */
    public static final int SQUARE_ROW = WINDOW_HEIGHT / SQUARE_SIZE;

    /** Number of Columns in the square world of this Game of Life. */
    public static final int SQUARE_COLUMN = WINDOW_WIDTH / SQUARE_SIZE;

    /** Radius from the center of the Hex to the top pointy vertices. */
    public static final double R = 20;

    /** Radius from the center of the Hex to the edge on the side (left or right). */
    public static final double N = Math.sqrt(R * R * 0.75);

    /** Width of the Hexagon. */
    public static final double HEX_WIDTH = 2 * N;

    /** Height of the Hexagon. */
    public static final double HEX_HEIGHT = 2 * R;

    /** Offset to display the hex world. */
    public static final double X_OFFSET = 30;

    /** Offset to display the  */
    public static final double Y_OFFSET = 45;

    /** Number of rows of hex cells. */
    public static final int HEX_ROW = WINDOW_HEIGHT / (int) HEX_WIDTH;

    /** Number of columns in each row of hex cells. */
    public static final int HEX_COLUMN = WINDOW_WIDTH / (int) HEX_WIDTH - 2;

    /**
     * Ensures that a Setting Object cannot be created.
     */
    private Setting() {}

}
