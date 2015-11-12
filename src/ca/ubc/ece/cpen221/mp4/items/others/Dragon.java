package ca.ubc.ece.cpen221.mp4.items.others;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Dragon extends AbstractMedievalCreature {
    private ImageIcon image;

    public Dragon(Location location) {
        this.setINITIAL_ENERGY(600);
        this.setMAX_ENERGY(900);
        this.setSTRENGTH(500);
        this.setVIEW_RANGE(12);
        this.setMIN_BREEDING_ENERGY(500);
        this.setCOOLDOWN(3);
        this.setMAX_MAGIC(300);
        this.setBravery(45);
        Random random = new Random();
        this.setIsEvil(random.nextBoolean());
        this.setINITIAL_MAGIC();
        this.setTreasure(random.nextInt(500));
        this.setLocation(location);
        this.image = Util.loadImage("unknown.gif");
    }

    @Override
    public LivingItem breed() {
        Dragon child = new Dragon(getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Dragon";
    }

}
