package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.Location;

public class AIAlgorithms {
    
    private int getDistance(Location otherLocation, Location location) {
        return (Math.abs(location.getX() - otherLocation.getX()) + Math.abs(location.getY() - otherLocation.getY()));
    }
    
    private Location checkFurtherLocation(Location toCompareTo, Location Location1, Location Location2){
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);
        
        if(Distance2 > Distance1)
            return Location2;
        
        return Location1;
    }
    
    private Location checkClosestLocation(Location toCompareTo, Location Location1, Location Location2){
        int Distance1 = getDistance(toCompareTo, Location1);
        int Distance2 = getDistance(toCompareTo, Location2);
        
        if(Distance2 > Distance1)
            return Location2;
        
        return Location1;
    }
    
    public Location getFurthestMoveableLocation(Location otherLocation, Location currentLocation){
        Location furthestLocation;
        Location North = new Location(currentLocation.getX(),currentLocation.getY()-1);
        Location South = new Location(currentLocation.getX(),currentLocation.getY()+1);
        Location West = new Location(currentLocation.getX()-1,currentLocation.getY());
        Location East = new Location(currentLocation.getX()+1,currentLocation.getY());
        
        furthestLocation = checkFurtherLocation(otherLocation,North,South);
        furthestLocation = checkFurtherLocation(otherLocation,furthestLocation,East);
        furthestLocation = checkFurtherLocation(otherLocation,furthestLocation,West);
        
        return furthestLocation;
    }
    public Location getClosestMoveableLocation(Location otherLocation, Location currentLocation){
        Location closestLocation;
        Location North = new Location(currentLocation.getX(),currentLocation.getY()-1);
        Location South = new Location(currentLocation.getX(),currentLocation.getY()+1);
        Location West = new Location(currentLocation.getX()-1,currentLocation.getY());
        Location East = new Location(currentLocation.getX()+1,currentLocation.getY());
        
        closestLocation = checkClosestLocation(otherLocation,North,South);
        closestLocation = checkClosestLocation(otherLocation,closestLocation,East);
        closestLocation = checkClosestLocation(otherLocation,closestLocation,West);
        
        return closestLocation;
    }

}
