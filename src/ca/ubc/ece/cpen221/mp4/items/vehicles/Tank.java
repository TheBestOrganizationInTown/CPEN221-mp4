package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.vehicleCommands.*;


public class Tank extends Vehicle {
    private static final ImageIcon tankImage = Util.loadImage("unkown.gif");
    
        /**
         * Create a new {@link Tank} with a {@link VehicleAI} at
         * <code>initialLocation</code> facing the given direction. 
         * The <code> initialLocation </code> must be
         * valid and empty
         * 
         * @param initialLocation
         *            the location where this Tank will be created
         * @param heading
         *      the direction this vehicle is facing initially           
         *            
         */
        public Tank(Location initialLocation, Direction heading) {
            this.location = initialLocation;
            this.heading = heading;
            this.refuel();
            this.setMAX_ACCELERATION(1);
            this.setMAX_DECELERATION(-2);
            this.setMAX_FUEL_LEVEL(100);
            this.setMAX_SPEED(3);
            this.setSTRENGTH(1000);
            this.setCOOLDOWN(1);
            this.setVIEW_RANGE(16);
        }

        @Override
        public Location getLocation() {
            return location;
        }
        
        @Override
        public String getName() {
            return "Tank";
        }

        @Override
        public void moveTo(Location targetLocation) {
            location = targetLocation;
        }
        @Override
        public void loseEnergy(int energy) {
           this.vehicleTotalled();
            
        }}
