package world.item;

import characters.Detective;
import world.interactable.Interactable;
import characters.Person;
import world.interactable.InteractionResult;

public class Clue implements Interactable {
    private final String id;
    private final String descriptionKey;

    public Clue(String id, String descriptionKey) {
        this.id = id;
        this.descriptionKey = descriptionKey;
    }

    public String getId() {
        return id;
    }

    /**
     * Devuelve la descripción localizada de la pista.
     */
    public String getDescription() {
        return this.descriptionKey;
    }

    @Override
    public InteractionResult interact(Detective player) {
        player.addClue(this);
        String msg = String.format("NOT FOUND", getDescription());
        return new InteractionResult(msg, true);
    }

    @Override
    public String toString() {
        return getDescription();
    }

}