package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.BearAI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal{
        private BearAI ai;
        private ImageIcon image;
    
    public Bear( Location location){
        this.setINITIAL_ENERGY(150);
        this.setMAX_ENERGY(200);
        this.setSTRENGTH(150);
        this.setVIEW_RANGE(5);
        this.setMIN_BREEDING_ENERGY(70);
        this.setCOOLDOWN(4);
        this.setLocation(location);
        this.image = Util.loadImage("bear.gif");

    }
    @Override
    public LivingItem breed() {
        Bear child = new Bear( getLocation());
        child.setEnergy(getEnergy() / 2);
        this.setEnergy(getEnergy() / 2);
        return child;
    
   }
    
    @Override
    public String getName() {
       return "Bear";
    }
    
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }
}
