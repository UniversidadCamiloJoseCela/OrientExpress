package characters;

import world.item.Clue;
import world.map.CarriageLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {

    // Attr
    private String name;
    private int age;
    private boolean gender;
    private String description;
    private String adjetives;
    private String emoji;

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    //Constructor
    public Person (String name, int age, boolean gender, String description, String adjetives){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.adjetives = adjetives;
    }

    //

    private int currentCarriage  = 0;
    private int row = 0, col = 1; // posición inicial

    public int getCurrentCarriage() { return currentCarriage; }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public void moveToCarriage(int index) {
        this.currentCarriage = index;
    }

    public void moveUp()    { if (row > 0) row--; }
    public void moveDown()  { if (row < CarriageLayout.ROWS - 1) row++; }
    public void moveLeft()  { if (col > 0) col--; }
    public void moveRight() { if (col < CarriageLayout.COLS - 1) col++; }




    // Setter and getters



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdjetives() {
        return adjetives;
    }
    public void setAdjetives(String adjetives) {
        this.adjetives = adjetives;
    }


    public void setRow(int i) {
        this.row = i;
    }

    public void setCol(int i) {
        this.col = i;
    }
}


