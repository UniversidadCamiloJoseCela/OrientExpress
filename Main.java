import characters.*;
import dialog.DialogueManager;
import dialog.DialogueScene;
import dialog.Scene;
import world.TerminalUI;
import world.interactable.InteractionResult;
import world.item.Clue;
import world.item.Door;
import world.map.Carriage;
import world.map.CarriageBuilder;
import world.map.CarriageType;

import java.util.*;

public static void main(String[] args) throws Exception {
    TerminalUI.launchWithUI(args, () -> {
        try {
            // Aquí llamas a tu antiguo main sin cambios:
            originalMain(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}

public static void originalMain (String[] argumentos) throws Exception {

    String trainArt = """
                         _-====-__-======-__-========-_____-============-__
                       _(                                                 _)
                    OO(        O R I E N T   E X P R E S S               )_
                   0  (_                                              __  _)
                 o0     (_                                                _)
                o         '=-___-===-_____-========-___________-===-dwb-='
              .o                                _________
             . ______          ______________  |         |      _____
           _()_||__|| ________ |            |  |_________|   __||___||__
          (BNSF 1995| |      | |            | __Y______00_| |_         _|
         /-OO----OO""="OO--OO"="OO--------OO"="OO-------OO"="OO-------OO"=P
        #####################################################################
        """;

    System.out.println(trainArt);

    System.out.println("Seleccione lenguaje: en, es, ro, ru, ch ");

    // Scanner
    Scanner sc = new Scanner(System.in);

    // Crear vagones
    List<Carriage> train = getCarriages();

    // Mapa de puertas a destino [vagónÍndice,row,col]->destÍndice
    Map<String, Integer> doorMap = getStringIntegerMap(train);

    // Translate
    Locale locale = Locale.forLanguageTag(sc.next().trim());
    ResourceBundle bundleMsg = ResourceBundle.getBundle("i18n.messages", locale); // Texto del juego
    ResourceBundle bundleDialog = ResourceBundle.getBundle("i18n.dialogues", locale); // Dialogo del juego

    // Gestor de dialogos
    DialogueManager dm = new DialogueManager();

    // Narrador
    Person narrador = new Person("Narrador", 0, true, "Narrador", "N/A");

    // Peronajes
    Detective detective = new Detective(
            bundleMsg.getString("detective.name"),
            38,
            false,
            bundleMsg.getString("detective.bio"),
            bundleMsg.getString("detective.traits")
    );
    detective.setRow(2);
    detective.setCol(0);

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


    // Introduccion al juego
    dm.loadScene(Scene.scene1(bundleDialog, narrador));
    dm.start();


    Carriage vw = train.getFirst(); // viewpoint
    vw.getLayout().cellAt(2,1).setNpc(locomotiveDriver); // ticketero
    vw.getLayout().cellAt(1,2).setNpc(mayor); // coliison con table
    vw.getLayout().cellAt(1,4).setNpc(comander);

    // Impresion del mapa (return aunque no se se aqui)
    printMap(train, detective);

    dm.loadScene(Scene.scene2(bundleDialog, narrador, detective, locomotiveDriver));
    dm.start();


    // Lunes
    System.out.println("LUNES: Inicio del trayecto");

    dm.loadScene(Scene.scene3(bundleDialog, narrador, detective, locomotiveDriver));

    // Menu de opciones
    //optionMenu(sc, bundleDialog, narrador, detective, dm);

    // PISTA + NPC



    // WASD E H P .....
    interactionMovement(train, detective, sc, dm, bundleMsg, doorMap);
    sc.close();
    System.out.println("EXIT");

}

private static Carriage printMap(List<Carriage> train, Detective detective) {
    Carriage current = train.get(detective.getCurrentCarriage());
    System.out.println(STR."Vagón: \{current.getType()}");
    current.render(detective);
    return current;
}

private static void optionMenu(Scanner sc, ResourceBundle bundleDialog, Person narrador, Detective detective, DialogueManager dm) throws Exception {
    while (true) {
        System.out.println("\n--- MENÚ DE DIÁLOGOS ---");
        System.out.print("Elige (a/b/c) o cualquier otra tecla para salir: ");
        String op = sc.next().trim().toLowerCase();
        if (!op.equals("a") && !op.equals("b") && !op.equals("c")) {
            System.out.println("Saliendo del menú de diálogos.");
            break;
        }
        DialogueScene nextScene = switch (op) {
            case "a" -> Scene.scene1(bundleDialog, narrador);
            case "b" -> Scene.scene1(bundleDialog, narrador);
            default -> throw new IllegalStateException(STR."ERROR: \{op}");
        };
        dm.loadScene(nextScene);
        dm.start();
    }
}

private static void interactionMovement(List<Carriage> train, Detective detective, Scanner sc, DialogueManager dm, ResourceBundle bundleMsg, Map<String, Integer> doorMap) {
    System.out.println("Usa W/A/S/D para moverte, E para interactuar, Q para salir, H para historial, P para pistas.");
    while (true) {
        Carriage current = printMap(train, detective);
        System.out.print("> ");
        char cmd = Character.toUpperCase(sc.next().charAt(0));
        if (cmd == 'Q') break;
        int newR = detective.getRow();
        int newC = detective.getCol();
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
                for (Clue c : detective.getClues()) {
                    System.out.println(STR."- \{c.getDescription()}");
                }
                continue;
            }
            case 'E': {
                // interactuar pista obligatorio
                var cell = current.getLayout().cellAt(detective.getRow(), detective.getCol());
                if (cell.isItem()) {
                    InteractionResult res = cell.getItem().interact(detective);
                    System.out.println(res.message());
                    cell.setItem(null);
                } else {
                    // interactuar puerta
                    String key = STR."\{detective.getCurrentCarriage()},\{detective.getRow()},\{detective.getCol()}";
                    System.out.println(key);
                    if (doorMap.containsKey(key)) {
                        int dest = doorMap.get(key);
                        detective.moveToCarriage(dest);
                        // posiciona al otro lado de la puerta
                        detective.setRow(1);
                        detective.setCol((detective.getCol() == 0) ? 6 : 0);
                        System.out.println(STR."Te mueves al vagón \{train.get(dest).getType()}");

                    }
                }
                continue;
            }
        }
        // mover jugador si es transitable
        if (current.isWalkable(newR, newC)) {
            detective.setRow(newR);
            detective.setCol(newC);
        }
    }
}

private static Map<String, Integer> getStringIntegerMap(List<Carriage> train) {
    Map<String,Integer> doorMap = new HashMap<>();
    for (int i = 0; i < train.size(); i++) {
        Carriage c = train.get(i);
        for (Door d : c.getDoors()) {
            int dest = (d.y() == 0) ? i - 1 : i + 1;
            doorMap.put(STR."\{i},\{d.x()},\{d.y()}", dest);
        }
    }
    return doorMap;
}

private static List<Carriage> getCarriages() {
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
    return train;
}