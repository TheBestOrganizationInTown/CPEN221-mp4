package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.BearAI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal {
    private static final int INITIAL_ENERGY = 150;
    private static final int MAX_ENERGY = 200;
    private static final int STRENGTH = 150;
    private static final int MIN_BREEDING_ENERGY = 70;
    private static final int VIEW_RANGE = 5;
    private static final int COOLDOWN = 4;
    private static final ImageIcon bearImage = Util.loadImage("bear.gif");

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
    public Bear(AI bearAI, Location initialLocation) {
        this.setAI(bearAI);
        this.setImage(bearImage);
        this.setEnergy(INITIAL_ENERGY);
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setLocation(initialLocation);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.ai = bearAI;

    }

    @Override
    public LivingItem breed() {
        Bear child = new Bear(this.ai, getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;

    }

    @Override
    public String getName() {
        return "Bear";
    }
}
