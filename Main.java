import characters.*;
import dialog.DialogueLine;
import dialog.DialogueManager;
import dialog.DialogueScene;
import dialog.Scene;
import world.MapWindow;
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




        Person narrador = new Person("Narrador", 0, true, "Es el narrador", "Strong");

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


        DialogueManager dm = new DialogueManager();

        // Carga y ejecuta la escena 1
        dm.loadScene(Scene.scene1(bundleDialog, narrador, detective));
        dm.start();

        MapWindow.showMapWithDialogue(Scene.scene1(bundleDialog, narrador, detective));

        // Carga y ejecuta la escena 2
        dm.loadScene(Scene.scene2(bundleDialog, narrador, detective));
        dm.start();

        dm.loadScene(Scene.scene3(bundleDialog, narrador, detectiveAssistant, criminologist, comander));
        dm.start();

        while (true) {
            System.out.println("\n--- MENÚ DE DIÁLOGOS ---");
            System.out.print("Elige (a/b/c) o cualquier otra tecla para salir: ");
            String op = sc.next().trim().toLowerCase();
            if (!op.equals("a") && !op.equals("b") && !op.equals("c")) {
                System.out.println("Saliendo del menú de diálogos.");
                break;
            }
            DialogueScene nextScene = switch (op) {
                case "a" -> Scene.scene1(bundleDialog, narrador, detective);
                case "b" -> Scene.scene2(bundleDialog, narrador, detective);
                default -> throw new IllegalStateException(STR."ERROR: \{op}");
            };
            dm.loadScene(nextScene);
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
        Carriage bedroom = train.get(0);
        bedroom.getLayout().cellAt(0,0).setItem(new Clue("12","adasda"));
        bedroom.getLayout().cellAt(1,2).setNpc(comander); // usar table como colision
        bedroom.getLayout().cellAt(1,4).setNpc(criminologist);


        System.out.println("Usa W/A/S/D para moverte, E para interactuar, Q para salir, H para historial, P para pistas.");
        while (true) {
            Carriage current = train.get(player.getCurrentCarriage());
            System.out.println(STR."Vagón: \{current.getType()}");
            current.render(player);
            System.out.print("> ");
            char cmd = Character.toUpperCase(sc.next().charAt(0));
            if (cmd == 'Q') break;
            int newR = player.getRow();
            int newC = player.getCol();
            switch (cmd) {
                case 'W': newR--; break;
                case 'S': newR++; break;
                case 'A': newC--; break;
                case 'D': newC++; break;
                case 'H': {
                    dm.printHistory(
                            bundleMsg.getString("scene"),
                            bundleMsg.getString("character"),
                            bundleMsg.getString("text")
                    );
                    continue;
                }
                case 'P': {
                    System.out.println("Pistas encontradas:");
                    for (Clue c : player.getClues()) {
                        System.out.println(STR."- \{c.getDescription()}");
                    }
                    continue;
                }
                case 'E': {
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