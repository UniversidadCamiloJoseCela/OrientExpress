package world.map;

import static world.map.CellType.*;

/**
 * Tipos de vagón disponibles y sus layouts por defecto.
 */
public enum CarriageType {
    VIEWPOINT(createViewpointMatrix()),
    BAGGAGE(createOpenMatrix()),
    DINNER(createDiningMatrix()),
    LOUNGE(createLoungeMatrix()),
    BEDROOM(createSleeperMatrix()),
    BATHROOM(createBathroomMatrix()),
    KITCHEN(createKitchenMatrix()),
    COULOIR(createCouloirMatrix()),
    LOCOMOTIVE(createLocomotiveMatrix());

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

    private static CellType[][] createViewpointMatrix() {
        // Vista panorámica: asientos (sin ventanas, todas son suelo)
        return new CellType[][] {
                {FLOOR, FLOOR, SEAT, SEAT, SEAT, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, SEAT, SEAT, SEAT, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createOpenMatrix() {
        // Espacio abierto (bodega o pasillo)
        return new CellType[][] {
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createDiningMatrix() {
        // Comedor: varias mesas
        return new CellType[][] {
                {FLOOR, TABLE, FLOOR, TABLE, FLOOR, TABLE, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, TABLE, FLOOR, TABLE, FLOOR, TABLE, FLOOR}
        };
    }

    private static CellType[][] createLoungeMatrix() {
        // Salón: sillones y mesas bajas
        return new CellType[][] {
                {FLOOR, ARMCHAIR, FLOOR, TABLE, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR,     FLOOR, FLOOR, FLOOR, FLOOR,     FLOOR},
                {FLOOR, ARMCHAIR, FLOOR, TABLE, FLOOR, ARMCHAIR, FLOOR}
        };
    }

    private static CellType[][] createSleeperMatrix() {
        // Camarote: literas
        return new CellType[][] {
                {FLOOR, BED, BED, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createBathroomMatrix() {
        // Baño: lavabo, inodoro y puertas interiores
        return new CellType[][] {
                {FLOOR, SINK,  FLOOR, TOILET, FLOOR, SINK,  FLOOR},
                {FLOOR, FLOOR,  FLOOR, FLOOR,   FLOOR, FLOOR,   FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR,  FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createKitchenMatrix() {
        // Cocina: mesa de preparación y panel de control
        return new CellType[][] {
                {FLOOR, FLOOR, FLOOR, FLOOR,  FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, PANEL,  FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR, FLOOR, PANEL,  FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createCouloirMatrix() {
        // Pasillo interior con puertas
        return new CellType[][] {
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR,  FLOOR, FLOOR,  FLOOR, FLOOR,  FLOOR},
                {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR}
        };
    }

    private static CellType[][] createLocomotiveMatrix() {
        // Locomotora: panel de mandos y puertas
        return new CellType[][] {
                {FLOOR, FLOOR, FLOOR, PANEL, FLOOR, FLOOR, FLOOR},
                {FLOOR, FLOOR,  FLOOR, FLOOR, FLOOR, FLOOR,  FLOOR},
                {FLOOR, FLOOR, FLOOR, PANEL, FLOOR, FLOOR, FLOOR}
        };
    }
}
