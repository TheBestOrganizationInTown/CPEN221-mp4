package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;

public class Firefly extends Vehicle{
    private static final ImageIcon FireflyImage = Util.loadImage("unkown.gif");
    
    /**
     * Create a new {@link Firefly} Class spaceship with a {@link VehicleAI} at
     * <code>initialLocation</code> facing the given direction. 
     * The <code> initialLocation </code> must be
     * valid and empty
     * 
     * @param initialLocation
     *            the location where this Firefly Class spaceship will be created
     * @param heading
     *      the direction this vehicle is facing initially           
     *            
     */
    public Firefly(Location initialLocation, Direction heading) {
        this.location = initialLocation;
        this.heading = heading;
        this.refuel();
        this.setMAX_ACCELERATION(10);
        this.setMAX_DECELERATION(-10);
        this.setMAX_FUEL_LEVEL(1000);
        this.setMAX_SPEED(25);
        this.setSTRENGTH(600);
        this.setCOOLDOWN(1);
        this.setVIEW_RANGE(16);
    }
    @Override
    public ImageIcon getImage() {
        return FireflyImage;
    }

    @Override
    public String getName() {
        return "Firefly";
    }

    }



