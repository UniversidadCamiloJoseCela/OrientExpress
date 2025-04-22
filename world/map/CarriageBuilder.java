package world.map;

import world.item.Door;

import java.util.HashSet;
import java.util.Set;

public class CarriageBuilder {

    private final CarriageType type;
    private CarriageLayout layout = new CarriageLayout();

    // Van por separado porque son puntos de union entre vagones
    private final Set<Door> doors = new HashSet<>();


    public CarriageBuilder(CarriageType type) {
        this.type = type;
    }

    /**
     * Agrega una puerta externa en coordenadas (fila, columna) y actualiza el layout.
     */
    public CarriageBuilder addDoor(int row, int col) {
        Door door = new Door(row, col);
        doors.add(door);
        layout.cellAt(row, col).setType(CellType.DOOR); // Para imprimir
        return this;
    }

    /**
     * Personaliza el plano de CellType (debe ser 3×7).
     */
    public CarriageBuilder setLayout(CellType[][] types) {
        this.layout = new CarriageLayout(types);
        return this;
    }

    public Carriage build() {
        return new Carriage(this);
    }

    // Getters para uso interno por Carriage
    CarriageType getType() {
        return type;
    }

    Set<Door> getDoors() {
        return doors;
    }

    CarriageLayout getLayout() {
        return layout;
    }
}
