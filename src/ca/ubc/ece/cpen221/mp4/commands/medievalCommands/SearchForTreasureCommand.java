package ca.ubc.ece.cpen221.mp4.commands.medievalCommands;

import java.util.Random;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.others.AbstractMedievalCreature;

public class SearchForTreasureCommand implements Command {

    private final AbstractMedievalCreature creature;
    private final Location targetLocation;

    /**
     * Construct a {@link SearchForTreasureCommand}, where <code>creature</code>
     * is the creature searching for treasure and <code>targetLocation</code> is
     * the location that it is looking in and moving to. The target location
     * must be within <code>creature</code>'s moving range and the target
     * location must be empty.
     *
     * @param creature
     *            the AbstractMedievalCreature that is moving and looking for
     *            treasure
     * @param targetLocation
     *            the location that AbstractMedievalCreature is moving to to
     *            look
     */
    public SearchForTreasureCommand(AbstractMedievalCreature creature, Location targetLocation) {
        this.creature = creature;
        this.targetLocation = targetLocation;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        // If the item is dead, then it will not move.
        if (creature.isDead()) {
            return;
        }
        if (!Util.isValidLocation(world, targetLocation) || !Util.isLocationEmpty(world, targetLocation)) {
            throw new InvalidCommandException("Invalid SearchForTreasureCommand: Invalid/non-empty target location");
        }
        if (creature.getMovingRange() < targetLocation.getDistance(creature.getLocation())) {
            throw new InvalidCommandException(
                    "Invalid SearchForTreasureCommand: Target location farther than moving range");
        }
        Random random = new Random();
        creature.moveTo(targetLocation);
        if (random.nextBoolean())
            creature.addTreasure(random.nextInt(25));
    }

}
