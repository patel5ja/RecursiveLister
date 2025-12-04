import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveListerFrame extends JFrame {

    private JTextArea textArea;

    public RecursiveListerFrame() {

        setTitle("Recursive File Lister");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Recursive File Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);


        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);


        startButton.addActionListener(this::startAction);
        quitButton.addActionListener(e -> System.exit(0));
        setVisible(true);

    }

    private void startAction(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
            textArea.setText("");
            listFilesRecursive(selectedDir, "");
        }
    }

    private void listFilesRecursive(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                textArea.append(indent + f.getAbsolutePath() + "\n");
                if (f.isDirectory()) {
                    listFilesRecursive(f, indent + "   ");
                }
            }
        }
    }
}