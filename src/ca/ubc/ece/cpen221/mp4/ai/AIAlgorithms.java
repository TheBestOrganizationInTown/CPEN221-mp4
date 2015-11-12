package ca.ubc.ece.cpen221.mp4.ai;

import java.util.*;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * Algorithms that are used by the various arena animals
 * 
 * @author RobynCastro
 *
 */
public class AIAlgorithms {

    /**
     * Returns the manhattan distance between otherLocation and location
     * 
     * @param otherLocation
     *            location to compare to.
     * @param location
     *            current location
     * @return manhattan distance between the two parameters
     */
    public int getDistance(Location otherLocation, Location location) {
        return (Math.abs(location.getX() - otherLocation.getX()) + Math.abs(location.getY() - otherLocation.getY()));
    }

    /**
     * Returns the locations furthest from the parameter toCompareTo
     * 
     * @param toCompareTo
     *            location to compare to
     * @param Location1
     *            first location to be compared
     * @param Location2
     *            second location to be compared
     * @return furthest location from toCompareTo
     */
    public Location checkFurtherLocation(Location toCompareTo, Location Location1, Location Location2) {
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);

        if (Distance2 > Distance1)
            return Location2;

        return Location1;
    }

    /**
     * Returns the closest location to parameter toCompareTo
     * 
     * @param toCompareTo
     *            location to be compared to
     * @param Location1
     *            first location to be compared
     * @param Location2
     *            second location to be compared
     * @return closest location from toCompareTo
     */
    public Location checkClosestLocation(Location toCompareTo, Location Location1, Location Location2) {
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);
        if (Distance2 < Distance1)
            return Location2;
        return Location1;
    }

    /**
     * Returns a random location adjacent to current location and furthest away
     * from otherLocation
     * 
     * @param otherLocation
     *            location to be further away from
     * @param currentLocation
     *            current location of the item
     * @return furthest location from other location but still adjacent to
     *         currentLocation
     */
    public Location getFurthestMoveableLocation(Location otherLocation, Location currentLocation) {
        Location furthestLocation;
        List<Location> locations = getSurroundingFourLocations(currentLocation);
        List<Location> randomizedLocations = new LinkedList<Location>();

        while (!locations.isEmpty()) {
            int LOW = 0;
            int HIGH = locations.size();
            Random randomize = new Random();

            int randomIndex = randomize.nextInt(HIGH - LOW) + LOW;
            randomizedLocations.add(locations.remove(randomIndex));
        }

        furthestLocation = checkFurtherLocation(otherLocation, randomizedLocations.get(0), randomizedLocations.get(1));
        furthestLocation = checkFurtherLocation(otherLocation, furthestLocation, randomizedLocations.get(2));
        furthestLocation = checkFurtherLocation(otherLocation, furthestLocation, randomizedLocations.get(3));
        return furthestLocation;
    }

    /**
     * Returns a random location adjacent to current location and closest to
     * otherLocation
     * 
     * @param otherLocation
     *            location to be closer to
     * @param currentLocation
     *            current location of the item
     * @return closest location from other location but still adjacent to
     *         currentLocation
     */
    public Location getClosestMoveableLocation(Location otherLocation, Location currentLocation) {
        Location closestLocation;
        List<Location> locations = getSurroundingFourLocations(currentLocation);
        List<Location> randomizedLocations = new LinkedList<Location>();

        while (!locations.isEmpty()) {
            int LOW = 0;
            int HIGH = locations.size();
            Random randomize = new Random();

            int randomIndex = randomize.nextInt(HIGH - LOW) + LOW;
            randomizedLocations.add(locations.remove(randomIndex));
        }

        closestLocation = checkClosestLocation(otherLocation, randomizedLocations.get(0), randomizedLocations.get(1));
        closestLocation = checkClosestLocation(otherLocation, closestLocation, randomizedLocations.get(2));
        closestLocation = checkClosestLocation(otherLocation, closestLocation, randomizedLocations.get(3));

        return closestLocation;
    }

    /**
     * Returns the 8 surrounding locations of the the passed item location
     * 
     * @param itemLocation
     *            location of the item
     * @return 8 surrounding locations of itemLocation in a list
     */
    public List<Location> getSurroundingEightLocations(Location itemLocation) {
        List<Location> Locations = new LinkedList<Location>();
        Location North = new Location(itemLocation.getX(), itemLocation.getY() - 1);
        Location South = new Location(itemLocation.getX(), itemLocation.getY() + 1);
        Location West = new Location(itemLocation.getX() - 1, itemLocation.getY());
        Location East = new Location(itemLocation.getX() + 1, itemLocation.getY());
        Location NEast = new Location(itemLocation.getX() + 1, itemLocation.getY() - 1);
        Location SEast = new Location(itemLocation.getX() + 1, itemLocation.getY() + 1);
        Location NWest = new Location(itemLocation.getX() - 1, itemLocation.getY() - 1);
        Location SWest = new Location(itemLocation.getX() - 1, itemLocation.getY() + 1);
        Locations.add(North);
        Locations.add(South);
        Locations.add(West);
        Locations.add(East);
        Locations.add(NEast);
        Locations.add(SEast);
        Locations.add(NWest);
        Locations.add(SWest);

        return Locations;
    }

    /**
     * Gets North, South, West, and East locations of itemLocation and returns
     * them in a list
     * 
     * @param itemLocation
     *            location of the item
     * @return a list of North,South,West, and East locations
     */
    public List<Location> getSurroundingFourLocations(Location itemLocation) {
        List<Location> Locations = new LinkedList<Location>();
        Location North = new Location(itemLocation.getX(), itemLocation.getY() - 1);
        Location South = new Location(itemLocation.getX(), itemLocation.getY() + 1);
        Location West = new Location(itemLocation.getX() - 1, itemLocation.getY());
        Location East = new Location(itemLocation.getX() + 1, itemLocation.getY());
        Locations.add(North);
        Locations.add(South);
        Locations.add(West);
        Locations.add(East);

        return new LinkedList<Location>(Locations);
    }

    /**
     * Checks whether the passed location is a valid location to target
     * 
     * @param world
     *            the world being used
     * @param animal
     *            the animal doing the targeting
     * @param targetLocation
     *            the target location to be checked
     * @return false if invalid, and true if valid
     */
    public boolean checkValidity(ArenaWorld world, ArenaAnimal animal, Location targetLocation) {
        boolean isValid = true;
        List<Location> occupiedLocations = new LinkedList<Location>();
        Set<Item> surroundingItems = new HashSet<Item>();
        surroundingItems = world.searchSurroundings(animal);
        Iterator<Item> itemIterator = surroundingItems.iterator();
        Util.isValidLocation(world, targetLocation);
        while (itemIterator.hasNext()) {
            Item newItem = itemIterator.next();
            occupiedLocations.add(newItem.getLocation());
        }
        Iterator<Location> locationIterator = occupiedLocations.iterator();
        while (locationIterator.hasNext()) {
            Location occupiedLocation = locationIterator.next();
            if (occupiedLocation.equals(targetLocation))
                isValid = false;
            if (!Util.isValidLocation(world, targetLocation))
                isValid = false;
        }

        return isValid;
    }

    /**
     * Returns the valid adjacent location of the passed animal
     * 
     * @param world
     *            world the animal lives in
     * @param animal
     *            the animal that is checked
     * @param surroundingLocations
     *            the possible locations
     * @return a list of all the valid surrounding locations
     */
    public List<Location> getValidSurroundingLocations(ArenaWorld world, ArenaAnimal animal,
            List<Location> surroundingLocations) {

        Iterator<Location> locationIterator = surroundingLocations.iterator();
        while (locationIterator.hasNext()) {
            Location location = locationIterator.next();
            if (!checkValidity(world, animal, location))
                locationIterator.remove();

        }

        List<Location> validSurroundingLocations = new LinkedList<Location>(surroundingLocations);
        return validSurroundingLocations;
    }

    /**
     * Returns a random location from the passed list of locations
     * 
     * @param world
     *            world the animal lives in
     * @param animal
     *            animal that will use the random location
     * @param locations
     *            list of locations
     * @return a random location from the list, but if list is empty return the
     *         animals current location.
     */
    public Location getRandomLocation(ArenaWorld world, ArenaAnimal animal, List<Location> locations) {
        int HIGH = locations.size();
        int randomIndex = 0;
        Random randomize = new Random();
        if (HIGH > 0) {
            randomIndex = randomize.nextInt(HIGH);
            return locations.get(randomIndex);
        }
        return animal.getLocation();
    }

    /**
     * Return a random adjacent location to the animal
     * 
     * @param world
     *            the world the animal lives in
     * @param animal
     *            the animal to be adjacent to the chosen location
     * @return a location
     */
    public Location getRandomMoveLocation(ArenaWorld world, ArenaAnimal animal) {
        List<Location> surroundingLocations = getSurroundingFourLocations(animal.getLocation());
        List<Location> validSurroundingLocations = getValidSurroundingLocations(world, animal, surroundingLocations);

        return getRandomLocation(world, animal, validSurroundingLocations);
    }

    /**
     * Return a random location adjacent to the animal (including diagonals)
     * 
     * @param world
     *            world the animal lives in
     * @param animal
     *            animal to be adjacent to chosen location
     * @return a location
     */
    public Location getRandomBreedingLocation(ArenaWorld world, ArenaAnimal animal) {
        List<Location> surroundingLocations = getSurroundingEightLocations(animal.getLocation());
        List<Location> validSurroundingLocations = getValidSurroundingLocations(world, animal, surroundingLocations);

        return getRandomLocation(world, animal, validSurroundingLocations);
    }

    /**
     * Return a random integer between 0 and 3
     * 
     * @return an int
     */
    public int getRandomNumber() {
        int Random;
        Random randomize = new Random();
        Random = randomize.nextInt(3);
        return new Integer(Random);
    }

    /**
     * return north of the animal
     * 
     * @param animal
     *            an ArenaAnimal
     * @return a location
     */
    public Location getNorth(ArenaAnimal animal) {
        return new Location(animal.getLocation().getX(), animal.getLocation().getY() - 1);
    }

    /**
     * return south of the animal
     * 
     * @param animal
     *            an ArenaAnimal
     * @return a location
     */
    public Location getSouth(ArenaAnimal animal) {
        return new Location(animal.getLocation().getX(), animal.getLocation().getY() + 1);
    }

    /**
     * return west of the animal
     * 
     * @param animal
     *            an ArenaAnimal
     * @return a location
     */
    public Location getWest(ArenaAnimal animal) {
        return new Location(animal.getLocation().getX() - 1, animal.getLocation().getY());
    }

    /**
     * return east of the animal
     * 
     * @param animal
     *            an ArenaAnimal
     * @return a location
     */
    public Location getEast(ArenaAnimal animal) {
        return new Location(animal.getLocation().getX() + 1, animal.getLocation().getY());
    }
}
