package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.vehicleCommands.*;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;

public abstract class Vehicle implements MoveableItem, Actor {

    private int MAX_FUEL_LEVEL;
    private int STRENGTH;
    private int VIEW_RANGE;
    private int COOLDOWN;
    private ImageIcon image;
    private boolean isDead = false;
    private int MAX_SPEED;
    private int MAX_ACCELERATION;
    private int MAX_DECELERATION;

    protected VehicleAI ai;

    protected Location location;
    protected int fuelLevel = MAX_FUEL_LEVEL;
    private int speed = 0;
    private int acceleration = 0;
    protected Direction heading;

    public void refuel() {
        fuelLevel = MAX_FUEL_LEVEL;
    }

    protected void setMAX_FUEL_LEVEL(int i) {
        this.MAX_FUEL_LEVEL = i;
    }

    protected void setSTRENGTH(int i) {
        this.STRENGTH = i;
    }

    protected void setVIEW_RANGE(int i) {
        this.VIEW_RANGE = i;
    }

    protected void setCOOLDOWN(int i) {
        this.COOLDOWN = i;
    }

    protected void setMAX_SPEED(int i) {
        this.MAX_SPEED = i;
    }

    protected void setMAX_ACCELERATION(int i) {
        this.MAX_ACCELERATION = i;
    }

    protected void setMAX_DECELERATION(int i) {
        this.MAX_DECELERATION = i;
    }

    protected void setLocation(Location l) {
        this.location = l;
    }

    protected void setHeading(Direction d) {
        this.heading = d;
    }

    protected void vehicleTotalled() {
        this.isDead = true;
    }

    /**
     * Must be between MAX_DECELERATION and MAX_ACCELERATION
     * 
     * @param a
     */
    protected void setAcceleration(int a) {
        this.acceleration = a;
        if (acceleration > MAX_ACCELERATION)
            acceleration = MAX_ACCELERATION;
        if (acceleration < MAX_DECELERATION)
            acceleration = MAX_DECELERATION;
    }

    protected void setSpeed() {
        this.speed += acceleration;
        if (speed > this.MAX_SPEED)
            speed = MAX_SPEED;
        else if (speed < 0)
            speed = 0;
    }

    /**
     * Causes the vehicle to brake, which means its acceleration becomes
     * negative and it begins to lose speed
     * 
     * @return the new speed of the vehicle after braking
     */
    public int brake() {
        this.acceleration = MAX_DECELERATION;
        this.speed += acceleration;
        return speed;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    public Direction getHeading() {
        return this.heading;
    }

    public int getMaxFuelLevel() {
        return MAX_FUEL_LEVEL;
    }

    @Override
    public int getMeatCalories() {
        // not edible
        return 0;
    }

    /**
     * Returns the maximum distance that this item can move in one step. For
     * example, a {@link MoveableItem} with moving range 1 can only move to
     * adjacent locations. For vehicles, this will be their current moving
     * range, which depends on their speed.
     *
     * @return the maximum moving distance
     */
    @Override
    public int getMovingRange() {
        // can move more than one space, distance determined by current speed
        return speed;
    }

    @Override
    public abstract String getName();

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.fuelLevel--; // Loses 1 unit of fuel regardless of action.
        return nextAction;
    }

    @Override
    public int getPlantCalories() { // not edible
        return 0;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    public void loseFuel(int fuelLoss) {
        this.fuelLevel = this.fuelLevel - fuelLoss;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getAcceleration() {
        return this.acceleration;
    }
}
