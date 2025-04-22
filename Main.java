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

        List<DialogueLine> scene3 = List.of(
                new DialogueLine(comander,    "He recogido huellas y muestras de sangre en el suelo.",             0),
                new DialogueLine(detective,  "¿Algún hallazgo relevante?",                                       0),
                new DialogueLine(comander,    "El patrón de la sangre indica dos personas heridas.",              1000),
                new DialogueLine(detective,  "Entonces había un segundo agresor o víctima móvil.",              0),
                new DialogueLine(comander,    "También hay fibras de tela negra cerca de la ventana.",           0),
                new DialogueLine(detective,  "Muy bien. Asegura todo y llévate las muestras al laboratorio.",  0)
        );

        // Escena 4: interrogatorio al sospechoso
        List<DialogueLine> scene4 = List.of(
                new DialogueLine(detective,  "Señor López, ¿dónde estaba usted entre las dos y las tres de la mañana?", 0),
                new DialogueLine(comander, "En mi piso, durmiendo. Nadie puede confirmarlo.",                     0),
                new DialogueLine(detective,  "Tenemos testigos que oyeron su voz en el rellano.",                    1000),
                new DialogueLine(comander, "Eso es imposible… ¡nunca salí de mi casa!",                              0),
                new DialogueLine(comander,  "¿Nos permite registrar su teléfono y movimientos bancarios?",        0),
                new DialogueLine(comander, "Está bien, no tengo nada que ocultar.",                                   0)
        );

        // Escena 5: el testigo aporta una pista de última hora
        List<DialogueLine> scene5 = List.of(
                new DialogueLine(comander,    "Disculpen, olvidé decirles algo importante.",                      0),
                new DialogueLine(detective,  "Adelante, señora Pérez.",                                            0),
                new DialogueLine(comander,    "Vi a una figura encapuchada salir corriendo con un maletín.",      1000),
                new DialogueLine(detective,  "¿Hacia dónde fue esa persona?",                                     0),
                new DialogueLine(comander,    "Se metió por la calle del Olmo, justo al lado de la panadería.",   0),
                new DialogueLine(detective,  "Apúntalo: posible ruta de huida. Gracias.",                        0)
        );



        while (true) {
            System.out.println("\n--- MENÚ DE DIÁLOGOS ---");
            System.out.print("Elige (a/b/c) o cualquier otra tecla para salir: ");
            String op = sc.next().trim().toLowerCase();
            if (!op.equals("a") && !op.equals("b") && !op.equals("c")) {
                System.out.println("Saliendo del menú de diálogos.");
                break;
            }
            List<DialogueLine> nextScene = switch (op) {
                case "a" -> scene3;
                case "b" -> scene4;
                default  -> scene5;
            };
            dm.loadScene(new DialogueScene(nextScene));
            dm.start();
        }


        dm.printHistory(
                    bundleMsg.getString("scene"),
                    bundleMsg.getString("character"),
                    bundleMsg.getString("text")
        );



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

    System.out.println("Usa W/A/S/D para moverte, E para interactuar, Q para salir.");
    while (true) {
        Carriage current = train.get(player.getCurrentCarriage());
        System.out.println("Vagón: " + current.getType());
        current.render(player);
        System.out.print("> ");
        String cmd = sc.nextLine().toUpperCase();
        if (cmd.equals("Q")) break;
        int newR = player.getRow();
        int newC = player.getCol();
        switch (cmd) {
            case "W": newR--; break;
            case "S": newR++; break;
            case "A": newC--; break;
            case "D": newC++; break;
            case "E": {
                // interactuar pista obligatorio
                var cell = current.getLayout().cellAt(player.getRow(), player.getCol());
                if (cell.getItem()) {
                    InteractionResult res = cell.getItems().interact(player);
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
                        System.out.println(STR."ANTES\{player.getRow()} \{player.getCol()}");
                        player.setRow(1);
                        player.setCol((player.getCol() == 0) ? 6 : 0);
                        System.out.println(STR."DESPUES\{player.getRow()} \{player.getCol()}");
                        System.out.println(STR."Te mueves al vagón \{train.get(dest).getType()}");

                    }else{
                        System.out.println("No hay door");
                    }
                }
                System.out.print("QUE PASA ");
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



