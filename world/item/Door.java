package world.item;

import characters.Person;
import world.interactable.Interactable;
import world.interactable.InteractionResult;

public record Door(int x, int y) implements Interactable {
    @Override
    public InteractionResult interact(Person player) {
        return new InteractionResult("Te mueves al vagón ", false);
    }
}