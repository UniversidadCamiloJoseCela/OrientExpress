package dialog;

import characters.*;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Scene {
    public static DialogueScene scene1(ResourceBundle bundleDialog, Person narrador) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene1.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene1.line5.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene2(ResourceBundle bundleDialog, Person narrador, Detective detective, LocomotiveDriver locomotiveDriver) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(locomotiveDriver, bundleDialog.getString("scene2.line1.text"), 0),
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

    // LUNES
    public static DialogueScene scene3(ResourceBundle bundleDialog,
                                       Person narrador,
                                       DetectiveAssistant assistant,
                                       TrainCoalman trainCoalman,
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
                new DialogueLine(trainCoalman, bundleDialog.getString("scene3.line12.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line13.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene3.line14.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene4(ResourceBundle bundleDialog,
                                       Person narrador,
                                       Detective detective,
                                       Comander comander) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene4.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene4.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene4.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene4.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene4.line5.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene4.line6.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene5(ResourceBundle bundleDialog,
                                       Person narrador) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene5.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene5.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene5.line3.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene5a(ResourceBundle bundleDialog,
                                        Detective detective,
                                        Comander comander) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene5a.line1.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5a.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene5a.line3.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5a.line4.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene5a.line5.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5a.line6.text"), 0)
        );
        return new DialogueScene(lines);
    }



    public static DialogueScene scene5b(ResourceBundle bundleDialog,
                                        Detective detective,
                                        Comander comander) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene5b.line1.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5b.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene5b.line3.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5b.line4.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene5b.line5.text"), 0),
                new DialogueLine(comander, bundleDialog.getString("scene5b.line6.text"), 0)
        );
        return new DialogueScene(lines);
    }


    public static DialogueScene scene6(ResourceBundle bundleDialog,
                                       Person narrador) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene6.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line5.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line6.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line7.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line8.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line9.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line10.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line11.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene6.line12.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene7(ResourceBundle bundleDialog,
                                       Person narrador) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene7.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line5.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line6.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line7.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line8.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line9.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line10.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line11.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene7.line12.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene8(ResourceBundle bundleDialog,
                                       Person narrador,
                                       Detective detective,
                                       Mayor mayor) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene8.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene8.line2.text"), 0),
                new DialogueLine(mayor, bundleDialog.getString("scene8.line3.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene8.line4.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene8a(ResourceBundle bundleDialog,
                                        Detective detective,
                                        Mayor mayor) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene8a.line1.text"), 0),
                new DialogueLine(mayor, bundleDialog.getString("scene8a.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene8a.line3.text"), 0),
                new DialogueLine(mayor, bundleDialog.getString("scene8a.line4.text"), 0)
        );
        return new DialogueScene(lines);
    }


    public static DialogueScene scene8b(ResourceBundle bundleDialog,
                                        Detective detective,
                                        Mayor mayor) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene8b.line1.text"), 0),
                new DialogueLine(mayor, bundleDialog.getString("scene8b.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene8b.line3.text"), 0),
                new DialogueLine(mayor, bundleDialog.getString("scene8b.line4.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene scene9(ResourceBundle bundleDialog,
                                       Person narrador) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene9.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene9.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene9.line3.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene10(ResourceBundle bundleDialog,
                                        Detective detective,
                                        DetectiveAssistant assistant) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene10.line1.text"), 0),
                new DialogueLine(assistant, bundleDialog.getString("scene10.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10.line3.text"), 0),
                new DialogueLine(assistant, bundleDialog.getString("scene10.line4.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10.line5.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene10a(ResourceBundle bundleDialog,
                                         Detective detective,
                                         DetectiveAssistant assistant) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene10a.line1.text"), 0),
                new DialogueLine(assistant, bundleDialog.getString("scene10a.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10a.line3.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene10b(ResourceBundle bundleDialog,
                                         Detective detective,
                                         DetectiveAssistant assistant) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene10b.line1.text"), 0),
                new DialogueLine(assistant, bundleDialog.getString("scene10b.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10b.line3.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene10c(ResourceBundle bundleDialog,
                                         Detective detective,
                                         Criminologist criminologist) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene10c.line1.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene10c.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10c.line3.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene10c.line4.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene10n(ResourceBundle bundleDialog,
                                         Detective detective,
                                         Novelist novelist) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene10n.line1.text"), 0),
                new DialogueLine(novelist, bundleDialog.getString("scene10n.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene10n.line3.text"), 0),
                new DialogueLine(novelist, bundleDialog.getString("scene10n.line4.text"), 0)
        );

        return new DialogueScene(lines);
    }


    public static DialogueScene scene11(ResourceBundle bundleDialog,
                                        Detective detective,
                                        Person narrador) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("scene11.line1.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene11.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene11.line3.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene11.line4.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("scene11.line5.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene11a(ResourceBundle bundleDialog,
                                         Detective detective,
                                         Criminologist criminologist) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene11a.line1.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene11a.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene11a.line3.text"), 0),
                new DialogueLine(criminologist, bundleDialog.getString("scene11a.line4.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene scene11b(ResourceBundle bundleDialog,
                                         Detective detective,
                                         Novelist novelist) {

        List<DialogueLine> lines = List.of(
                new DialogueLine(detective, bundleDialog.getString("scene11b.line1.text"), 0),
                new DialogueLine(novelist, bundleDialog.getString("scene11b.line2.text"), 0),
                new DialogueLine(detective, bundleDialog.getString("scene11b.line3.text"), 0),
                new DialogueLine(novelist, bundleDialog.getString("scene11b.line4.text"), 0)
        );

        return new DialogueScene(lines);
    }

    public static DialogueScene sceneFinalA(ResourceBundle bundleDialog,
                                            Person narrador) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalA.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalA.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalA.line3.text"), 0)
        );
        return new DialogueScene(lines);
    }

    public static DialogueScene sceneFinalB(ResourceBundle bundleDialog,
                                            Person narrador) {
        List<DialogueLine> lines = List.of(
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalB.line1.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalB.line2.text"), 0),
                new DialogueLine(narrador, bundleDialog.getString("sceneFinalB.line3.text"), 0)
        );
        return new DialogueScene(lines);
    }


}
