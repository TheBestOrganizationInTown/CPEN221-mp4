package ca.ubc.ece.cpen221.mp4.ai;

import java.util.*;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class AIAlgorithms {

    public int getDistance(Location otherLocation, Location location) {
        return (Math.abs(location.getX() - otherLocation.getX()) + Math.abs(location.getY() - otherLocation.getY()));
    }

    public Location checkFurtherLocation(Location toCompareTo, Location Location1, Location Location2) {
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);

        if (Distance2 > Distance1)
            return Location2;

        return Location1;
    }

    public Location checkClosestLocation(Location toCompareTo, Location Location1, Location Location2) {
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);
        if (Distance2 < Distance1)
            return Location2;
        return Location1;
    }

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

        return Locations;
    }

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

    public Location getRandomLocation(ArenaWorld world, ArenaAnimal animal, List<Location> locations) {
        int HIGH = locations.size();
        int randomIndex = 0;
        Random randomize = new Random();
        if (HIGH > 0){
            randomIndex = randomize.nextInt(HIGH);
            return locations.get(randomIndex);
        }
        return animal.getLocation();
    }

    public Location getRandomMoveLocation(ArenaWorld world, ArenaAnimal animal) {
        List<Location> surroundingLocations = getSurroundingFourLocations(animal.getLocation());
        List<Location> validSurroundingLocations = getValidSurroundingLocations(world, animal, surroundingLocations);

        return getRandomLocation(world, animal, validSurroundingLocations);
    }

    public Location getRandomBreedingLocation(ArenaWorld world, ArenaAnimal animal) {
        List<Location> surroundingLocations = getSurroundingEightLocations(animal.getLocation());
        List<Location> validSurroundingLocations = getValidSurroundingLocations(world, animal, surroundingLocations);

        return getRandomLocation(world, animal, validSurroundingLocations);
    }

}
