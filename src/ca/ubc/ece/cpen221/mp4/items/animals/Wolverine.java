package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * Wolverine class. An AbstractArenaAnimal
 * 
 * @author danger
 *
 */
public class Wolverine extends AbstractArenaAnimal {

    private static final int INITIAL_ENERGY = 120;
    private static final int MAX_ENERGY = 160;
    private static final int STRENGTH = 140;
    private static final int MIN_BREEDING_ENERGY = 40;
    private static final int VIEW_RANGE = 5;
    private static final int COOLDOWN = 3;
    private static final ImageIcon wolverineImage = Util.loadImage("wolverine.gif");

    private final AI ai;

    /**
     * Create a new {@link Wolverine} with at <code> Location </code>. The
     * <code> Loation
     * </code> must be valid and empty.
     * 
     * @param Location
     *            : the location where this Wolverine will be created
     */
    public Wolverine(AI wolverineAI, Location initialLocation) {        
        this.setAI(wolverineAI);
        this.setImage(wolverineImage);
        this.setEnergy(INITIAL_ENERGY);
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setLocation(initialLocation);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        
        this.ai = wolverineAI;

    }

    @Override
    public LivingItem breed() {
        Wolverine child = new Wolverine(this.ai, getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;

    }

    @Override
    public String getName() {
        return "Wolverine";
    }

}
