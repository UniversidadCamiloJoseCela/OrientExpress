package world.map;

import static world.map.CellType.*;

/**
 * Tipos de vagón disponibles y sus layouts por defecto.
 */
public enum CarriageType {
    VIEWPOINT(createBathroomMatrix()),
    BAGGAGE(createKitchenMatrix()),
    DINNER(createBathroomMatrix()),
    LOUNGE(createBathroomMatrix()),
    BEDROOM(createBathroomMatrix()),
    BATHROOM(createBathroomMatrix()),
    KITCHEN(createKitchenMatrix()),
    COUL(createKitchenMatrix()),
    LOCOMOTIVE(createBathroomMatrix());

    private final CellType[][] matrix;

    CarriageType(CellType[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Devuelve una copia del layout asociado a este tipo de vagón (3×7).
     */
    public CellType[][] getMatrix() {
        CellType[][] copy = new CellType[CarriageLayout.ROWS][CarriageLayout.COLS];
        for (int r = 0; r < CarriageLayout.ROWS; r++) {
            System.arraycopy(matrix[r], 0, copy[r], 0, CarriageLayout.COLS);
        }
        return copy;
    }

    private static CellType[][] createBathroomMatrix() {
        return new CellType[][]{
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR,  FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createDiningMatrix() {
        return new CellType[][]{
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR,  FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createKitchenMatrix() {
        return new CellType[][]{
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, TABLE, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createSleeperMatrix() {
        return new CellType[][]{
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, TABLE, TABLE, TABLE, FLOOR, TABLE, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR}
        };
    }
}

