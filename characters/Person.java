package characters;

import map.Carriage;

import java.util.Arrays;
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

    public void move(String movement, Carriage carriage){
        int[] actualPosition = carriage.findPosition();
        //System.out.println("START ****" + Arrays.toString(actualPosition));
        switch (movement.toLowerCase()){
            case "w":
                if (actualPosition[0] > 0){
                    actualPosition[0]-=1;
                }
                break;
            case "s":
                if (actualPosition[0] < 2){
                    actualPosition[0]+=1;
                }
                break;
            case "a":
                if(actualPosition[1] > 0){
                    actualPosition[1]-=1;
                }
                break;
            case "d":
                if (actualPosition[1] < 6){
                    actualPosition[1]+=1;
                }
                break;
        }

        if (carriage.checkObjectCollision(actualPosition)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Perro: Hola!");
            boolean exists = false;
            while (!exists){
                System.out.println("1. Como te ha ido el dia?");
                System.out.println("2. Que estas haciendo perro?");
                System.out.println("3. [Accion] Pedirle la patita?");
                System.out.println("4. [Accion] Dejar conversacion");
                switch (scanner.nextLine()){
                    case "1":
                        System.out.println("-> Perro: Guau, mi día fue genial, lleno de juegos y siestas.");
                        break;
                    case "2":
                        System.out.println("-> Perro: Estoy corriendo y explorando, ¡la vida de perro es emocionante!");
                        break;
                    case "3":
                        System.out.println("-> Perro: *Levanto mi patita con alegría*");
                        break;
                    case "4":
                        System.out.println("-> Perro: *Muevo la cola y me despido, hasta luego, humano!*");
                        exists = true;
                        break;
                }
            }
        }else {
            //System.out.println("FINISH ****" + Arrays.toString(actualPosition));
            carriage.updatePosition(actualPosition);
        }

    }

}


