package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ai.AIAlgorithms;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
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

    private boolean shouldEat;
    private boolean foxFound;
    private boolean grassFound;
    private boolean shouldBreed;
    private boolean grassAdjacent;
    private boolean tendencySet;
    private Location foxLocation;
    private Location targetLocation;
    private Location surroundingRabbitLocation;
    private Item grassToGo;
    private Item grassToEat;
    private int grassDistance;
    private int itemDistance;
    private int surroundingRabbits;
    private int straightMoves;
    private int tendency;
    private int newTendency;
    private int surroundingGrass;
    private int north;
    private int south;
    private int east;
    private int west;

    Set<Item> surroundingItems = new HashSet<Item>();

    public RabbitAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        foxFound = false;
        shouldBreed = false;
        shouldEat = true;
        grassAdjacent = false;
        surroundingRabbits = -1;
        surroundingGrass = 0;
        grassDistance = 5;
        surroundingItems = world.searchSurroundings(animal);
        tendencySet = false;
        AIAlgorithms rabbitMind = new AIAlgorithms();

        Iterator<Item> iterator = surroundingItems.iterator();

        // Searches the surroundings and instantiates variables based on the
        // outcome
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item instanceof Fox) {
                foxFound = true;
                foxLocation = item.getLocation();
            }
            if (item instanceof Grass) {
                surroundingGrass++;
                itemDistance = rabbitMind.getDistance(animal.getLocation(), item.getLocation());
                // Goes to the closest piece of grass
                if (itemDistance < grassDistance) {
                    grassFound = true;
                    grassToGo = item;
                    grassDistance = itemDistance;
                }
            }
            // If adjacent to grass, grassAdjacent = true
            if (item.getLocation().getDistance(animal.getLocation()) == 1 && item instanceof Grass) {
                grassAdjacent = true;
                grassToEat = item;
            }
            // Checks if there are any rabbits nearby
            if (item instanceof Rabbit) {
                surroundingRabbits++;
                surroundingRabbitLocation = item.getLocation();
            }
        }

        if (animal.getEnergy() == animal.getMaxEnergy() && surroundingRabbits < 2 && surroundingGrass > 1)
            shouldBreed = true;
        if (surroundingRabbits > 0 || surroundingGrass < 2 || animal.getEnergy() < animal.getMaxEnergy() / 8)
            shouldEat = false;
        if (surroundingRabbits == 0)
            shouldEat = true;

        // Runs away if conditions are met
        if (foxFound && animal.getLocation().getDistance(foxLocation) < 3) {
            targetLocation = rabbitMind.getFurthestMoveableLocation(foxLocation, animal.getLocation());
            if (rabbitMind.checkValidity(world, animal, targetLocation)) {
                return new MoveCommand(animal, targetLocation);
            }
        }

        // Breeds if conditions are met
        if (shouldBreed) {
            tendency = rabbitMind.getRandomNumber();
            targetLocation = rabbitMind.getRandomBreedingLocation(world, animal);
            if (rabbitMind.checkValidity(world, animal, targetLocation)) {
                return new BreedCommand(animal, targetLocation);
            }

        }

        // Attempts to spread out if rabbits are nearby
        if (!shouldEat && surroundingRabbits > 0) {
            targetLocation = rabbitMind.getFurthestMoveableLocation(surroundingRabbitLocation, animal.getLocation());
            if (rabbitMind.checkValidity(world, animal, targetLocation)) {
                return new MoveCommand(animal, targetLocation);
            }

        }

        // Eats the piece of grass if conditions are met
        if (grassFound && grassAdjacent && shouldEat) {
            grassFound = false;
            return new EatCommand(animal, grassToEat);
        }

        // Goes to closest piece of grass if conditions are met
        if (grassFound && !grassAdjacent && shouldEat) {
            targetLocation = rabbitMind.getClosestMoveableLocation(grassToGo.getLocation(), animal.getLocation());
            if (rabbitMind.checkValidity(world, animal, targetLocation)) {
                return new MoveCommand(animal, targetLocation);
            }
        }

        // If none of above actions are performed go in a random straight line
        if (straightMoves > 0 && tendencySet) {
            straightMoves--;
            switch (tendency) {
            case 0:
                south++;
                System.out.println("South " + south);
                targetLocation = rabbitMind.getSouth(animal);
            case 1:
                south++;
                System.out.println("South " + south);
                targetLocation = rabbitMind.getSouth(animal);
            case 2:
                east++;
                System.out.println("East " + east);
                targetLocation = rabbitMind.getEast(animal);
            case 3:
                west++;
                System.out.println("West " + west);
                targetLocation = rabbitMind.getWest(animal);
            case 4:
                System.out.println("North " + north);
                north++;
                targetLocation = rabbitMind.getNorth(animal);
            }
            if (rabbitMind.checkValidity(world, animal, targetLocation)) {
                return new MoveCommand(animal, targetLocation);
            }
        }
        
        
        newTendency--;
        straightMoves = 7; //Amount of times to go in that straight direction
        
        //If straight direction is invalid choose a new direction
        if (newTendency < 0) {
            tendency = rabbitMind.getRandomNumber();
            newTendency = 2;
            tendencySet = true;
        }

        //If cannot do anything go in a random direction once
        Location randomLocation = rabbitMind.getRandomMoveLocation(world, animal);
        if (rabbitMind.checkValidity(world, animal, randomLocation))
            return new MoveCommand(animal, randomLocation);
        
        //If no moves possible, just wait
        return new WaitCommand();
    }
}
