package world;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TerminalUI extends JFrame {
    private final JTextArea console;
    private final JTextField inputField;
    private final PipedOutputStream pipedOut;
    private int fontSize = 12;

    public TerminalUI(String title, int w, int h) throws IOException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(w, h);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con botones para cambiar tamaño de fuente
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnIncrease = new JButton("A+\u2191");
        JButton btnDecrease = new JButton("A-\u2193");
        topPanel.add(new JLabel("Tamaño de fuente: ")); topPanel.add(btnDecrease); topPanel.add(btnIncrease);
        add(topPanel, BorderLayout.NORTH);

        // Área de texto para salida
        console = new JTextArea();
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        console.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(console);
        add(scrollPane, BorderLayout.CENTER);

        // Campo de entrada para Scanner
        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        // Botones para ajustar fuente
        // Botones para ajustar fuente directamente con ActionListener
        btnIncrease.addActionListener(e -> adjustFontSize(2));
        btnDecrease.addActionListener(e -> adjustFontSize(-2));

        // Interceptar System.out y System.err con UTF-8 correcto
        OutputStream os = new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            @Override public void write(int b) throws IOException { buffer.write(b); }
            @Override public void write(byte[] b, int off, int len) throws IOException { buffer.write(b, off, len); }
            @Override public void flush() throws IOException {
                byte[] bytes = buffer.toByteArray();
                String text = new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
                SwingUtilities.invokeLater(() -> {
                    console.append(text);
                    console.setCaretPosition(console.getDocument().getLength());
                });
                buffer.reset();
            }
        };
        PrintStream ps = new PrintStream(os, true, "UTF-8");
        System.setOut(ps);
        System.setErr(ps);

        // Hijack System.in via piped streams
        PipedInputStream pipedIn = new PipedInputStream();
        pipedOut = new PipedOutputStream(pipedIn);
        System.setIn(pipedIn);

        // Al presionar Enter, escribimos al pipe para Scanner
        inputField.addActionListener(e -> {
            String line = inputField.getText() + "\n";
            inputField.setText("");
            try {
                pipedOut.write(line.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                pipedOut.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    private void adjustFontSize(int delta) {
        fontSize = Math.max(8, fontSize + delta);
        Font f = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
        console.setFont(f);
        inputField.setFont(f);
    }

    /**
     * Punto de entrada: lanza tu main existente sin modificar.
     */
    public static void launchWithUI(String[] args, Runnable gameMain) throws IOException {
        // Crea la ventana gráfica que intercepta I/O
        new TerminalUI("Consola Gráfica de Juego", 800, 600);
        // Ejecuta tu lógica principal en otro hilo
        new Thread(gameMain).start();
    }
}

