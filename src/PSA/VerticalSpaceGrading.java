package PSA;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

/**********************************
 This class will be used to grade the amount of extraneous white space detected within a file.
 Any series of more than 1 line of empty white space is considered to be extraneous.
 *********************************/

public class VerticalSpaceGrading
{
    public static String gradeVerticalWhitespace(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        int newLineCounter = 0;
        int lineCount = 1;
        int errors = 0;
        List<String> errorLines = new ArrayList<>();

        //Sets up file to be read by line
        Path filePath = Paths.get(fileName);
        //System.out.println(filePath);
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        String line;
        line = br.readLine();

        //Read each line
        while(line != null)
        {
            //Checks if line is just an empty line of white space
            if(line.trim().length() > 0)
            {
                newLineCounter = 0;
            }

            else
            {
                newLineCounter++;
            }

            //If more than 1 consecutive lines of white space are detected, this is counted as an error
            if(newLineCounter > 1)
            {
                errors++;
                errorLines.add("Errors in vertical white space (extraneous white space usage): " + lineCount + ".");
            }

            line = br.readLine();
            lineCount++;
        }

        //Close reader, build final result report
        br.close();
        result = new StringBuilder("There were " + errors + " cases of extraneous white space usage.\n");
        for(String s : errorLines)
        {
            result.append(s).append("\n");
        }
        return result.toString();
    }
}
