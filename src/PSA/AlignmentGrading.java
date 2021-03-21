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
    public static String gradeAlignment(String fileName, int gradeType) throws IOException {
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
            //System.out.println(line);
            tabCount = 0;

            if (line.contains("}"))
            {
                currentIndentLevel--;
            }

            for (int i = 0; i < line.length(); i++)
            {

                if(line.charAt(i) == '\t')
                {
                    tabCount++;
                }
            }
            //System.out.println("Tab count " + tabCount);
            //System.out.println("Expected tabs: " +currentIndentLevel);
            //System.out.println("Current line: " + lineCount);

            if(tabCount != currentIndentLevel)
            {
                errors++;
                System.out.println("Alignment error on line " + lineCount);
            }

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
        while (scanner.hasNextLine())
        {
            fileText = fileText + "\n" + scanner.nextLine();
        }
        fileText = fileText + " ";
        char[] charArray = fileText.toCharArray();
        //System.out.println(charArray);

        if (gradeType == 0)
        {
            for (int i = 0; i < charArray.length; i++)
            {
                if(charArray[i] == '{')
                {
                    currentIndentLevel++;
                }

                else if (charArray[i] == '}')
                {
                    currentIndentLevel--;
                }

                else if (charArray[i] == '\n')  //if (!String.valueOf(charArray[i]).matches("."))
                {
                    System.out.println("Current line is " + lineCount);
                    lineCount++;
                    if(currentIndentLevel > 0)
                    {
                        for(int j = i+1; j < i+currentIndentLevel+1; j++)
                        {
                            System.out.println("Current indent: " + currentIndentLevel);
                            //System.out.println("Current character is " + charArray[j]);
                            if(charArray[j] == '{')
                            {
                                currentIndentLevel++;
                            }

                            else if (charArray[j] == '}')
                            {
                                currentIndentLevel--;
                            }

                            else if(charArray[j] != '\t')
                            {
                                System.out.println("Error on line " + lineCount);
                                errors++;
                            }
                        }
                    }
                }
            }
        }

        String result = "This file had improper alignment in " + errors + " lines of code.";
        return result;
    }*/

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
}
