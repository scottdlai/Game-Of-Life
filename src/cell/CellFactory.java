package cell;

import utility.Setting;

/**
 * Factory to abstract the process of making Cells.
 */
public final class CellFactory {

    /** The mode to know which cells to create. */
    private String mode;

    /** Number of rows. */
    private int rowNumber;

    /** Number of columns in each row. */
    private int columnNumber;

    /**
     * Constructs a Cell Factory by defining its mode.
     * @param mode
     */
    public CellFactory(String mode) {
        this.mode = mode;

        setNumber(this.mode);
    }

    /**
     * Change the current mode of this factory to the specified mode.
     * 
     * @param mode {@code String} new mode
     * 
     * @return the new Mode
     */
    public String setMode(String mode) {
        if (!(mode.equalsIgnoreCase("SQUARE") || mode.equalsIgnoreCase("HEX")))
            throw new IllegalArgumentException("Invalid mode");
        
        this.mode = mode;

        setNumber(mode);

        return mode;
    }

    /**
     * Set the number of row and column based on the mode.
     * 
     * @param mode {@code String}  
     */
    private void setNumber(String mode) {
        if (mode.equalsIgnoreCase("SQUARE")) {
            rowNumber = Setting.SQUARE_ROW;
            columnNumber = Setting.SQUARE_COLUMN;
        } else if (mode.equalsIgnoreCase("HEX")) {
            rowNumber = Setting.HEX_ROW;
            columnNumber = Setting.HEX_COLUMN;
        }
    }

    /**
     * Returns a new Cell with the specified row and column values according to
     * this factory's current mode.
     * 
     * @param row {@code int} row value, i.e. y-coordinate
     * @param column {@code int} column value, i.e. x-coordinate
     * 
     * @return a new Cell with the specified row and column value; {@code null}
     * if the mode is invalid
     */
    public Cell makeCell(int row, int column) {

        if (mode.equalsIgnoreCase("SQUARE"))
            return new Square(row, column);
        else if (mode.equalsIgnoreCase("HEX"))
            return new Hex(row, column);
        
        return null;
    }

    /**
     * Returns the number of rows this factory can create.
     */
    public int row() {
        return rowNumber;
    }

    /**
     * Returns the number of columns of Cells this factory can create.
     */
    public int column() {
        return columnNumber;
    }
}