import characters.*;
import dialog.DialogueLine;
import dialog.DialogueManager;
import dialog.DialogueScene;
import map.*;

import java.util.*;

public static void main (String[] argumentos) throws Exception {


        Scanner sc = new Scanner(System.in);
        System.out.print("Seleccione lenguaje: en, es, ro, ru, ch ");

        Locale locale = Locale.forLanguageTag(sc.next().trim());
        ResourceBundle bundleMsg = ResourceBundle.getBundle("i18n.messages", locale); // Texto del juego
        ResourceBundle bundleDialog = ResourceBundle.getBundle("i18n.dialogues", locale); // Dialogo del juego



        DialogueManager dm = new DialogueManager();


        Detective detective = new Detective(
                bundleMsg.getString("detective.name"),
                38,
                false,
                bundleMsg.getString("detective.bio"),
                bundleMsg.getString("detective.traits")
        );


        DetectiveAssistant detectiveAssistant = new DetectiveAssistant(
                bundleMsg.getString("detectiveAssistant.name"),
                18,
                false,
                bundleMsg.getString("detectiveAssistant.bio"),
                bundleMsg.getString("detectiveAssistant.traits")
        );

        LocomotiveDriver locomotiveDriver = new LocomotiveDriver(
                bundleMsg.getString("locomotiveDriver.name"),
                45,
                false,
                bundleMsg.getString("locomotiveDriver.bio"),
                bundleMsg.getString("locomotiveDriver.traits")
        );

        TrainCoalman trainCoalman = new TrainCoalman(
                bundleMsg.getString("trainCoalman.name"),
                56,
                false,
                bundleMsg.getString("trainCoalman.bio"),
                bundleMsg.getString("trainCoalman.traits")
        );

        Comander comander = new Comander(
                bundleMsg.getString("comander.name"),
                87,
                false,
                bundleMsg.getString("comander.bio"),
                bundleMsg.getString("comander.traits")
        );

        Mayor mayor = new Mayor(
                bundleMsg.getString("mayor.name"),
                50,
                false,
                bundleMsg.getString("mayor.bio"),
                bundleMsg.getString("mayor.traits")
        );

        Criminologist criminologist = new Criminologist(
                bundleMsg.getString("criminologist.name"),
                29,
                true,
                bundleMsg.getString("criminologist.bio"),
                bundleMsg.getString("criminologist.traits")
        );

        Novelist novelist = new Novelist(
                bundleMsg.getString("novelist.name"),
                43,
                true,
                bundleMsg.getString("novelist.bio"),
                bundleMsg.getString("novelist.traits")
        );

        BaggageRoom baggageRoom = new BaggageRoom(
                bundleMsg.getString("baggageRoom.name"),
                bundleMsg.getString("baggageRoom.description"),
                false
        );

        Bathroom bathroom = new Bathroom(
                bundleMsg.getString("bathroom.name"),
                bundleMsg.getString("bathroom.description"),
                false
        );

        Bedroom bedroom = new Bedroom(
                bundleMsg.getString("bedroom.name"),
                bundleMsg.getString("bedroom.description"),
                false
        );

        Coul coul = new Coul(
                bundleMsg.getString("coul.name"),
                bundleMsg.getString("coul.description"),
                false
        );

        DiningRoom diningRoom = new DiningRoom(
                bundleMsg.getString("diningRoom.name"),
                bundleMsg.getString("diningRoom.description"),
                false
        );

        Kitchen kitchen = new Kitchen(
                bundleMsg.getString("kitchen.name"),
                bundleMsg.getString("kitchen.description"),
                false
        );

        Locomotive locomotive = new Locomotive(
                bundleMsg.getString("locomotive.name"),
                bundleMsg.getString("locomotive.description"),
                false
        );

        Lounge lounge = new Lounge(
                bundleMsg.getString("lounge.name"),
                bundleMsg.getString("lounge.description"),
                false
        );

        Viewpoint viewpoint = new Viewpoint(
                bundleMsg.getString("viewpoint.name"),
                bundleMsg.getString("viewpoint.description"),
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


        List<DialogueLine> lines = List.of(
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene1.line1.text"),
                        0
                ),
                new DialogueLine(
                        comander,
                        bundleDialog.getString("scene1.line2.text"),
                        0
                ),
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene1.line3.text"),
                        1000    // avanza solo tras 1s
                ),
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene1.line4.text"),
                        0
                ),
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene1.line5.text"),
                        0
                )
        );


        dm.loadScene(new DialogueScene(lines));
        dm.start();

        System.out.print("Elige (a/b/c): ");

        List<DialogueLine> scene2 = List.of(
                new DialogueLine(detective,  "Comisario, la señora Martínez dice que escuchó gritos anoche.",         0),
                new DialogueLine(comander,  "¿A qué hora exactamente?",                                            0),
                new DialogueLine(comander,     "Sobre las 2:17 de la madrugada, algo crujió en el pasillo.",            1000),
                new DialogueLine(detective,  "¿Reconoce algún sonido en particular?",                                0),
                new DialogueLine(comander,     "Sí, como si alguien arrastrara muebles pesados.",                       0),
                new DialogueLine(detective,  "Bien. Vamos al piso 4, al apartamento 412.",                            0),
                new DialogueLine(comander,"(Abre la puerta con desgana) Buenas… ¿En qué puedo ayudarles?",      0),
                new DialogueLine(detective,  "Señor López, tenemos testigos que oyeron ruidos extraños en su casa.", 0),
                new DialogueLine(comander,"Eso no puede ser… yo estaba durmiendo todo el tiempo.",                  1000),
                new DialogueLine(comander,  "Necesitamos que nos acompañe a la comisaría para unas preguntas.",     0)
        );

        List<DialogueLine> nextScene = switch (sc.next().trim().toLowerCase()) {
            case "a" -> scene2;
            case "b" -> scene2;
            default  -> lines;
        };

        dm.loadScene(new DialogueScene(nextScene));
        dm.start();


        dm.printHistory(
                bundleMsg.getString("scene"),
                bundleMsg.getString("character"),
                bundleMsg.getString("text")
        );


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            iniciar(detective, viewpoint);
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



