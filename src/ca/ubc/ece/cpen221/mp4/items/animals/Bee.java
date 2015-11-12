package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link Bee} is an {@link ArenaAnimal} that eats {@link Honey} and can be
 * eaten by {@link Bear}.
 */
public class Bee extends AbstractArenaAnimal {

    private static final int INITIAL_ENERGY = 20;
    private static final int MAX_ENERGY = 40;
    private static final int STRENGTH = 20;
    private static final int MIN_BREEDING_ENERGY = 5;
    private static final int VIEW_RANGE = 2;
    private static final int COOLDOWN = 1;
    private static final ImageIcon beeImage = Util.loadImage("bee.gif");
    
    
    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new {@link Bee} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param initialLocation
     *            : the location where this bee will be created
     */
    public Bee(AI beeAI, Location initialLocation) {
        this.setAI(beeAI);
        this.setImage(beeImage);
        this.setEnergy(INITIAL_ENERGY);
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setLocation(initialLocation);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.ai = beeAI;
        
    }

    @Override
    public LivingItem breed() {
        Bee child = new Bee(this.ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }


    @Override
    public String getName() {
        return "Bee";
    }
}
