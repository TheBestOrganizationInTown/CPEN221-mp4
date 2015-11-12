package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.vehicles.*;

public final class RefuelCommand implements Command{
    private final Vehicle vehicle;
    
    
    /**
     * Construct a {@link RefuelCommand}, where <code>vehicle</code> is the
     * vehicle being refueled. Vehicle must have speed and acceleration zero. 
     * This command will restore the vehcle's fuel level to maximum.
     *
     * @param vehicle
     *            the vehicle to be refueled
     */
    public RefuelCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
       if (vehicle.isDead()) {
           return;
       }
       if ( this.vehicle.getSpeed() != 0 || this.vehicle.getAcceleration() != 0 ) {
           throw new InvalidCommandException("Invalid RefuelCommand: Cannot be moving while refueling");
       } 
        this.vehicle.refuel();

}
}
