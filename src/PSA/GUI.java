package PSA;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class GUI {
    public static void main(String args[]) throws IOException {
        AtomicInteger alignmentInput = new AtomicInteger();
        AtomicInteger braceInput = new AtomicInteger();
        AtomicInteger vertInput = new AtomicInteger();

        //Making the GUI look better
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creating the Frame for the main application
        JFrame mainFrame = new JFrame("Programming Style Autograder");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 350);

        //Create settings frame
        JFrame settingFrame = new JFrame("Settings");
        settingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingFrame.setSize(300, 300);

        //Settings menu
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JLabel indLabel = new JLabel("Alignment Grading Setting");
        JLabel bpLabel = new JLabel("Brace Grading Setting");
        JLabel vwsLabel = new JLabel("Vertical White Space Grading Setting");

        JLabel[] labels = new JLabel[]{indLabel, bpLabel, vwsLabel};
        for(JLabel j : labels)
        {
            j.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            j.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        }

        String[] indList = {"Tabs","4 Spaces","5 Spaces"};
        String[] bpList = {"Grade for Consistency","Grade for Same-line Braces","Grade for Next-line Braces"};
        String[] vwsList = {"Off","On"};

        JComboBox indBox = new JComboBox(indList);
        JComboBox bpBox = new JComboBox(bpList);
        JComboBox vwsBox = new JComboBox(vwsList);

        panel.add(indLabel);
        panel.add(indBox);
        panel.add(bpLabel);
        panel.add(bpBox);
        panel.add(vwsLabel);
        panel.add(vwsBox);
        panel.add(saveButton);

        //Buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton fileButton = new JButton("Upload File");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(fileButton, c);

        JButton settingsButton = new JButton("Settings");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        buttonPanel.add(settingsButton, c);

        JButton gradeButton = new JButton("Grade File");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        buttonPanel.add(gradeButton, c);

        //Logo
        BufferedImage myPicture = ImageIO.read(new File("C:\\icons\\school\\csc530\\logo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(picLabel, c);

        //File Directory
        JTextField currFileChosenLabel = new JTextField("File Directory Chosen");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 15;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        buttonPanel.add(currFileChosenLabel, c);

        //file chooser and grading
        File userDir = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser(userDir);
        fileChooser.setDialogTitle("Open File to Be Graded");

        //Upload file button action listener
        fileButton.addActionListener(e -> {
            fileChooser.showOpenDialog(null);
            File fileToBeGraded = fileChooser.getSelectedFile();
            currFileChosenLabel.setText(fileToBeGraded.toString());
        });

        //Grade Button action listener
        gradeButton.addActionListener(e -> {
            FileWriter resultFile = null;
            try {
                resultFile = new FileWriter("Results.txt");

                //Grade Alignment
                resultFile.write(AlignmentGrading.gradeAlignment(currFileChosenLabel.getText(),alignmentInput.get()) + "\n");

                //Grade Braces
                resultFile.write(BraceGrading.gradeBraces(currFileChosenLabel.getText(),braceInput.get()) + "\n");

                //Grade Vertical White Space
                if(vertInput.get() == 1)
                {
                    resultFile.write(VerticalSpaceGrading.gradeVerticalWhitespace(currFileChosenLabel.getText()));
                }

                resultFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //listener for settings
        settingsButton.addActionListener(e -> settingFrame.setVisible(true));
        saveButton.addActionListener(e -> {
            //Save selected settings here

            //Alignment Grading Setting
            if(indBox.getSelectedIndex() == 0)
            {
                alignmentInput.set(0);
            }
            else if(indBox.getSelectedIndex() == 1)
            {
                alignmentInput.set(1);
            }
            else
            {
                alignmentInput.set(2);
            }

            //Brace Grading Setting
            if(bpBox.getSelectedIndex() == 0)
            {
                braceInput.set(0);
            }
            else if(bpBox.getSelectedIndex() == 1)
            {
                braceInput.set(1);
            }
            else
            {
                braceInput.set(2);
            }

            //Vertical White Space Grading Setting
            if(vwsBox.getSelectedIndex() == 0)
            {
                vertInput.set(0);
            }
            else if(vwsBox.getSelectedIndex() == 1)
            {
                vertInput.set(1);
            }

            settingFrame.setVisible(false);
            //System.out.println("Alignment Setting: " + alignmentInput);
            //System.out.println("Brace Setting: " + braceInput);
            //System.out.println("Vertical White Space Setting: " + vertInput);
        });

        //Adding Components to the frame.
        //mainFrame.getContentPane().add(BorderLayout.NORTH, mb);
        mainFrame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        settingFrame.getContentPane().add(BorderLayout.CENTER,panel);
        mainFrame.setVisible(true);

    }
}