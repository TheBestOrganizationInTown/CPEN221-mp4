package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class BrakeCommand implements Command {
    private final Vehicle vehicle;

    /**
     * Construct a {@link BrakeCommand}, where <code>vehicle</code> is the
     * braking vehicle. 
     *
     * @param vehicle
     *            the vehicle that is braking
     * 
     */
    public BrakeCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        // If the item is dead, then it will not move.
        if (vehicle.isDead()) {
            return;
        }

        this.vehicle.brake();
        if (this.vehicle.getSpeed() > 0) {
            DriveCommand continueDriving = new DriveCommand(vehicle);
            continueDriving.execute(world);
        }

    }

}
