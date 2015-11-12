package ca.ubc.ece.cpen221.mp4.items.others;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Paladin extends AbstractMedievalCreature {
    private ImageIcon image;

    public Paladin(Location location) {
        this.setINITIAL_ENERGY(190);
        this.setMAX_ENERGY(260);
        this.setSTRENGTH(160);
        this.setVIEW_RANGE(7);
        this.setMIN_BREEDING_ENERGY(100);
        this.setCOOLDOWN(3);
        this.setMAX_MAGIC(300);
        this.setBravery(65);
        Random random = new Random();
        this.setIsEvil(false);
        this.setINITIAL_MAGIC();
        this.setTreasure(random.nextInt(140));
        this.setLocation(location);
        this.image = Util.loadImage("unknown.gif");
    }

    @Override
    public LivingItem breed() {
        Paladin child = new Paladin(getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Paladin";
    }

}