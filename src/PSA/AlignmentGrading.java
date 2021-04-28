package PSA;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Files;

/**********************************
 This class will be used to evaluate the indentation of a given java file.
 "Indentions" in this case are being used to denote the type of spacing used after a method declaration, loop, or comparison.
 The grading method takes two parameters: the name of the file to be graded and the type of indention grading to be used, which is indicated by an integer.
    gradeType 0 = tabs expected
    gradeType 1 = 4 spaces expected
    gradeType 2 = 5 spaces expected
 *********************************/

public class AlignmentGrading
{
    public static String gradeAlignment(String fileName, int gradeType) throws IOException
    {
        int lineCount = 1;
        int errors = 0;
        int currentIndentLevel = 0;
        int tabCount;

        Path filePath = Paths.get(fileName);
        System.out.println(filePath);
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        String line;
        line = br.readLine();

        while (line != null)
        {
            tabCount = 0;

            //Checks for closing brackets to indicate a decrease in expected indents
            if (line.contains("}"))
            {
                currentIndentLevel--;
            }

            //Scans each line and counts the number of tabs present on each line
            for (int i = 0; i < line.length(); i++)
            {
                if(line.charAt(i) == '\t')
                {
                    tabCount++;
                }
            }

            //Increases error count if number of indents does not meet expected
            if(tabCount != currentIndentLevel)
            {
                errors++;
                System.out.println("Alignment error on line " + lineCount);
            }

            //Increases the expected indent level at each opening bracket
            if (line.contains("{"))
            {
                currentIndentLevel++;
            }

            line = br.readLine();
            lineCount++;
        }
        br.close();
        return "Errors: " + errors;
    }

    /*
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String fileInput = sc.nextLine();
        System.out.println("Enter expected indentation style: (0 for tabs, 1 for four spaces, 2 for five spaces");
        String numInput = sc.nextLine();
        try
        {
            System.out.println(gradeAlignment(fileInput, 0));
        }
        catch (IOException e)
        {
            System.out.println("File input was not found.");
        }
    }
     */
}
