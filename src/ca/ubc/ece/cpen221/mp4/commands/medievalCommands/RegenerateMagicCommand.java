package ca.ubc.ece.cpen221.mp4.commands.medievalCommands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.others.AbstractMedievalCreature;

public class RegenerateMagicCommand implements Command {
    private final AbstractMedievalCreature creature;
       
    public RegenerateMagicCommand(AbstractMedievalCreature creature){
        this.creature = creature;
    }
    @Override
    public void execute(World world) throws InvalidCommandException {
        if (creature.isDead()) {
            return;
        }
        this.creature.increaseMagic(50);
        WaitCommand rest = new WaitCommand();
        rest.execute(world);
      
    }


}
