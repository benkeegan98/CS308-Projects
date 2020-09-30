package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.sprites.strategies.cost.CostStrategy;
import voogasalad.gameengine.executors.sprites.strategies.effect.EffectStrategy;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;
import voogasalad.gameengine.executors.utils.SpriteArchetype;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;

public class SpriteBuilder {

    public static final int DEFAULT_SIZE = 20;
    public static final SpriteArchetype DEFAULT_ARCHETYPE = SpriteArchetype.UNCLASSIFIED;

    private int mySpriteId;
    private double myWidth;
    private double myHeight;
    private double myXPos;
    private double myYPos;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;
    private AttackStrategy myAttackStrategy;
    private RotationStrategy myRotationStrategy;
    private CostStrategy myCostStrategy;
    private EffectStrategy myEffectStrategy;
    private String myImagePath;
    private SpriteArchetype myArchetype;
    private int myPrototypeId;

    public SpriteBuilder setX(double xPos) {
        myXPos = xPos;
        return this;
    }

    public double getX() {
        return myXPos;
    }

    public SpriteBuilder setPrototypeId(int prototypeId) {
        myPrototypeId = prototypeId;
        return this;
    }

    public SpriteBuilder setPrototypeId(String prototypeId) throws GameEngineException {
        try {
            Integer id = Integer.parseInt(prototypeId);
            myPrototypeId = id;
            return this;
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "AttemptedToCreateSpriteWithInvalidPrototypeId");
        }
    }

    public int getPrototypeId() {
        return myPrototypeId;
    }

    public SpriteBuilder setY(double yPos) {
        myYPos = yPos;
        return this;
    }

    public double getY() {
        return myYPos;
    }

    public SpriteBuilder setSpriteId(int id) {
        mySpriteId = id;
        return this;
    }

    public int getSpriteId() {
        return mySpriteId;
    }

    public SpriteBuilder setHealthStrategy(HealthStrategy healthStrategy) {
        myHealthStrategy = healthStrategy;
        return this;
    }

    public SpriteBuilder setCostStrategy(CostStrategy costStrategy) {
        myCostStrategy = costStrategy;
        return this;
    }

    public CostStrategy getCostStrategy() {
        return myCostStrategy;
    }

    public SpriteBuilder setEffectStrategy(EffectStrategy effectStrategy) {
        myEffectStrategy = effectStrategy;
        return this;
    }

    public EffectStrategy getEffectStrategy() { return myEffectStrategy; }

    public SpriteBuilder setAttackStrategy(AttackStrategy attackStrategy) {
        myAttackStrategy = attackStrategy;
        return this;
    }

    public AttackStrategy getAttackStrategy() {
        return myAttackStrategy;
    }

    public HealthStrategy getHealthStrategy() {
        return myHealthStrategy;
    }

    public SpriteBuilder setMovementStrategy(MovementStrategy movementStrategy) {
        myMovementStrategy = movementStrategy;
        return this;
    }

    public MovementStrategy getMovementStrategy() {
        return myMovementStrategy;
    }

    public SpriteBuilder setImagePath(String imagePath) {
        myImagePath = imagePath;
        System.out.println("image path set in builder with value: " + myImagePath);
        return this;
    }

    public SpriteBuilder setRotationStrategy(RotationStrategy rotationStrategy) {
        myRotationStrategy = rotationStrategy;
        return this;
    }

    public RotationStrategy getRotationStrategy() {
        return myRotationStrategy;
    }

    public String getImagePath() {
        return myImagePath;
    }

    public SpriteBuilder setWidth(double width) {
        myWidth = width;
        return this;
    }

    public SpriteBuilder setWidth(String width) {
        try {
            Double widthDouble = Double.parseDouble(width);
            myWidth = widthDouble;
            System.out.println("width set in builder with value: " + myWidth);
        } catch (NumberFormatException e) {
            myWidth = DEFAULT_SIZE;
        }
        return this;
    }

    public double getWidth() {
        return myWidth;
    }

    public SpriteBuilder setHeight(double height) {
        myHeight = height;
        return this;
    }

    public SpriteBuilder setHeight(String height){
        try {
            Double heightDouble = Double.parseDouble(height);
            myHeight = heightDouble;
            System.out.println("height set in builder with value: " + myHeight);
        } catch (NumberFormatException e) {
            myHeight = DEFAULT_SIZE;
        }
        return this;
    }

    public double getHeight() {
        return myHeight;
    }

    public SpriteArchetype getSpriteArchetype() {
        return myArchetype;
    }

    public SpriteBuilder setArchetype(SpriteArchetype archetype) {
        myArchetype = archetype;
        return this;
    }

    public SpriteBuilder setArchetype(String archetypeString) {
        SpriteArchetype archetype;
        try {
            archetype = SpriteArchetype.valueOf(archetypeString);
        } catch (IllegalArgumentException e) {
            archetype = DEFAULT_ARCHETYPE;
        }
        myArchetype = archetype;
        return this;
    }

    public Sprite build() throws GameEngineException {
        checkParametersAndAssignDefault();
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        Sprite sprite = spriteProductsFactory.makeSprite(this);
        return sprite;
    }

    private void checkParametersAndAssignDefault() throws GameEngineException {
        if (myMovementStrategy == null) {
            myMovementStrategy = new MovementBuilder().setType("NoMovement").build();
        }
        if (myHealthStrategy == null) {
            myHealthStrategy = new HealthBuilder().build();
        }
        if (myImagePath == null) {
            throw new GameEngineException("SpriteImageUnspecified");
        }
        if (myArchetype == null) {
            myArchetype = SpriteArchetype.UNCLASSIFIED;
        }
        if (myAttackStrategy == null) {
            myAttackStrategy = new AttackBuilder().build();
        } if (myCostStrategy == null) {
            myCostStrategy = new CostBuilder().build();
        } if (myEffectStrategy == null) {
            myEffectStrategy = new EffectBuilder().build();
        } if (myRotationStrategy == null) {
            myRotationStrategy = new RotationBuilder().build();
        }
    }
}





