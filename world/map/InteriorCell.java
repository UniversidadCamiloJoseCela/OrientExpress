package world.map;

import characters.Person;
import world.interactable.Interactable;

public class InteriorCell {

    private CellType type;
    private Person npcs;

    // Para guardar todos los objetos diferentes pero que al heredar
    // de interaccionable puedas guardarlos
    private Interactable item;

    public InteriorCell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isNpc() {
        return this.npcs != null;
    }

    public void setNpc(Person npcs) {
        this.npcs = npcs;
    }

    public void setItem(Interactable item) {
        this.item = item;
    }

    public boolean isItem() {
        return this.item != null;
    }

    public Interactable getItem() {
        return item;
    }

    public Person getNpc() {
        return npcs;
    }
}
