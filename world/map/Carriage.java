package world.map;


import characters.*;
import world.item.Door;

import java.util.Collections;
import java.util.Set;

public class Carriage {

    private final CarriageType type;
    private final CarriageLayout layout;
    private final Set<Door> doors;

    /**
     * Constructor package-private que solo acepta un builder externo.
     */
    Carriage(CarriageBuilder builder) {
        this.type   = builder.getType();
        this.layout = builder.getLayout();
        this.doors  = Collections.unmodifiableSet(builder.getDoors());
    }

    public CarriageType getType()    { return type;   }
    public Set<Door> getDoors()      { return doors;  }
    public CarriageLayout getLayout(){ return layout;}

    /**
     * Comprueba si una posición está dentro de los límites del vagón.
     */
    public boolean isInside(int row, int col) {
        return row >= 0 && row < CarriageLayout.ROWS && col >= 0 && col < CarriageLayout.COLS;
    }

    /**
     * Comprueba si la celda en (row,col) es transitable.
     */
    public boolean isWalkable(int row, int col) {
        if (!isInside(row, col)) return false;
        CellType type = layout.cellAt(row, col).getType();
        return type == CellType.FLOOR || type == CellType.DOOR;
    }

    public void render(Person player) {
        int pr = player.getRow();
        int pc = player.getCol();
        for (int r = 0; r < CarriageLayout.ROWS; r++) {
            for (int c = 0; c < CarriageLayout.COLS; c++) {
                if (r == pr && c == pc) {
                    System.out.print("🕵");
                } else {
                    InteriorCell cell = layout.cellAt(r, c);
                    String emoji;
                    if (cell.isItem()) {
                        emoji = "💡";
                    } else if (cell.isNpc()) {
                        if (cell.getNpc() instanceof Detective) {
                            emoji = "🔍";  // Detective FOR BOT ONLY OTHER FINALS DOENST FIND
                        } else if (cell.getNpc() instanceof DetectiveAssistant) {
                            emoji = "👦";  // DetectiveAssistant
                        } else if (cell.getNpc() instanceof LocomotiveDriver) {
                            emoji = "👲";  // LocomotiveDriver
                        } else if (cell.getNpc() instanceof TrainCoalman) {
                            emoji = "👷";  // TrainCoalman
                        } else if (cell.getNpc() instanceof Comander) {
                            emoji = "👮";  // Comander
                        } else if (cell.getNpc() instanceof Mayor) {
                            emoji = "👴";  // Mayor
                        } else if (cell.getNpc() instanceof Criminologist) {
                            emoji = "👩";  // Criminologist
                        } else if (cell.getNpc() instanceof Novelist) {
                            emoji = "👵";  // Novelist
                        } else {
                            emoji = "👤";  // Otro NPC
                        }

                    }
                    else {
                        int finalR = r;
                        int finalC = c;
                        if (doors.stream().anyMatch(d -> d.x() == finalR && d.y() == finalC)) {
                            emoji = "🚪";
                        } else {
                            switch (cell.getType()) {
                                case FLOOR   -> emoji = "🟩";
                                case TABLE   -> emoji = "🍽️";
                                default      -> emoji = "❓";
                            }
                        }
                    }
                    System.out.print(emoji);
                }
            }
            System.out.println();
        }
    }

}
