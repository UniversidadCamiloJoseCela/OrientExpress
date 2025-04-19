package characters;

import map.Carriage;
import map.Train;

import java.util.Arrays;
import java.util.Scanner;
//Clase base de las que heredaran todos los personajes
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
    public Person(String name, int age, boolean gender, String description, String adjetives) {
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


    public void move(String movement, Carriage carriage) {
        int[] actualPosition = carriage.findPosition();
        //System.out.println("START ****" + Arrays.toString(actualPosition));
        if (checkDoor(actualPosition[0], actualPosition[1], carriage) != null) {
            //Si cambia de vagón se sobreescribe el vagón
            carriage = checkDoor(actualPosition[0], actualPosition[1], carriage);
        }
        switch (movement.toLowerCase()) {
            /*
            actualPosition[0] = posicion eje x.
            actualPosition[1] = posicion eje y.
             */

            case "w":
                if (actualPosition[0] > 0) {
                    actualPosition[0] -= 1;
                }
                break;
            case "s":
                if (actualPosition[0] < 2) {
                    actualPosition[0] += 1;
                }
                break;
            case "a":
                if (actualPosition[1] > 0) {
                    actualPosition[1] -= 1;
                }
                break;
            case "d":
                if (actualPosition[1] < 6) {
                    actualPosition[1] += 1;
                }
                break;
        }


        if (carriage.checkObjectCollision(actualPosition)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Perro: Hola! Oso glotón!");
            boolean exists = false;
            while (!exists) {
                System.out.println("1. Como te ha ido el dia?");
                System.out.println("2. Que estas haciendo perro?");
                System.out.println("3. [Accion] Pedirle la patita?");
                System.out.println("4. [Accion] Dejar conversación");
                switch (scanner.nextLine()) {
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
                        System.out.println("-> Perro: *Muevo la cola y me despido, hasta luego, Oso!*");
                        exists = true;
                        break;
                }

            }
        } else {
            //System.out.println("FINISH ****" + Arrays.toString(actualPosition));
            carriage.updatePosition(actualPosition);
        }


    }

    //función comprobar puertas
    public static Carriage checkDoor(int x, int y, Carriage carriage) {
        //interacción con las puertas
        if ((x == 1 && y == 0) || (x == 6 && y == 1)) {
            //Se encuentra en el extremo izquierdo

            //Comprobación del vagón dentro del tren
            if (Train.getPositionCarriage(carriage) >= 0) {
                //puerta izquierda
                if (x == 0) {
                    //Esta adelantado de la primera posición
                    if (Train.getPositionCarriage(carriage) > 0) {
                        carriage = Train.arrCarriages.get(Train.getPositionCarriage(carriage) - 1);
                        return carriage;
                    } else {
                        System.out.println("Puerta bloqueada o inexistente");
                        return null;
                    }
                }
                //puerta derecha
                if (x == 6) {
                    //Está atrasado de la última posición
                    if (Train.getPositionCarriage(carriage) < 6) {
                        carriage = Train.arrCarriages.get(Train.getPositionCarriage(carriage) + 1);
                        return carriage;
                    } else {
                        System.out.println("Puerta bloqueada o inexistente");
                        return null;
                    }
                }

            }

        }
        return null;
    }


}


