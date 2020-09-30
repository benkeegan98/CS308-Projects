package slogo.backend;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * This class is used to read and save files. Upon reading a file, the files contents will automatically
 * be ran by the parser. When saving files, files will be saved to the "data" folder.
 */
public class FileSaver {

    private static final String prefix = "data/";
    private UserDefinedCommandsAndVars userCommandAndVars;

    /**
     *
     * @param workspaceObject Any previously set user commands/variables
     */
    public FileSaver(UserDefinedCommandsAndVars workspaceObject) {
        userCommandAndVars = workspaceObject;
    }

    /**
     *
     * @param myController Controller to send error messages to
     * @param name name of the file to be read
     * @return string containing the contents of the read file.
     */
    public static String readFile(Controller myController, String name) {
        try {
            InputStream is = new FileInputStream(prefix + name);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            myController.setErrorText("Error Reading the Specified File! Ensure specified file is in the data folder.");
        }
        return " ";
    }

    /**
     *
     * @param workspaceObject Any previously set user commands/variables
     * @return String containing the contents of any user commands and variables
     */
    public String makeStrings(UserDefinedCommandsAndVars workspaceObject) {
        String fileContent = "";
        Map<String, UserCommand> commandMap = workspaceObject.getCommandMap();
        for (String s : commandMap.keySet()) {
            fileContent += commandMap.get(s).getUserCommandLiteral() + "\n";
        }
        Map<String, Double> variableMap = workspaceObject.getVariableMap();
        for (String s : variableMap.keySet()) {
            fileContent += "make " + s + " " + variableMap.get(s) + "\n";
        }
        System.out.println(fileContent);
        return fileContent;
    }

    /** Writes any input data to a file. Stored in the data folder.
     *
     * @param myController Controller to send any errors to
     * @param name name of the file to save the input data to
     */
    public void writeFile(Controller myController, String name) {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(prefix + name), StandardCharsets.UTF_8))) {
            writer.write(makeStrings(userCommandAndVars));
        } catch (IOException e) {
            myController.setErrorText("Error writing to the file!");
        }
    }

}
