package ca.ubc.ece.cpen221.mp4.commands.medievalCommands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.others.AbstractMedievalCreature;

public class CastMagicSpellCommand implements Command {

    private final AbstractMedievalCreature caster;
    private final Location target;
    
    /**
     * Construct a {@link CastMagicSpellCommand}, where <code> caster </code> is
     * the caster and <code> opponent </code> is the opponent. The target location must be within the
     * view range of the caster. caster must have magic >= 20. The spell will cost the caster 20 magic
     * and the target will lose 20 energy.
     *
     * @param caster
     *            the caster
     * @param target
     *            : the target location
     */
    public CastMagicSpellCommand(AbstractMedievalCreature caster, Location target) {
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        if(caster.getMagic() < 20){
            throw new InvalidCommandException("Invalid CastMagicSpellCommand: Not enough Magic power");
        }
        if (caster.isDead()) {
            return;
        }
        if (!Util.isValidLocation(world, target)) {
            throw new InvalidCommandException("Invalid CastMagicSpellCommand: Invalid target location");
        }
        if (caster.getViewRange() < target.getDistance(caster.getLocation())) {
            throw new InvalidCommandException("Invalid CastMagicSpellCommand: Target location farther than view range");
        }

        for (Item item : world.getItems()) {
            if (item.getLocation().equals(target)) {
                caster.loseMagic(20);
                item.loseEnergy(20);
                break;
            }
        }
    }

}


