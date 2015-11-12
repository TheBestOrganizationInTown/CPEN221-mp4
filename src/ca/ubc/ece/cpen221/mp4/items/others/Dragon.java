package ca.ubc.ece.cpen221.mp4.items.others;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
/**
 * Dragon class. An AbstractArenaAnimal
 * @author danger
 *
 */
public class Dragon extends AbstractMedievalCreature {
 
    private static final int INITIAL_ENERGY = 600;
    private static final int MAX_ENERGY = 900;
    private static final int STRENGTH = 500;
    private static final int MIN_BREEDING_ENERGY = 500;
    private static final int VIEW_RANGE = 12;
    private static final int COOLDOWN = 3;
    private static final ImageIcon dragonImage = Util.loadImage("dragon.gif");
    
    private final AI ai;
    /**
     * Create a new {@link Dragon} with at
     * <code> Location </code>. The <code> Loation
     * </code> must be valid and empty.
     * @param Location
     *            : the location where this Dragon will be created
     */
    public Dragon(AI dragonAI, Location initialLocation) {
        this.setMAX_MAGIC(300);
        this.setBravery(45);
        Random random = new Random();
        this.setIsEvil(random.nextBoolean());
        this.setMagic();
        this.setTreasure(random.nextInt(500));
        this.setAI(dragonAI);
        this.setImage(dragonImage);
        this.setEnergy(INITIAL_ENERGY);
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setLocation(initialLocation);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.ai = dragonAI;
    }

    @Override
    public LivingItem breed() {
        Dragon child = new Dragon(this.ai, getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Dragon";
    }
}
