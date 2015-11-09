package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class BrakeCommand implements Command{
    private final Vehicle vehicle;
    private final Location targetLocation;

    /**
     * Construct a {@link BrakeCommand}, where <code>vehicle</code> is the braking
     * vehicle.
     *
     * @param item
     *            the Item that is moving
     * @param targetLocation
     *            the location that Item is moving to
     */
    public BrakeCommand(Vehicle vehicle){
        this.vehicle = vehicle;
        this.vehicle.brake();
        DriveCommand continueDriving = new DriveCommand(vehicle);
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        // If the item is dead, then it will not move.
        if (vehicle.isDead()) {
            return;
        }
        if (!Util.isValidLocation(world, targetLocation) || !Util.isLocationEmpty(world, targetLocation)) {
            throw new InvalidCommandException("Invalid MoveCommand: Invalid/non-empty target location");
        }
        if (item.getMovingRange() < targetLocation.getDistance(item.getLocation())) {
            throw new InvalidCommandException("Invalid MoveCommand: Target location farther than moving range");
        }

        item.moveTo(targetLocation);
    }

}
}
