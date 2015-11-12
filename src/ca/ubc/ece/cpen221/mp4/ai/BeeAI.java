package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class BeeAI extends AbstractAI {
    
    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
    
        
    return new WaitCommand();
    }
}
