package world.interactable;

import characters.Person;

public interface Interactable {
    InteractionResult interact(Person player);
}
