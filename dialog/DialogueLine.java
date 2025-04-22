package dialog;

import characters.Person;

public record DialogueLine(Person speaker, String text, long delayMillis) {
}


