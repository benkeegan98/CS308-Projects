package voogasalad.gameengine.executors.exceptions;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class GameEngineException extends Exception {

    private final Throwable myThrowableEx;
    private final String myMessageKey;

    /**
     * Purpose:
     * Assumptions:
     * @param e
     * @param exceptionMessageKey
     */
    public GameEngineException(Throwable e, String exceptionMessageKey) {
        myThrowableEx = e;
        myMessageKey = exceptionMessageKey;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param exceptionMessageKey
     */
    public GameEngineException(String exceptionMessageKey) {
        myThrowableEx = new Throwable();
        myMessageKey = exceptionMessageKey;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getMessage() {
        return myMessageKey;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Throwable getException() {
        return myThrowableEx;
    }
}
