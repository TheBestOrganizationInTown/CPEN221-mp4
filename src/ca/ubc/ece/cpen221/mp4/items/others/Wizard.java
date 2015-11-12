package ca.ubc.ece.cpen221.mp4.items.others;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Wizard extends AbstractMedievalCreature {
    private ImageIcon image;

    public Wizard(Location location) {
        this.setINITIAL_ENERGY(400);
        this.setMAX_ENERGY(600);
        this.setSTRENGTH(300);
        this.setVIEW_RANGE(20);
        this.setMIN_BREEDING_ENERGY(500);
        this.setCOOLDOWN(2);
        this.setLocation(location);
        this.setMAX_MAGIC(300);
        this.setBravery(45);
        Random random = new Random();
        this.setIsEvil(random.nextBoolean());
        this.setINITIAL_MAGIC();
        this.setTreasure(random.nextInt(80));
        this.image = Util.loadImage("unknown.gif");
    }

    @Override
    public LivingItem breed() {
        Wizard child = new Wizard(getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Wizard";
    }

}