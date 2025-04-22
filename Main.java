import characters.*;
import dialog.DialogueLine;
import dialog.DialogueManager;
import dialog.DialogueScene;
import world.interactable.InteractionResult;
import world.item.Clue;
import world.item.Door;
import world.map.Carriage;
import world.map.CarriageBuilder;
import world.map.CarriageType;

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

        Person narrador = new Person("Narrador", 0, true, "Es el narrador", "Strong");
        List<DialogueLine> scene1 = List.of(
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene1.line1.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene1.line2.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene1.line3.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene1.line4.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene1.line5.text"),
                        0
                )
        );

        // MAPA ENTRA EN EL TREN - ENTREGAR TICKER

        List<DialogueLine> scene2 = List.of(
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line1.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line2.text"),
                        0
                ),
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene2.line3.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line4.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line5.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line6.text"),
                        0
                ),
                new DialogueLine(
                        detective,
                        bundleDialog.getString("scene2.line7.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene2.line8.text"),
                        0
                )

        );

        // LUNES

        List<DialogueLine> scene3 = List.of(
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line1.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line2.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line3.text"),
                        0
                ),
                new DialogueLine(
                        detectiveAssistant,
                        bundleDialog.getString("scene3.line4.text"),
                        0
                ),
                new DialogueLine(
                        criminologist,
                        bundleDialog.getString("scene3.line5.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line6.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line7.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line8.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line9.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line10.text"),
                        0
                ),
                new DialogueLine(
                        comander,
                        bundleDialog.getString("scene3.line11.text"),
                        0
                ),
                new DialogueLine(
                        trainCoalman,
                        bundleDialog.getString("scene3.line12.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line13.text"),
                        0
                ),
                new DialogueLine(
                        narrador,
                        bundleDialog.getString("scene3.line14.text"),
                        0
                )
        );

        dm.loadScene(new DialogueScene(scene3));
        dm.start();


        while (true) {
            System.out.println("\n--- MENÚ DE DIÁLOGOS ---");
            System.out.print("Elige (a/b/c) o cualquier otra tecla para salir: ");
            String op = sc.next().trim().toLowerCase();
            if (!op.equals("a") && !op.equals("b") && !op.equals("c")) {
                System.out.println("Saliendo del menú de diálogos.");
                break;
            }
            List<DialogueLine> nextScene = switch (op) {
                case "a" -> scene1;
                case "b" -> scene1;
                default  -> scene1;
            };
            dm.loadScene(new DialogueScene(nextScene));
            dm.start();
        }






        // Crear vagones
        List<Carriage> train = new ArrayList<>();
        for (CarriageType type : CarriageType.values()) {
            // En vez de .setLayout(CarriageType.DINING_MATRIX)
            // usamos la matriz que está “guardada” en el enum
            CarriageBuilder b = new CarriageBuilder(type)
                    .setLayout(type.getMatrix());
            int idx = type.ordinal();
            if (idx > 0)   b.addDoor(1, 0);
            if (idx < CarriageType.values().length - 1) b.addDoor(1, 6);
            train.add(b.build());
        }


        // Mapa de puertas a destino [vagónÍndice,row,col]->destÍndice
        Map<String,Integer> doorMap = new HashMap<>();
        for (int i = 0; i < train.size(); i++) {
            Carriage c = train.get(i);
            for (Door d : c.getDoors()) {
                int dest = (d.y() == 0) ? i - 1 : i + 1;
                doorMap.put(STR."\{i},\{d.x()},\{d.y()}", dest);
            }
        }

        doorMap.forEach((key, value) -> System.out.println(STR."\{key}:\{value}"));

        Detective player = new Detective("Flor", 1,true,"HGUAD", "d");

        // Añadimos una pista en BEDROOM (índice 2)
        Carriage bedroom = train.get(2);
        bedroom.getLayout().cellAt(0,0).setItem(new Clue("12","adasda"));
        bedroom.getLayout().cellAt(1,2).setNpc(
                new Criminologist("Flor", 1,true,"HGUAD", "d")
        );


        System.out.println("Usa W/A/S/D para moverte, E para interactuar, Q para salir, H para historial, P para pistas.");
        while (true) {
            Carriage current = train.get(player.getCurrentCarriage());
            System.out.println(STR."Vagón: \{current.getType()}");
            current.render(player);
            System.out.print("> ");
            sc.next().toUpperCase(); //flushData
            String cmd = sc.nextLine().toUpperCase();
            if (cmd.equals("Q")) break;
            int newR = player.getRow();
            int newC = player.getCol();
            switch (cmd) {
                case "W": newR--; break;
                case "S": newR++; break;
                case "A": newC--; break;
                case "D": newC++; break;
                case "H": {
                    dm.printHistory(
                            bundleMsg.getString("scene"),
                            bundleMsg.getString("character"),
                            bundleMsg.getString("text")
                    );
                    continue;
                }
                case "P": {
                    System.out.println("Pistas encontradas:");
                    for (Clue c : player.getClues()) {
                        System.out.println(STR."- \{c.getDescription()}");
                    }
                    continue;
                }
                case "E": {
                    // interactuar pista obligatorio
                    var cell = current.getLayout().cellAt(player.getRow(), player.getCol());
                    if (cell.isItem()) {
                        InteractionResult res = cell.getItem().interact(player);
                        System.out.println(res.message());
                        cell.setItem(null);
                    } else {
                        // interactuar puerta
                        String key = STR."\{player.getCurrentCarriage()},\{player.getRow()},\{player.getCol()}";
                        System.out.println(key);
                        if (doorMap.containsKey(key)) {
                            int dest = doorMap.get(key);
                            player.moveToCarriage(dest);
                            // posiciona al otro lado de la puerta
                            player.setRow(1);
                            player.setCol((player.getCol() == 0) ? 6 : 0);
                            System.out.println(STR."Te mueves al vagón \{train.get(dest).getType()}");

                        }
                    }
                    continue;
                }
            }
            // mover jugador si es transitable
            if (current.isWalkable(newR, newC)) {
                player.setRow(newR);
                player.setCol(newC);
            }
        }
            sc.close();
            System.out.println("¡Juego terminado!");

}