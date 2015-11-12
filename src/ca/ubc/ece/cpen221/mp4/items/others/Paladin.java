package ca.ubc.ece.cpen221.mp4.items.others;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
/**
 * Paladin class. An AbstractArenaAnimal
 * @author danger
 *
 */
public class Paladin extends AbstractMedievalCreature {
    private static final int INITIAL_ENERGY = 190;
    private static final int MAX_ENERGY = 260;
    private static final int STRENGTH = 160;
    private static final int MIN_BREEDING_ENERGY = 100;
    private static final int VIEW_RANGE = 7;
    private static final int COOLDOWN = 3;
    private static final ImageIcon paladinImage = Util.loadImage("paladin.gif");
    
    private final AI ai;
    /**
     * Create a new {@link Paladin} with at
     * <code> Location </code>. The <code> Loation
     * </code> must be valid and empty.
     * @param Location
     *            : the location where this Paladin will be created
     */
    public Paladin(AI paladinAI, Location initialLocation) {
        this.setMAX_MAGIC(300);
        this.setBravery(65);
        Random random = new Random();
        this.setIsEvil(false);
        this.setMagic();
        this.setTreasure(random.nextInt(140));
        
        this.setAI(paladinAI);
        this.setImage(paladinImage);
        this.setEnergy(INITIAL_ENERGY);
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setLocation(initialLocation);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        
        this.ai = paladinAI;
    }

    @Override
    public LivingItem breed() {
        Paladin child = new Paladin(this.ai, getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Paladin";
    }
}