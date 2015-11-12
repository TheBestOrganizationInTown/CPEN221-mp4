package ca.ubc.ece.cpen221.mp4.items.others;

import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public abstract class AbstractMedievalCreature extends AbstractArenaAnimal {
    private int MAX_MAGIC;
    private boolean isEvil;
    private int MAX_BRAVERY = 100;
    
    private int bravery;
    private int magic;
    private int treasure;

    /**
     * the following set method gives values to MAX_MAGIC for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
    protected void setMAX_MAGIC(int i) {
        this.MAX_MAGIC = i;
    }
    /**
     * the following set method gives values to magic for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
    protected void setMagic() {
        this.magic = MAX_MAGIC/2;
    }
    /**
     * the following set method gives values to isEvil for
     * a new instance of an AbstractArenaAnimal, and updates it for commands
     * @param i
     */
    protected void setIsEvil(boolean evilness) {
        this.isEvil = evilness;
    }
    /**
     * the following set method gives values to treasure for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
    protected void setTreasure(int i) {
        this.treasure = i;
    }
    /**
     * the following set method gives values to bravery for
     * a new instance of an AbstractArenaAnimal
     * @param i
     */
    protected void setBravery(int i) {
        this.bravery = i;
    }

    /**
     * treasure must be a positive number
     * 
     * @param treasure
     */
    public void addTreasure(int treasure) {
        if (treasure > 0)
        this.treasure += treasure;
    }
    /**
     * treasure must be a positive number. Total treasure cannot go below zero
     * 
     * @param treasure
     */
    public void loseTreasure(int treasure){
        if (treasure > 0)
        this.treasure -= treasure;
        if (treasure < 0 )
            treasure = 0;
    }
    /**
     * bravery must be a positive number
     * 
     * @param bravery
     */
    public void increaseBravery(int bravery) {
        if (bravery > 0)
            this.bravery += bravery;
        if (bravery > MAX_BRAVERY)
            bravery = MAX_BRAVERY;
    }
    
    /**
     * Must be a positive number. This will decrease the creatures current bravery
     * @param cowardess The amount by which to decrease the creature's bravery
     */

    public void increaseCowardess(int cowardess) {
        if(cowardess > 0)
            this.bravery -= cowardess;
        if(bravery < 0)
            bravery = 0;
    }
    /**
     * Decreases creature's magic power. Cannot go below zero or above MAX_MAGIC.
     * Amount must be positive.
     * 
     * @param amount
     */
    public void loseMagic(int amount){
        if(amount > 0)
                this.magic -= amount;
        if(magic < 0)
            magic = 0;
    }
    /**
     * Must be a positive number. This will increase the creature's magic power.
     * Cannot go above MAX_MAGIC. 
     * @param amount The amount by which to increase the creature's magic power
     */

    public void increaseMagic(int amount) {
        if(amount > 0)
            this.magic += amount;
        if(magic > MAX_MAGIC)
            magic = MAX_MAGIC;
    }
    
    public int getTreasure() {
        return treasure;
    }

    public int getMAX_MAGIC() {
        return MAX_MAGIC;
    }

    public int getMAX_BRAVERY() {
        return MAX_BRAVERY;
    }

    public boolean getIsEvil() {
        return isEvil;
    }

    public int getMagic() {
        return magic;
    }

    public int getBravery() {
        return bravery;
    }
}