package characters;

public class Criminologist extends Person {

    /**
     * @param name        Nombre del criminólogo
     * @param age         Edad del personaje
     * @param gender      true=masculino, false=femenino
     * @param description Descripción breve
     * @param adjectives  Adjetivos que lo caracterizan
     */
    public Criminologist(String name, int age, boolean gender, String description, String adjectives) {
        super(name, age, gender, description, adjectives);
    }
}
