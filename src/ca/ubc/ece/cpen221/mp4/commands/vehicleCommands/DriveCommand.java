package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class DriveCommand implements Command {
    private Vehicle vehicle;

    public DriveCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute(World w) throws InvalidCommandException {
        int speed = this.vehicle.getSpeed();
        Location loc = this.vehicle.getLocation();
        Direction directionToMove = this.vehicle.getHeading();
        Location target;
        for (int index = 0; index < speed; index++) {
            switch (directionToMove) {
            case NORTH:
                target = new Location(loc.getX(), loc.getY() + 1);
                break;
            case SOUTH:
                target = new Location(loc.getX(), loc.getY() - 1);
                break;
            case EAST:
                target = new Location(loc.getX() + 1, loc.getY());
                break;
            case WEST:
                target = new Location(loc.getX() - 1, loc.getY());
                break;
            default:
                target = loc;
            }
            find out what's at target location and destroy it, or be destroyed
            MoveCommand move = new MoveCommand(this.vehicle, target);
            move.execute(w);
        }

    }

}
