package model.strategy;

import model.Population;
import model.genetics.BinaryChromosome;
import model.genetics.BinaryGene;

public class Strategy {

    public static int stratID = 0;
    public int id;

    public BinaryChromosome chromosome; // 1 = betrayal, 0 = cooperation
    public SingleTurn[] memorizedTurns;
    public int points = 0;
    public float avgPoints = 0.0f;

    public Strategy(BinaryChromosome chromosome, SingleTurn[] memorizedTurns) {
        this.chromosome = chromosome;
        this.memorizedTurns = memorizedTurns;

        stratID++;
        id = stratID;

        print();
    }

    public static SingleTurn[] generateRandomTurns(int numberOfTurns) {
        SingleTurn[] tmpTurns = new SingleTurn[numberOfTurns];
        for(int i = 0; i < numberOfTurns; i++) {
            tmpTurns[i] = new SingleTurn(BinaryGene.getRandomGene(),BinaryGene.getRandomGene());
        }
        return tmpTurns;
    }

    public void print() {
       // System.out.println("Id: " + this.id + " Memorized turns = " + memorizedTurns.length + " : " + turnsToString(this.memorizedTurns)
         //           + " ||| Decision table = " + chromosome.toString().length() + " : " + chromosome.toString());
    }

    private String turnsToString(SingleTurn[] turns) {
        String tmp = "";
        for(SingleTurn turn : turns) {
            tmp += turn.playerOneChoice.toString();
            tmp += turn.playerTwoChoice.toString();
        }
        return tmp;
    }

    /**
     *  1 = betrayal, 0 = cooperation
     */
    public void playWith(Strategy opponent, ScoringTable scoringTab) {
       // System.out.println("Starting round");
        for (int i = 0; i < this.chromosome.genes.length; i++) {
          //  System.out.println("Round: " + (i+1) + ":: Player1, points before = " + points + " avg = " + avgPoints + " Decision table " + turnsToString(memorizedTurns));
          //  System.out.println("Round: " + (i+1) + ":: Player2, points before = " + opponent.points + " avg = " + opponent.avgPoints + " Decision table " + turnsToString(opponent.memorizedTurns));

            int p1_DecisionChromosomeIndex = turnsToInt(this.memorizedTurns);
            int p2_DecisionChromosomeIndex = turnsToInt(opponent.memorizedTurns);
            BinaryGene p1_Decision = this.chromosome.genes[p1_DecisionChromosomeIndex];
            BinaryGene p2_Decision = opponent.chromosome.genes[p2_DecisionChromosomeIndex];

            String p1p2_BinaryResult = p1_Decision.geneVal.getStringRepresentation() + p2_Decision.geneVal.getStringRepresentation();
            int p1p2_ScoreIndex = Integer.parseInt(p1p2_BinaryResult, 2);

            this.points += scoringTab.table[p1p2_ScoreIndex][0];
            opponent.points += scoringTab.table[p1p2_ScoreIndex][1];

            if(this.memorizedTurns.length > 1) {
                for (int j = 0; j < this.memorizedTurns.length - 1; j++) {
                    this.memorizedTurns[j] = this.memorizedTurns[j+1];
                    opponent.memorizedTurns[j] = opponent.memorizedTurns[j+1];
                }
                this.memorizedTurns[this.memorizedTurns.length-1] = new SingleTurn(p1_Decision, p2_Decision);
                opponent.memorizedTurns[opponent.memorizedTurns.length-1] = new SingleTurn(p2_Decision, p1_Decision);
            }
            else {
                this.memorizedTurns[this.memorizedTurns.length-1] = new SingleTurn(p1_Decision, p2_Decision);
                opponent.memorizedTurns[opponent.memorizedTurns.length-1] = new SingleTurn(p2_Decision, p1_Decision);
            }


          //  System.out.println("Round: " + (i+1) + ":: Player1, decision index = " + p1_DecisionChromosomeIndex + " decision = " + p1_Decision + " points after = " + points + " avg = " + avgPoints + " Decision table " + turnsToString(memorizedTurns));
        //    System.out.println("Round: " + (i+1) + ":: Player2, decision index = " + p2_DecisionChromosomeIndex + " decision = " + p2_Decision + " points after = " + opponent.points + " avg = " + opponent.avgPoints + " Decision table " +turnsToString(opponent.memorizedTurns));

        }

        this.avgPoints = (float) this.points/this.chromosome.genes.length;
        opponent.avgPoints = (float) opponent.points/this.chromosome.genes.length;

        if(this.avgPoints > Population.maxAvg) {
            Population.maxAvg = this.avgPoints;
            //System.out.println("Max avg by " +this.id + " avg= " + this.avgPoints);
        }
        if(opponent.avgPoints > Population.maxAvg) {
            Population.maxAvg = opponent.avgPoints;
            //System.out.println("Max avg by " +opponent.id + " avg= " + opponent.avgPoints);
        }

        this.memorizedTurns =  this.generateRandomTurns(this.memorizedTurns.length);
        opponent.memorizedTurns = opponent.generateRandomTurns(this.memorizedTurns.length);

     //   System.out.println("End of rounds, Player1 points after = " + points + " avg = " + avgPoints + " Decision table " + turnsToString(memorizedTurns));
     //   System.out.println("End of rounds, Player2 points after = " + opponent.points + " avg = " + opponent.avgPoints + " Decision table " + turnsToString(opponent.memorizedTurns));
       // System.out.println("Ending round");
    }

    private int turnsToInt(SingleTurn[] turns) {
        return Integer.parseInt(turnsToString(turns), 2);
    }

}
