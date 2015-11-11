package ca.ubc.ece.cpen221.mp4.ai;

import java.util.*;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
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
        Location North = new Location(currentLocation.getX(), currentLocation.getY() - 1);
        Location South = new Location(currentLocation.getX(), currentLocation.getY() + 1);
        Location West = new Location(currentLocation.getX() - 1, currentLocation.getY());
        Location East = new Location(currentLocation.getX() + 1, currentLocation.getY());

        furthestLocation = checkFurtherLocation(otherLocation, North, South);
        furthestLocation = checkFurtherLocation(otherLocation, furthestLocation, East);
        furthestLocation = checkFurtherLocation(otherLocation, furthestLocation, West);
        return furthestLocation;
    }

    public Location getClosestMoveableLocation(Location otherLocation, Location currentLocation) {
        Location closestLocation;
        Location North = new Location(currentLocation.getX(), currentLocation.getY() - 1);
        Location South = new Location(currentLocation.getX(), currentLocation.getY() + 1);
        Location West = new Location(currentLocation.getX() - 1, currentLocation.getY());
        Location East = new Location(currentLocation.getX() + 1, currentLocation.getY());

        closestLocation = checkClosestLocation(otherLocation, North, South);
        closestLocation = checkClosestLocation(otherLocation, closestLocation, East);
        closestLocation = checkClosestLocation(otherLocation, closestLocation, West);

        return closestLocation;
    }
    
    public List<Location> getPossibleLocations(Location itemLocation){
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
    
    public boolean checkValidity(ArenaWorld world, ArenaAnimal animal, Location targetLocation){
        boolean isValid = true;
        List<Location> occupiedLocations = new LinkedList<Location>();
        Set<Item> surroundingItems = new HashSet<Item>();
        surroundingItems = world.searchSurroundings(animal);
        Iterator<Item> itemIterator = surroundingItems.iterator();
        while(itemIterator.hasNext()){
            Item newItem = itemIterator.next();
            occupiedLocations.add(newItem.getLocation());
        }
        Iterator<Location> locationIterator = occupiedLocations.iterator();
        while(locationIterator.hasNext()){
            Location occupiedLocation = locationIterator.next();
            if(occupiedLocation.equals(targetLocation))
                isValid = false;
        }
        
        return isValid;
    }

}
