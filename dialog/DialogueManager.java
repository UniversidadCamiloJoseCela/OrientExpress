package dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class DialogueManager {

    private final Queue<DialogueLine> queue = new ArrayDeque<>(); // Guardamos las lineas de dialogo
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void loadScene(DialogueScene scene) {
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
    };

    private void waitForUserAction() throws IOException {
        System.out.print("  [ENTER ...]");
        reader.readLine();
    }
}


