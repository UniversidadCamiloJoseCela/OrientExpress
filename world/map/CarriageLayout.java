package world.map;

public class CarriageLayout {

    public static final int ROWS = 3;
    public static final int COLS = 7;

    private final InteriorCell[][] grid;

    /**
     * Crea un layout 3×7 inicializado a FLOOR sin NPCs ni pistas.
     */
    public CarriageLayout() {
        grid = new InteriorCell[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = new InteriorCell(CellType.FLOOR);
            }
        }
    }

    /**
     * Crea un layout 3×7 a partir de una matriz de CellType.
     * Valida que el tamaño sea exacto.
     */
    public CarriageLayout(CellType[][] types) {
        if (types.length != ROWS || types[0].length != COLS) {
            throw new IllegalArgumentException(
                    STR."El layout debe tener tamaño \{ROWS}x\{COLS}"
            );
        }
        grid = new InteriorCell[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = new InteriorCell(types[r][c]);
            }
        }
    }

    /**
     * Devuelve la celda interior en la posición (fila, columna).
     */
    public InteriorCell cellAt(int row, int col) {
        return grid[row][col];
    }

}
