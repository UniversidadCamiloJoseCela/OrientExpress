package world;

import javax.swing.*;
import java.awt.*;
import dialog.DialogueLine;
import dialog.DialogueScene;

public class MapWindow {
    private static final String[][] MAP = {
            {"💡","🔍","🟩","🟩","🟩","🟩","🟩"},
            {"🟩","🟩","👮","🟩","👩","🟩","🚪"},
            {"🟩","🟩","🟩","🟩","🟩","🟩","🟩"}
    };

    /**
     * Muestra el mapa y, a su derecha, los textos de la escena.
     */
    public static void showMapWithDialogue(DialogueScene scene) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mapa y Diálogo");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Split horizontal: 60% mapa, 40% diálogo
            JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            split.setResizeWeight(0.6);

            // --- Panel de mapa (izquierda) ---
            JPanel mapPanel = new JPanel(new GridLayout(MAP.length, MAP[0].length));
            for (int r = 0; r < MAP.length; r++) {
                for (int c = 0; c < MAP[r].length; c++) {
                    JLabel cell = new JLabel(MAP[r][c], SwingConstants.CENTER);
                    cell.setFont(cell.getFont().deriveFont(32f));
                    cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                    mapPanel.add(cell);
                }
            }
            split.setLeftComponent(mapPanel);

            // --- Panel de diálogo (derecha) ---
            JTextArea dialogArea = new JTextArea();
            dialogArea.setEditable(false);
            dialogArea.setLineWrap(true);
            dialogArea.setWrapStyleWord(true);
            // Volcar todas las líneas de la escena
            for (DialogueLine line : scene.lines()) {
                dialogArea.append(line.speaker().getName()
                        + ": " + line.text() + "\n\n");
            }
            JScrollPane scroll = new JScrollPane(dialogArea);
            split.setRightComponent(scroll);

            frame.add(split, BorderLayout.CENTER);
            frame.pack();
            // Dobla el tamaño para que sea más grande
            Dimension d = frame.getSize();
            frame.setSize(d.width * 2, d.height * 2);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


