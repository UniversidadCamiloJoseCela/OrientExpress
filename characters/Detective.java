package characters;

import world.item.Clue;

import java.util.ArrayList;
import java.util.List;

public class Detective extends Person {

    private List<Clue> clues = new ArrayList<>();

    public Detective(String name, int age, boolean gender, String description, String adjetives) {
        super(name,age, gender, description, adjetives);

    }

    public void addClue(Clue clue) { clues.add(clue); }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public List<Clue> getClues()    { return clues; }
}
