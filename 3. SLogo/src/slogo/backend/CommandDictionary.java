package slogo.backend;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandDictionary {
    private final static String DEFAULT_RESOURCE_PACKAGE = "English";
    private final static String DEFAULT_RESOURCE_DIR = "resources/languages/";
    private ResourceBundle commandResources;
    private String language;
    private Map<String, String> commandMap;

    public CommandDictionary() {
        commandResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_DIR + DEFAULT_RESOURCE_PACKAGE);
        makeCommandMap(commandResources);
        this.language = DEFAULT_RESOURCE_PACKAGE;
    }

    private void makeCommandMap(ResourceBundle rb) {
        this.commandMap = new HashMap<>();
        Enumeration<String> commandNames = commandResources.getKeys();
        while (commandNames.hasMoreElements()) {
            String commandName = commandNames.nextElement();
            String[] alternativeWrods = commandResources.getString(commandName).split("\\|");
            for (String word : alternativeWrods) {
                if (word.startsWith("\\")) word = word.substring(1);
                commandMap.put(word, commandName);
            }
        }
//        for (Map.Entry<String,String> e: commandMap.entrySet()){
//            System.out.println(e.getKey()+"  "+e.getValue());
//        }
    }

    public String getTranslationInEnglish(String command) {
        if (!this.commandMap.containsKey(command.toLowerCase())) {
            throw new RuntimeException("error translating command: " + command + " Cannot find command in this language.");
            //TODO: pass to front end

        } else return this.commandMap.get(command.toLowerCase());
    }

    public void changeLanguage(String language) {
        this.commandResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_DIR + language);
        makeCommandMap(commandResources);
        this.language = language;
    }

}
