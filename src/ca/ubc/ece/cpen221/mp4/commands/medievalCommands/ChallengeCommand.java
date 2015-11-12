package ca.ubc.ece.cpen221.mp4.commands.medievalCommands;

import java.util.Random;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.InvalidCommandException;
import ca.ubc.ece.cpen221.mp4.items.others.AbstractMedievalCreature;

public class ChallengeCommand implements Command {

    private final AbstractMedievalCreature challenger;
    private final AbstractMedievalCreature opponent;

    /**
     * Construct a {@link ChallengeCommand}, where <code> challenger </code> is
     * the challenger and <code> opponent </code> is the opponent. Remember that
     * the opponent must be adjacent to the challenger, and the challenger must
     * have bravery no more than 20 below the opponent's. challenger must have
     * energy above 20.
     *
     * @param challenger
     *            the challenger
     * @param opponent
     *            : the opponent
     */
    public ChallengeCommand(AbstractMedievalCreature challenger, AbstractMedievalCreature opponent) {
        this.opponent = opponent;
        this.challenger = challenger;
    }

    @Override
    public void execute(World w) throws InvalidCommandException {

        if (challenger.getBravery() + 20 < opponent.getBravery())
            throw new InvalidCommandException("Invalid EatCommand: Too cowardly to challenge");
        if (opponent.getLocation().getDistance(challenger.getLocation()) != 1)
            throw new InvalidCommandException("Invalid EatCommand: Opponent is not adjacent");

        int opponentChance = 0;
        int challengerChance = 0;
        if (challenger.getBravery() > opponent.getBravery())
            challengerChance++;
        else {
            opponentChance++;
        }
        if (challenger.getEnergy() > opponent.getEnergy())
            challengerChance++;
        else {
            opponentChance++;
        }

        if (challenger.getStrength() > opponent.getStrength())
            challengerChance++;
        else {
            opponentChance++;
        }
        Random random = new Random();
        opponent.loseMagic(random.nextInt(opponent.getMagic()));
        challenger.loseMagic(random.nextInt(challenger.getMagic()));
        int opponentScore = random.nextInt(6) + opponentChance;
        int challengerScore = random.nextInt(6) + challengerChance;
        boolean challengerWins = (challengerScore >= opponentScore);

        if (challengerWins) {
            challenger.addTreasure(opponent.getTreasure()/4);
            opponent.loseTreasure(opponent.getTreasure()/4);
            challenger.loseEnergy(10);
            opponent.loseEnergy(15);
            challenger.increaseBravery(2);
            opponent.increaseCowardess(1);
        }else{
            opponent.addTreasure(challenger.getTreasure()/4);
            challenger.loseTreasure(challenger.getTreasure()/4);
            opponent.loseEnergy(10);
            challenger.loseEnergy(15);
            opponent.increaseBravery(2);
            challenger.increaseCowardess(1);
        }

    }

}
