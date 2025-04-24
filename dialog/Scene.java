package dialog;

import characters.*;

import java.util.List;
import java.util.ResourceBundle;

public class Scene {
    public static DialogueScene scene1(ResourceBundle bundleDialog, Person narrador, Detective detective) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene1.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line5.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene2(ResourceBundle bundleDialog, Person narrador, Detective detective) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene2.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene2.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene2.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene2.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene2.line5.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene2.line6.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene2.line7.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene2.line8.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene3(ResourceBundle bundleDialog,
                                       Person narrador,
                                       DetectiveAssistant assistant,
                                       Criminologist criminologist,
                                       Comander comander) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene3.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line3.text"), 0),
                new DialogueLine(assistant, bundleDialog.getString("scene3.line4.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene3.line5.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line6.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line7.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line8.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line9.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line10.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene3.line11.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene3.line12.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line13.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line14.text"), 0)
        );
        return new DialogueScene(lines);
    }

}
