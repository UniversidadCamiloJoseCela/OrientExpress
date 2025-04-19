import characters.*;
import map.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

    public static void main (String[] argumentos) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Seleccione lenguaje: en, es, ro, ru, ch ");
        String lang = sc.next().trim();


        Locale locale = Locale.forLanguageTag(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);


        Detective detective = new Detective(
                bundle.getString("detective.name"),
                38,
                false,
                bundle.getString("detective.bio"),
                bundle.getString("detective.traits")
        );


        DetectiveAssistant detectiveAssistant = new DetectiveAssistant(
                bundle.getString("detectiveAssistant.name"),
                18,
                false,
                bundle.getString("detectiveAssistant.bio"),
                bundle.getString("detectiveAssistant.traits")
        );

        LocomotiveDriver locomotiveDriver = new LocomotiveDriver(
                bundle.getString("locomotiveDriver.name"),
                45,
                false,
                bundle.getString("locomotiveDriver.bio"),
                bundle.getString("locomotiveDriver.traits")
        );

        TrainCoalman trainCoalman = new TrainCoalman(
                bundle.getString("trainCoalman.name"),
                56,
                false,
                bundle.getString("trainCoalman.bio"),
                bundle.getString("trainCoalman.traits")
        );

        Comander comander = new Comander(
                bundle.getString("comander.name"),
                87,
                false,
                bundle.getString("comander.bio"),
                bundle.getString("comander.traits")
        );

        Mayor mayor = new Mayor(
                bundle.getString("mayor.name"),
                50,
                false,
                bundle.getString("mayor.bio"),
                bundle.getString("mayor.traits")
        );

        Criminologist criminologist = new Criminologist(
                bundle.getString("criminologist.name"),
                29,
                true,
                bundle.getString("criminologist.bio"),
                bundle.getString("criminologist.traits")
        );

        Novelist novelist = new Novelist(
                bundle.getString("novelist.name"),
                43,
                true,
                bundle.getString("novelist.bio"),
                bundle.getString("novelist.traits")
        );

        BaggageRoom baggageRoom = new BaggageRoom(
                bundle.getString("baggageRoom.name"),
                bundle.getString("baggageRoom.description"),
                false
        );

        Bathroom bathroom = new Bathroom(
                bundle.getString("bathroom.name"),
                bundle.getString("bathroom.description"),
                false
        );

        Bedroom bedroom = new Bedroom(
                bundle.getString("bedroom.name"),
                bundle.getString("bedroom.description"),
                false
        );

        Coul coul = new Coul(
                bundle.getString("coul.name"),
                bundle.getString("coul.description"),
                false
        );

        DiningRoom diningRoom = new DiningRoom(
                bundle.getString("diningRoom.name"),
                bundle.getString("diningRoom.description"),
                false
        );

        Kitchen kitchen = new Kitchen(
                bundle.getString("kitchen.name"),
                bundle.getString("kitchen.description"),
                false
        );

        Locomotive locomotive = new Locomotive(
                bundle.getString("locomotive.name"),
                bundle.getString("locomotive.description"),
                false
        );

        Lounge lounge = new Lounge(
                bundle.getString("lounge.name"),
                bundle.getString("lounge.description"),
                false
        );

        Viewpoint viewpoint = new Viewpoint(
                bundle.getString("viewpoint.name"),
                bundle.getString("viewpoint.description"),
                false
        );

        System.out.println(detectiveAssistant.getDescription());




        Train orientExpress = new Train("El orient express");

        //Creation of empty(null objects) bidimensional array
        Object[][] mapping = new Object[3][7];


            for (int i = 0; i < mapping.length; i++) {
                // Loop through each column in the current row
                Arrays.fill(mapping[i], "X");
                if(i % 2 != 0){
                    mapping[i][0] = detective;
                    mapping[i][mapping[i].length-1] = comander;
                }
            }


        viewpoint.setMapping(mapping);
        orientExpress.getArrCarriages().add(viewpoint);
        detective.setEmoji("\uD83D\uDC3B");
        comander.setEmoji("\uD83D\uDC36");
        //orientExpress.getArrCarriages().getFirst().printMap();



        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n¡Buenas! Si quiere empezar esta nueva aventura escriba 'jugar'. \nSi no quiere entrar, " +
                    "escriba cualquier otra cosa.\n" );

            System.out.print("Escribe 'jugar': ");
            if(scanner.nextLine().trim().equalsIgnoreCase("jugar")){
                iniciar(detective, viewpoint);

            } else{
                exit = true;
            }

        }



















    }


    /**
     * DECLARACIÓN DE FUNCIONES AQUÍ
     */

    //Iniciar juego
    private static void iniciar(Detective detective, Carriage carriage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBienvenido, muevase libremente por el escenario");
        while(true){
            carriage.printMap();
            //movimiento del personaje principal
            System.out.print("W: arriba, S: abajo, D: derecha, A: izquierda -> ");
            detective.move(scanner.nextLine(), carriage);
        }

    }



