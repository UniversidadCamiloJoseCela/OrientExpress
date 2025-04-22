package world.item;

import characters.Person;
import world.interactable.Interactable;
import world.interactable.InteractionResult;

public class Clue implements Interactable {
    private final String id;
    private final String description;

    public Clue(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public InteractionResult interact(Person player) {
        player.addClue(this);
        return new InteractionResult(STR."Has encontrado la pista: \{this.description}", true);
    }

    @Override
    public String toString() {
        return this.description;
    }

}
