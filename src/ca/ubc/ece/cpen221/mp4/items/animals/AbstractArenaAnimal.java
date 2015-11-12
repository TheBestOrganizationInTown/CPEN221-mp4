package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public abstract class AbstractArenaAnimal implements ArenaAnimal { // abstract
																	// class for
																	// other
	//private fields representing characteristics of the			// arena
	//animal														// animals
	private int INITIAL_ENERGY;
	private int MAX_ENERGY;
	private int STRENGTH;
	private int VIEW_RANGE;
	private int MIN_BREEDING_ENERGY;
	private int COOLDOWN;
	private ImageIcon image;
	private boolean isDead;

	private AI ai;

	protected Location location;
	private int energy = INITIAL_ENERGY;

	@Override
	public abstract LivingItem breed();

	@Override
	public void eat(Food food) {
		energy = Math.min(MAX_ENERGY, energy + food.getMeatCalories());
	}

	/**
	 * the following set method gives values to INITIAL_ENERGY for
	 * a new instance of an AbstractArenaAnimal
	 * @param i
	 */

	protected void setINITIAL_ENERGY(int i) {
		this.INITIAL_ENERGY = i;
	}
	/**
     * the following set method gives values to energy for
     * a new instance of an AbstractArenaAnimal, or to change this value later
     * @param i
     */
	protected void setEnergy(int i) {
		this.energy = i;
	}
	/**
     * the following set method gives values to MAX_ENERGY for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setMAX_ENERGY(int i) {
		this.MAX_ENERGY = i;
	}
	/**
     * the following set method gives values to STRENGTH for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setSTRENGTH(int i) {
		this.STRENGTH = i;
	}
	/**
     * the following set method gives values to VIEW_RANGE for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setVIEW_RANGE(int i) {
		this.VIEW_RANGE = i;
	}
	/**
     * the following set method gives values to MIN_BREEDING_ENERGY for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setMIN_BREEDING_ENERGY(int i) {
		this.MIN_BREEDING_ENERGY = i;
	}
	/**
     * the following set method gives values to COOLDOWN for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setCOOLDOWN(int i) {
		this.COOLDOWN = i;
	}
	/**
     * the following set method gives values to location for
     * a new instance of an AbstractArenaAnimal, or to change this value later
     * @param i
     */
	protected void setLocation(Location l) {
		this.location = l;
	}
	/**
     * the following set method gives values to image for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setImage(ImageIcon i){
	    this.image = i;
	}
	/**
     * the following set method gives values to ai for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
	protected void setAI(AI ai){
	    this.ai = ai;
	}

	@Override
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public ImageIcon getImage() {
		return image;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public int getMaxEnergy() {
		return MAX_ENERGY;
	}

	@Override
	public int getMeatCalories() {
		return energy;
	}

	@Override
	public int getMinimumBreedingEnergy() {
		return MIN_BREEDING_ENERGY;
	}

	@Override
	public int getMovingRange() {
		return 1; // Can only move to adjacent locations.
	}

	@Override
	public abstract String getName();

	@Override
	public Command getNextAction(World world) {
		Command nextAction = ai.getNextAction(world, this);
		this.energy--; // Loses 1 energy regardless of action.
		return nextAction;
	}

	@Override
	public int getPlantCalories() { // arena animals dont eat plants
		return 0;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public int getViewRange() {
		return VIEW_RANGE;
	}

	@Override
	public boolean isDead() {
	    this.isDead = (this.energy <= 0);
		return isDead;
	}

	@Override
	public void loseEnergy(int energyLoss) {
		this.energy = this.energy - energyLoss;
	}

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
	}
}
