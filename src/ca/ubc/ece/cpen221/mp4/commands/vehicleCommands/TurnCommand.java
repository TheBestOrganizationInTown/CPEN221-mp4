package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class TurnCommand implements Command {
    private final Vehicle vehicle;
    private final Direction direction;
    /**
     * Construct a {@link TurnCommand}, where <code>vehicle</code> is the
     * turning vehicle. Must have speed <= 2.
     *
     * @param vehicle
     *            the vehicle that is turning
     * @param direction
     *              the direction to turn          
     * 
     */
    public TurnCommand(Vehicle vehicle, Direction direction) {
        this.vehicle = vehicle;   
        this.direction = direction;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        // If the item is dead, then it will not move.
        if (vehicle.isDead()) {
            return;
        }

        this.vehicle.turn(direction);
        if (this.vehicle.getSpeed() > 0) {
            DriveCommand continueDriving = new DriveCommand(vehicle);
            continueDriving.execute(world);
        }

    }

}


