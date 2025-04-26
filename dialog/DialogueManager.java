package dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DialogueManager {

    private final Queue<DialogueLine> queue = new ArrayDeque<>(); // Guardamos las lineas de dialogo
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private record HistoryEntry(int scene, DialogueLine line) {}
    private final List<HistoryEntry> history = new ArrayList<>();

    private int sceneCounter = 0;
    private int currentScene;


    public void loadScene(DialogueScene scene) {
        sceneCounter++;
        currentScene = sceneCounter;
        queue.clear();
        queue.addAll(scene.lines());
    }

    public void start() throws Exception {
        onStart();
        while (!queue.isEmpty()) {
            DialogueLine line = queue.poll();
            display(line);
            if (line.delayMillis() > 0) {
                Thread.sleep(line.delayMillis());
            } else {
                waitForUserAction();
            }
        }
        onEnd();
    }

    protected void onStart() { }

    protected void onEnd() { }

    private void display(DialogueLine line){
        System.out.printf("%s: %s%n",
                line.speaker().getName(),
                line.text()
        );
        history.add(new HistoryEntry(currentScene, line));
    };

    private void waitForUserAction() throws IOException {
        System.out.println("  [ENTER ...]");
        reader.readLine();
    }

    public void printHistory(String scenes, String characters, String texts) {
        history.stream()
                // 1. Agrupar por escena en orden de inserción
                .collect(Collectors.groupingBy(
                        HistoryEntry::scene,
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .forEach((scene, rows) -> {
                    // 2. Calcular anchos usando streams
                    int maxSpeaker = Math.max(
                            characters.length(),
                            rows.stream()
                                    .mapToInt(e -> e.line().speaker().getName().length())
                                    .max().orElse(0)
                    );
                    int maxText = Math.max(
                            texts.length(),
                            rows.stream()
                                    .mapToInt(e -> e.line().text().length())
                                    .max().orElse(0)
                    );
                    // 3. Crear separador
                    String sep = STR."+\{"-".repeat(maxSpeaker + 2)}+\{"-".repeat(maxText + 2)}+";
                    // 4. Imprimir cabecera y tabla
                    System.out.printf(STR."%n==== \{scenes} %d ====%n%s%n", scene, sep);
                    System.out.printf(STR."| %-\{maxSpeaker}s | %-\{maxText}s |%n", characters, texts);
                    System.out.println(sep);
                    rows.forEach(e ->
                            System.out.printf(
                                    STR."| %-\{maxSpeaker}s | %-\{maxText}s |%n",
                                    e.line().speaker().getName(),
                                    e.line().text()
                            )
                    );
                    System.out.println(sep);
                });
    }



}


