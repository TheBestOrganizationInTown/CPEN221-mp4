package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Wolverine extends AbstractArenaAnimal{

    private ImageIcon image;

public Wolverine( Location location){
    this.setINITIAL_ENERGY(120);
    this.setMAX_ENERGY(160);
    this.setSTRENGTH(140);
    this.setVIEW_RANGE(5);
    this.setMIN_BREEDING_ENERGY(40);
    this.setCOOLDOWN(3);
    this.setLocation(location);
    this.image = Util.loadImage("unknown.gif");

}
@Override
public LivingItem breed() {
    Wolverine child = new Wolverine( getLocation());
    child.setEnergy(getEnergy() / 2);
    this.setEnergy(getEnergy() / 2);
    return child;

}

@Override
public String getName() {
   return "Wolverine";
}

}
