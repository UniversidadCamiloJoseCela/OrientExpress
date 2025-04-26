package world.interactable;

import characters.Detective;
import characters.Person;

public interface Interactable {
    InteractionResult interact(Detective player);
}
