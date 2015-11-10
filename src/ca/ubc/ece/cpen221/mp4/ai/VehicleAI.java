package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public abstract class VehicleAI extends AbstractAI{

    public Command getNextAction(World world, Vehicle vehicle) {
        // TODO Auto-generated method stub
        return new WaitCommand();
    }

}
