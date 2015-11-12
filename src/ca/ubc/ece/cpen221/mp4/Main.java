package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

    static final int X_DIM = 100;
    static final int Y_DIM = 40;
    static final int SPACES_PER_GRASS = 7;
    static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
    static final int INITIAL_GNATS = INITIAL_GRASS / 20;

    // Animals
    static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
    static final int INITIAL_FOXES = INITIAL_GRASS / 32;
    static final int INITIAL_BEARS = INITIAL_GRASS / 40;
    static final int INITIAL_BEES = INITIAL_GRASS / 20;

    // Vehicles
    static final int INITIAL_CARS = INITIAL_GRASS / 100;
    static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
    static final int INITIAL_MOTORCYCLES = INITIAL_GRASS / 64;

    // Medieval Creatures
    static final int INITIAL_PALADINS = INITIAL_GRASS / 30;
    static final int INITIAL_DRAGONS = 1;
    static final int INITIAL_WIZARDS = INITIAL_GRASS / 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createAndShowWorld();
            }
        });
    }

    public void createAndShowWorld() {
        World world = new WorldImpl(X_DIM, Y_DIM);
        initialize(world);
        new WorldUI(world).show();
    }

    public void initialize(World world) {
        addGrass(world);
        world.addActor(new Gardener());

        addGnats(world);
        addRabbits(world);
        addFoxes(world);
        addBees(world);
        addBears(world);
        // TODO: You may add your own creatures here!
    }

    private void addGrass(World world) {
        for (int i = 0; i < INITIAL_GRASS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            world.addItem(new Grass(loc));
        }
    }

    private void addGnats(World world) {
        for (int i = 0; i < INITIAL_GNATS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Gnat gnat = new Gnat(loc);
            world.addItem(gnat);
            world.addActor(gnat);
        }
    }

    private void addFoxes(World world) {
        FoxAI foxAI = new FoxAI();
        for (int i = 0; i < INITIAL_FOXES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Fox fox = new Fox(foxAI, loc);
            world.addItem(fox);
            world.addActor(fox);
        }
    }

    private void addRabbits(World world) {
        RabbitAI rabbitAI = new RabbitAI();
        for (int i = 0; i < INITIAL_RABBITS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Rabbit rabbit = new Rabbit(rabbitAI, loc);
            world.addItem(rabbit);
            world.addActor(rabbit);
        }
    }

    private void addBees(World world) {
        BeeAI beeAI = new BeeAI();
        for (int i = 0; i < INITIAL_BEES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bee bee = new Bee(beeAI, loc);
            world.addItem(bee);
            world.addActor(bee);
        }
    }
    
    private void addBears(World world) {
        BearAI bearAI = new BearAI();
        for (int i = 0; i < INITIAL_BEARS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bear bear = new Bear(bearAI, loc);
            world.addItem(bear);
            world.addActor(bear);
        }
    }

    private void addDragons(World world) {
        BeeAI beeAI = new BeeAI();
        for (int i = 0; i < INITIAL_BEES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bee bee = new Bee(beeAI, loc);
            world.addItem(bee);
            world.addActor(bee);
        }
    }

    private void addPaladins(World world) {
        BeeAI beeAI = new BeeAI();
        for (int i = 0; i < INITIAL_BEES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bee bee = new Bee(beeAI, loc);
            world.addItem(bee);
            world.addActor(bee);
        }
    }

    private void addWizards(World world) {
        BeeAI beeAI = new BeeAI();
        for (int i = 0; i < INITIAL_BEES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bee bee = new Bee(beeAI, loc);
            world.addItem(bee);
            world.addActor(bee);
        }
    }
}