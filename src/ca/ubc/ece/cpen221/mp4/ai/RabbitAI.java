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
    private Item grassToGo;
    private Item grassToEat;
    private boolean grassAdjacent;
    private int grassDistance;
    private int itemDistance;
    private Location targetLocation;

    Set<Item> surroundingItems = new HashSet<Item>();

    public RabbitAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        // TODO: Change this. Implement your own AI rules.
        foxFound = false;
        shouldBreed = false;
        grassAdjacent = false;
        grassDistance = 5;

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
                itemDistance = rabbitMind.getDistance(animal.getLocation(), item.getLocation());
                if (itemDistance < grassDistance) {
                    grassFound = true;
                    grassToGo = item;
                    grassDistance = itemDistance;
                }
            }
            if (item.getLocation().getDistance(animal.getLocation()) == 1 && item instanceof Grass) {
                grassAdjacent = true;
                grassToEat = item;
            }
            if (animal.getEnergy() > animal.getMaxEnergy())
                shouldBreed = true;
        }

        if (foxFound) {
            System.out.println("Running");
            targetLocation = rabbitMind.getFurthestMoveableLocation(foxLocation, animal.getLocation());
            if (rabbitMind.checkValidity(world, animal, targetLocation))
                return new MoveCommand(animal, targetLocation);
        }
        if (shouldBreed) {
            System.out.println("Breeding");
            targetLocation = rabbitMind.getRandomBreedingLocation(world, animal);
            if (rabbitMind.checkValidity(world, animal, targetLocation))
                return new BreedCommand(animal, targetLocation);
        }
        if (grassFound && grassAdjacent) {
            System.out.println("Eating");
            grassFound = false;
            return new EatCommand(animal, grassToEat);
        }
        if (grassFound && !grassAdjacent) {
            System.out.println("Going to grass");
            targetLocation = rabbitMind.getClosestMoveableLocation(grassToGo.getLocation(), animal.getLocation());
            if (rabbitMind.checkValidity(world, animal, targetLocation))
                return new MoveCommand(animal, targetLocation);
        }
        System.out.println("Waiting");
        return new WaitCommand();
    }
}
