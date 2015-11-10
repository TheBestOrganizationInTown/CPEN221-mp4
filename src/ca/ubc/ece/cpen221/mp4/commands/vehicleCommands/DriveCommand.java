package ca.ubc.ece.cpen221.mp4.commands.vehicleCommands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class DriveCommand implements Command {
    private Vehicle vehicle;

    /**
     * Must have fuel or speed > 0.
     */
    public DriveCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute(World w) throws InvalidCommandException {
        vehicle.setSpeed();
        int speed = this.vehicle.getSpeed();
        Location loc = this.vehicle.getLocation();
        Direction directionToMove = this.vehicle.getHeading();
        Location target;
        for (int index = 0; index < speed; index++) {
            //if the vehicle runs out of fuel it will decelerate, but will continue
            //moving for a little bit
            if (this.vehicle.getFuelLevel() == 0) {
                this.vehicle.brake();
            }
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

            // find out what's at target location and destroy it, or be
            // destroyed

            for (Item item : w.getItems()) {
                if (item.getLocation().equals(target)) {
                    if (this.vehicle.getStrength() > item.getStrength())
                        item.loseEnergy(10000);
                    else if (this.vehicle.getStrength() < item.getStrength())
                        this.vehicle.loseEnergy(10000);
                    else if (this.vehicle.getStrength() == item.getStrength()) {
                        this.vehicle.crash();
                        if (item instanceof Vehicle) {
                            ((Vehicle) item).crash();
                        }
                    }
                    break;
                }
            }
            if (this.vehicle.isDead() || this.vehicle.getSpeed() == 0)
                break;
            MoveCommand move = new MoveCommand(this.vehicle, target);
            move.execute(w);
            // loses more fuel for more acceleration
            // still loses fuel when moving and not accelerating
            this.vehicle.loseFuel(vehicle.getAcceleration() + 1);
        }

    }

}
