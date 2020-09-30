package voogasalad.gameauthoringenvironment.gaeexception;

import java.util.HashMap;
import java.util.Map;

public class SpriteInputException extends RuntimeException implements GaeException {

    Map<String, String> myMap = new HashMap<String, String>();

    public SpriteInputException(Map map) {
        myMap = map;

    }




}
