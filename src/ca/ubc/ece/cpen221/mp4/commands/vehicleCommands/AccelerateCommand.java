package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class AccelerateCommand implements Command {
    private final Vehicle vehicle;
    private final int increase;
    /**
     * Construct a {@link AccelerateCommand}, where <code>vehicle</code> is the
     * accelerating vehicle. 
     *
     * @param vehicle
     *            the vehicle that is accelerating         
     * 
     */
    public AccelerateCommand(Vehicle vehicle, int increase) {
        this.vehicle = vehicle; 
        this.increase = increase;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        // If the item is dead, then it will not move.
        if (vehicle.isDead()) {
            return;
        }
        this.vehicle.setAcceleration(increase);
        if (this.vehicle.getSpeed()+this.vehicle.getAcceleration() > 0) {
            DriveCommand continueDriving = new DriveCommand(vehicle);
            continueDriving.execute(world);
        }


}}
