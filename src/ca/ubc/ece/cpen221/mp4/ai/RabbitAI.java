package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

    private boolean foxFound;
    private Location foxLocation;
    private boolean grassFound;
    private boolean shouldBreed;
    private Item grass;
    private boolean grassAdjacent;

    Set<Item> surroundingItems = new HashSet<Item>();

    public RabbitAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        // TODO: Change this. Implement your own AI rules.
        foxFound = false;
        grassFound = false;
        shouldBreed = false;
        grassAdjacent = false;

        surroundingItems = world.searchSurroundings(animal);
        AIAlgorithms rabbitMind = new AIAlgorithms();

        Iterator<Item> iterator = surroundingItems.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item instanceof Fox) {
                foxFound = true;
                foxLocation = item.getLocation();
            }
            if (item instanceof Grass) {
                grassFound = true;
                grass = item;
            }
            if (item.getLocation().getDistance(animal.getLocation()) == 1 && item instanceof Grass) {
                grassAdjacent = true;
                grass = item;
            }
            if (animal.getEnergy() > animal.getMaxEnergy())
                shouldBreed = true;
        }

        if (foxFound) {
            System.out.println("Running");
            return new MoveCommand(animal, rabbitMind.getFurthestMoveableLocation(foxLocation, animal.getLocation()));
        }
        if (shouldBreed) {
            System.out.println("Breeding");
            return new BreedCommand(animal,
                    rabbitMind.getFurthestMoveableLocation(grass.getLocation(), animal.getLocation()));
        }
        if (grassFound && grassAdjacent) {
            System.out.println("Eating: " + grass);
            return new EatCommand(animal, grass);
        }
        if (grassFound && !grassAdjacent) {
            System.out.println("Going to grass");
            return new MoveCommand(animal, rabbitMind.getClosestMoveableLocation(grass.getLocation(), animal.getLocation()));
        }
        System.out.println("Waiting");
        return new WaitCommand();
    }
}
