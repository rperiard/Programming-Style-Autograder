package PSA.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class GUI {
    public static void main(String args[]) {

        //Making the GUI look better
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creating the Frame for the main application
        JFrame mainFrame = new JFrame("Programming Style Autograder");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);

        //Create settings frame
        JFrame settingFrame = new JFrame("Settings");
        settingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingFrame.setSize(400, 500);

        //Settings menu
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JButton saveButton = new JButton("Save");

        JLabel indLabel = new JLabel("Grading Settings");
        JLabel bpLabel = new JLabel("Grading Settings");
        JLabel vwsLabel = new JLabel("Grading Settings");
        JLabel ncLabel = new JLabel("Grading Settings");
        JLabel comLabel = new JLabel("Grading Settings");

        String[] indList = {"one","two","Three"};
        String[] bpList = {"one","two","Three"};
        String[] vwsList = {"one","two","Three"};
        String[] ncList = {"one","two","Three"};
        String[] comList = {"one","two","Three"};

        JComboBox indBox = new JComboBox(indList);
        JComboBox bpBox = new JComboBox(bpList);
        JComboBox vwsBox = new JComboBox(vwsList);
        JComboBox ncBox = new JComboBox(ncList);
        JComboBox comBox = new JComboBox(comList);

        panel.add(indLabel);
        panel.add(indBox);
        panel.add(bpLabel);
        panel.add(bpBox);
        panel.add(vwsLabel);
        panel.add(vwsBox);
        panel.add(ncLabel);
        panel.add(ncBox);
        panel.add(comLabel);
        panel.add(comBox);
        panel.add(saveButton);

        //Creating the MenuBar and adding components
        /*
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Stuff1");
        JMenu m2 = new JMenu("Stuff2");
        mb.add(m1);
        mb.add(m2);
         */

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton fileButton = new JButton("Upload File");
        JButton settingsButton = new JButton("Setings");
        buttonPanel.add(fileButton);
        buttonPanel.add(settingsButton);

        //file chooser
        File userDir = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser(userDir);
        fileChooser.setDialogTitle("Open File to Be Graded");
        fileButton.addActionListener(e -> {
                fileChooser.showOpenDialog(null);
                File fileToBeGraded = fileChooser.getSelectedFile();
                System.out.println(fileToBeGraded.getName());
        });

        //listener for settings
        settingsButton.addActionListener(e -> settingFrame.setVisible(true));
        saveButton.addActionListener(e -> {
            //Save selected settings here
            settingFrame.setVisible(false);
        });

        //Adding Components to the frame.
        //mainFrame.getContentPane().add(BorderLayout.NORTH, mb);
        mainFrame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        settingFrame.getContentPane().add(BorderLayout.CENTER,panel);
        mainFrame.setVisible(true);

    }
}