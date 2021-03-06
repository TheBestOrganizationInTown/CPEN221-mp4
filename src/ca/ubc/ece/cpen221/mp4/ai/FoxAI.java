package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {

    private boolean shouldEat;
    private boolean rabbitFound;
    private boolean shouldBreed;
    private boolean rabbitAdjacent;
    private Location targetLocation;
    private Item rabbitToGo;
    private Item rabbitToEat;
    private int rabbitDistance;
    private int itemDistance;
    private int surroundingFoxes;
    private int straightMoves;
    private int tendency;
    private int newTendency;

    Set<Item> surroundingItems = new HashSet<Item>();

    public FoxAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        shouldBreed = false;
        shouldEat = true;
        rabbitAdjacent = false;
        surroundingFoxes = 0;
        rabbitDistance = 5;
        surroundingItems = world.searchSurroundings(animal);
        AIAlgorithms foxMind = new AIAlgorithms();

        Iterator<Item> iterator = surroundingItems.iterator();

        // Searches the surrounding environment and instantiates variables
        while (iterator.hasNext()) {
            Item item = iterator.next();

            // Eats rabbits that are weak
            if (item instanceof Rabbit && ((Rabbit) item).getEnergy() < 30) {
                itemDistance = foxMind.getDistance(animal.getLocation(), item.getLocation());
                if (itemDistance < rabbitDistance) {
                    rabbitFound = true;
                    rabbitToGo = item;
                    rabbitDistance = new Integer(itemDistance);
                }
            }
            if (item.getLocation().getDistance(animal.getLocation()) == 1 && item instanceof Rabbit) {
                rabbitAdjacent = true;
                rabbitToEat = item;
            }
        }

        if (animal.getEnergy() > animal.getMaxEnergy() * 2 / 3 && surroundingFoxes < 4)
            shouldBreed = true;

        if (surroundingFoxes > 6 && animal.getEnergy() > animal.getMaxEnergy() * 2 / 3)
            shouldEat = false;

        // Breeds if conditions are met
        if (shouldBreed) {
            targetLocation = foxMind.getRandomBreedingLocation(world, animal);
            if (foxMind.checkValidity(world, animal, targetLocation))
                return new BreedCommand(animal, targetLocation);
        }

        // Eats if conditions are met
        if (rabbitFound && rabbitAdjacent && shouldEat) {
            rabbitFound = false;
            return new EatCommand(animal, rabbitToEat);
        }

        // Chases rabbits if conditions are met
        if (rabbitFound && !rabbitAdjacent && shouldEat) {
            targetLocation = foxMind.getClosestMoveableLocation(rabbitToGo.getLocation(), animal.getLocation());
            if (foxMind.checkValidity(world, animal, targetLocation))
                return new MoveCommand(animal, targetLocation);
        }

        // Go in a random straight direction
        if (straightMoves > 0) {
            straightMoves--;
            switch (tendency) {
            case 0:
                targetLocation = foxMind.getNorth(animal);
            case 1:
                targetLocation = foxMind.getSouth(animal);
            case 2:
                targetLocation = foxMind.getEast(animal);
            case 3:
                targetLocation = foxMind.getWest(animal);

            }
            if (foxMind.checkValidity(world, animal, targetLocation))
                return new MoveCommand(animal, targetLocation);
        }
        newTendency--;
        straightMoves = 4; // Number of times to move in that straight direction

        // If invalid 5 times, choose a new direction
        if (newTendency < 0) {
            tendency = foxMind.getRandomNumber();
            newTendency = 5;
        }

        // If invalid randomly choose a direction
        Location randomLocation = foxMind.getRandomMoveLocation(world, animal);
        if (foxMind.checkValidity(world, animal, randomLocation))
            return new MoveCommand(animal, randomLocation);

        // If all moves are invalid, just wait.
        return new WaitCommand();
    }

}
