package characters;

public class Person {

    // Attr
    private String name;
    private int age;
    private boolean gender;
    private String description;
    private String adjetives;



    //Constructor
    public Person (String name, int age, boolean gender, String description, String adjetives){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.adjetives = adjetives;
    }


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

}


