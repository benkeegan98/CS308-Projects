package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Parses attack parameters from the XML and builds a new attack strategy using those parameters.
 */
public class AttackBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.attack.";
    public static final String DEFAULT_TYPE = "NoAttack";

    private String myType;
    private Integer myBulletPrototypeId;
    private Double myAttackRate;
    private ArrayList<Double> myShootingPositions;

    /**
     * Parses string ID of the bullet prototype a sprite will shoot into an integer
     * @param bulletPrototypeId a string from the XML that defines the ID of the bullet prototype a sprite will shoot.
     * @return the attack builder itself
     * @throws GameEngineException
     */
    public AttackBuilder setBulletPrototypeId(String bulletPrototypeId) throws GameEngineException {
        try {
            myBulletPrototypeId = Integer.parseInt(bulletPrototypeId);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    /**
     * Parses string that defines how often a sprite will attack into an double
     * @param attackRate a string from the XML that defines how often a sprite will attack
     * @return the attack builder itself
     * @throws GameEngineException
     */
    public AttackBuilder setAttackRate(String attackRate) throws GameEngineException {
        try {
            myAttackRate = Double.parseDouble(attackRate);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    /**
     * Saves a string that defines the type of attack strategy the builder will build
     * @param type a string that defines the type of attack strategy the builder will build
     * @return the attack builder itself
     */
    public AttackBuilder setType(String type) {
        myType = type;
        return this;
    }

    /**
     * Parse shootingPositionsString into an ArrayList of doubles that represent all angles from which an attack strategy
     * my shoot.
     * @param shootingPositionsString a string that represents all angles from which an attack strategy may shoot; the
     *                                array it represents can be of any size.
     * @return the attack builder itself
     * @throws GameEngineException
     */
    public AttackBuilder setShootingPositions(String shootingPositionsString) throws GameEngineException {
        ArrayList<Double> gunsParsed = new ArrayList<Double>();
        try{
            String[] shootingPositionsSplit = shootingPositionsString.strip().split(",");
            for(String gun : shootingPositionsSplit){
                gunsParsed.add(Double.parseDouble(gun));
            }
            myShootingPositions = gunsParsed;
        } catch(NumberFormatException e){
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    /**
     * @return the currently bullet prototype id; no default is set
     */
    public Integer getBulletPrototypeId() {
        return myBulletPrototypeId;
    }

    /**
     * @return the currently set attack rate; no default is set
     */
    public double getAttackRate() {
        return myAttackRate;
    }

    /**
     * @return the currently set type; if none has been set, does not return the default until AFTER build() is called
     */
    public String getType() {
        return myType;
    }

    /**
     * @return the currently set shooting positions; no default is set
     */
    public ArrayList<Double> getMyShootingPositions() {
        return myShootingPositions;
    }

    /**
     * Calls an attack strategy constructor of the specified type. If none is set by the XML, it uses the default.
     * @return a strategy of a specified type with the parameters previously parsed
     * @throws GameEngineException
     */
    @Override
    public AttackStrategy build() throws GameEngineException {
        if (myType==null) {
            myType = DEFAULT_TYPE;
        }
        try{
            return (AttackStrategy) Class.forName(CLASS_PATH + myType).getConstructor(AttackBuilder.class).newInstance(this);
        } catch(Exception e){
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
    }
}
