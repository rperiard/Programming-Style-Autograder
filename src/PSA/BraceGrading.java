package PSA;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

/**********************************
 This class will be used to evaluate the brace style of a given java file.
 "Braces" refers to the opening brackets used after a method declaration.
 Braces are being divided into 2 groups: either braces are used on the same line as the method declaration,
 or braces are used on the line following method declaration in their own line.
 Braces will be graded on 3 different options:
    gradeType 0 = A report will be generated the shows number of occasions each brace type was used.
    gradeType 1 = The report will show the number of occasions in which braces were on the line FOLLOWING method declaration.
    gradeType 2 = The report will show the number of occasions in which braces were on the SAME line as method declaration.
 *********************************/

public class BraceGrading
{
    public static String gradeBraces(String fileName, int gradeType) throws IOException
    {
        String result = "";
        boolean containsLetters = false;
        int bracesOnSameLine = 0;
        int bracesOnNextLine = 0;

        //Sets up file to be read by line
        Path filePath = Paths.get(fileName);
        //System.out.println(filePath);
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        String line;
        line = br.readLine();

        while (line != null)
        {
            //Checks if line contains an open brace, indicating a method declaration
            if(line.contains("{"))
            {
                //Checks if line contains a lambda expression; if so, ignores grading for that brace
                if(line.contains("->"))
                {
                    //do nothing
                }
                else
                {
                    //Scans each line containing a brace by eac character; if any letters/numbers are detected, it is assumed that this line had the brace on the same line
                    for (int i = 0; i < line.length(); i++)
                    {
                        if (Character.isLetterOrDigit(line.charAt(i)))
                        {
                            containsLetters = true;
                        }
                    }
                    //Adds to grading counter based on which brace placement the line was determined to have
                    if (containsLetters)
                    {
                        bracesOnSameLine++;
                    }
                    else
                    {
                        bracesOnNextLine++;
                    }
                }
            }
            line = br.readLine();
            containsLetters = false;
        }

        //Closes reader and returns grading results
        br.close();
        if(gradeType == 0)
        {
            result = "Lines with braces on the same line as method declaration: " + bracesOnSameLine + ", lines with braces on the line following method declaration: " + bracesOnNextLine;
        }

        else if(gradeType == 1)
        {
            result = "Errors in brace alignment (braces not on next-line): " + bracesOnSameLine;
        }

        else if(gradeType == 2)
        {
            result = "Errors in brace alignment (braces not on same-line): " + bracesOnNextLine;
        }

        return result;
    }

/*
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String fileInput = sc.nextLine();
        System.out.println("Enter expected indentation style: (0 for tabs, 1 for four spaces, 2 for five spaces");
        String numInput = sc.nextLine();
        int i = Integer.parseInt(numInput);
        try
        {
            System.out.println(gradeBraces(fileInput, i));
        }
        catch (IOException e)
        {
            System.out.println("File input was not found.");
        }
    }
 */
}