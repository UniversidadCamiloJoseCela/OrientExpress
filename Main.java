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

    int carriageIndex = detective.getCurrentCarriage();
    int dr = detective.getRow();
    int dc = detective.getCol();
    Carriage.printTrainWithDetective(carriageIndex, dr, dc);

    // Introduccion al juego
    dm.loadScene(Scene.scene1(bundleDialog, narrador));
    dm.start();

    Carriage vw = train.getFirst(); // viewpoint
    vw.getLayout().cellAt(2,1).setNpc(locomotiveDriver); // ticketero
    vw.getLayout().cellAt(1,2).setNpc(mayor); // coliison con table
    vw.getLayout().cellAt(1,4).setNpc(comander);

    // Impresion del mapa (return aunque no se se aqui)
    printMap(train, detective, detective.getCurrentCarriage());

    dm.loadScene(Scene.scene2(bundleDialog, narrador, detective, locomotiveDriver));
    dm.start();


    // Lunes
    System.out.println("LUNES: Inicio del trayecto");

    dm.loadScene(Scene.scene3(bundleDialog, narrador, detectiveAssistant, trainCoalman, criminologist, comander));
    dm.start();

    // Impresion del mapa (return aunque no se se aqui)
    vw = train.get(2); // DINNER
    vw.getLayout().cellAt(0,1).setNpc(detectiveAssistant);
    vw.getLayout().cellAt(0,2).setNpc(criminologist);
    vw.getLayout().cellAt(2,3).setNpc(comander);
    vw.getLayout().cellAt(2,4).setNpc(trainCoalman);
    printMap(train, detective, 2);

    // MARTES (LOUNGE)

    vw = train.get(2); // DINNER
    vw.getLayout().cellAt(0,1).setNpc(null);
    vw.getLayout().cellAt(0,2).setNpc(null);
    vw.getLayout().cellAt(2,3).setNpc(null);
    vw.getLayout().cellAt(2,4).setNpc(null);

    dm.loadScene(Scene.scene4(bundleDialog, narrador, detective, comander));
    dm.start();

    vw = train.get(3);
    vw.getLayout().cellAt(1,3).setNpc(comander);
    detective.setCurrentCarriage(3);
    printMap(train, detective, 3);


    interactionMovement(train, narrador ,detective, sc, dm, bundleMsg, bundleDialog,doorMap,
            comander, mayor, detectiveAssistant, criminologist, novelist);
    sc.close();
    System.out.println("EXIT");

}

private static Carriage printMap(List<Carriage> train, Detective detective, int index) {
    Carriage current = train.get(index);
    System.out.println(STR."Vagón: \{current.getType()}");
    current.render(detective);
    return current;
}

private static void optionMenu(Scanner sc, DialogueManager dm, DialogueScene sceneA, DialogueScene sceneB) throws Exception {
        System.out.println("\n--- DIALOGOS ---");
        System.out.print("Elige (a/b)");
        String op = sc.next().trim().toLowerCase();
        DialogueScene nextScene = switch (op) {
            case "a" -> sceneA;
            case "b" -> sceneB;
            default -> throw new IllegalStateException(STR."ERROR: \{op}");
        };
        dm.loadScene(nextScene);
        dm.start();
}

private static void interactionMovement(List<Carriage> train, Person narrador, Detective detective, Scanner sc, DialogueManager dm, ResourceBundle bundleMsg, ResourceBundle bundleDialog, Map<String, Integer> doorMap,
                                        Comander comander, Mayor mayor, DetectiveAssistant detectiveAssistant,
                                        Criminologist criminologist, Novelist novelist) throws Exception {
    System.out.println("Usa W/A/S/D para moverte, E para interactuar, Q para salir, H para historial, P para pistas, R para resolver caso.");
    while (true) {
        Carriage current = printMap(train, detective, detective.getCurrentCarriage()); // !!!!
        System.out.print("> ");
        char cmd = Character.toUpperCase(sc.next().charAt(0));
        if (cmd == 'Q') break;
        Carriage vw;
        int newR = detective.getRow();
        int newC = detective.getCol();
        switch (cmd) {
            case 'W': newR--; break;
            case 'S': newR++; break;
            case 'A': newC--; break;
            case 'D': newC++; break;
            case 'R': {

                // Crear scenario final
                if(detective.getClues().size() != 5) {
                    System.out.println("Faltan pistas investiga mas");
                    System.out.println(STR."\{detective.getClues().size()}de 5");
                    continue;
                }

                vw = train.get(1);
                detective.setCurrentCarriage(1);
                detective.setRow(1);
                detective.setCol(1);
                vw.getLayout().cellAt(0,3).setNpc(criminologist);
                vw.getLayout().cellAt(0,4).setNpc(detectiveAssistant);
                vw.getLayout().cellAt(1,4).setNpc(mayor);
                vw.getLayout().cellAt(2,4).setNpc(comander);

                dm.loadScene(Scene.scene11(bundleDialog, detective, narrador));
                dm.start();

                optionMenu(sc, dm,
                        Scene.scene11a(bundleDialog, detective, criminologist),
                        Scene.scene11b(bundleDialog, detective, novelist));



                continue;
            }
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
                } else if (cell.isNpc()) {

                    if (cell.getNpc() instanceof Comander) {

                        dm.loadScene(Scene.scene5(bundleDialog, narrador));
                        dm.start();

                        optionMenu(sc, dm,
                                Scene.scene5a(bundleDialog, detective, comander),
                                Scene.scene5b(bundleDialog, detective, comander));

                        dm.loadScene(Scene.scene6(bundleDialog, narrador));
                        dm.start();
                        dm.loadScene(Scene.scene7(bundleDialog, narrador));
                        dm.start();

                        // LIMPIAR BOTS
                        vw = train.get(3);
                        vw.getLayout().cellAt(1,3).setNpc(null);

                        // PONER BOTS MAPA
                        vw.getLayout().cellAt(0,5).setNpc(mayor);

                    } else if (cell.getNpc() instanceof Mayor) {

                        dm.loadScene(Scene.scene8(bundleDialog, narrador, detective, mayor));
                        dm.start();

                        optionMenu(sc, dm,
                                Scene.scene8a(bundleDialog, detective, mayor),
                                Scene.scene8b(bundleDialog, detective, mayor));

                        // LIMPIAR BOTS MAPA
                        vw = train.get(3);
                        vw.getLayout().cellAt(0,5).setNpc(null);

                        dm.loadScene(Scene.scene9(bundleDialog, narrador));
                        dm.start();

                        vw = train.get(4);
                        detective.setCurrentCarriage(4);
                        detective.setRow(1);
                        detective.setCol(3);
                        vw.getLayout().cellAt(0,5).setNpc(detectiveAssistant); // PONER CADAVER
                        vw.getLayout().cellAt(2,1).setNpc(criminologist);
                        vw.getLayout().cellAt(2,3).setNpc(novelist);

                        vw.getLayout().cellAt(0,0).setItem(new Clue("Pañuelo de María",
                                "Pañuelo de seda con iniciales 'F', encontrado cerca del cuerpo de Paco."));

                        vw = train.get(5); // LOUNGUE
                        vw.getLayout().cellAt(1,2).setItem(new Clue("Huellas femeninas",
                                "Marcas de zapatos pequeños, posiblemente femeninos, en la alfombra del camarote."));

                        vw = train.get(3); // LOUNGUE
                        vw.getLayout().cellAt(0,0).setItem(new Clue("Libro de Anne",
                                "Libro titulado 'Muerte Silenciosa', firmado por Anne. Detalla técnicas de asesinato sin rastros."));

                        vw = train.get(6); // KITCHEN
                        vw.getLayout().cellAt(1,0).setItem(new Clue("Té con sedantes",
                                "Restos de té con aroma peculiar. Contiene sedantes naturales capaces de paralizar temporalmente."));

                        vw = train.get(8); // LOCOMOTIVE
                        vw.getLayout().cellAt(1,1).setItem(new Clue("Nota de Paco",
                                "Nota arrugada escrita por Paco: 'Ya sé quién eres. Tenemos que hablar urgentemente.' Dirigida aparentemente a María."));


                    } else if (cell.getNpc() instanceof DetectiveAssistant) {
                        dm.loadScene(Scene.scene10(bundleDialog, detective, detectiveAssistant));
                        dm.start();

                        optionMenu(sc, dm,
                                Scene.scene10a(bundleDialog, detective, detectiveAssistant),
                                Scene.scene10b(bundleDialog, detective, detectiveAssistant));



                    } else if (cell.getNpc() instanceof Criminologist) {



                        dm.loadScene(Scene.scene10c(bundleDialog, detective, criminologist));
                        dm.start();

                    } else if (cell.getNpc() instanceof Novelist) {

                        dm.loadScene(Scene.scene10n(bundleDialog, detective, novelist));
                        dm.start();

                    }

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