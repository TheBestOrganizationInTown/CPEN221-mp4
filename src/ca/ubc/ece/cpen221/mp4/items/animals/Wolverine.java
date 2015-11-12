package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * Wolverine class. An AbstractArenaAnimal
 * @author danger
 *
 */
public class Wolverine extends AbstractArenaAnimal{

    private ImageIcon image;
    /**
     * Create a new {@link Wolverine} with at
     * <code> Location </code>. The <code> Loation
     * </code> must be valid and empty.
     * @param Location
     *            : the location where this Wolverine will be created
     */
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
