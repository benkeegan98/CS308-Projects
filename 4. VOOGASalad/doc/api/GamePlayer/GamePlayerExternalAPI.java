package GamePlayer;

public interface GamePlayerExternalAPI{
    // xmlPath - Path to the xml created by the GAE that holds initial game configuration details
    void startGame(String xmlPath);

    //Returns path to current xml being used by the Player
    String getXML();

    //Sets path of xml used by Player to xmlPath
    void loadXML(String xmlPath);
}
