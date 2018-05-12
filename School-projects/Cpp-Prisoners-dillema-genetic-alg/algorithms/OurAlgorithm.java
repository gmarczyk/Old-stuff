package algorithms;


import com.sun.org.apache.bcel.internal.generic.POP;
import model.Population;
import model.genetics.BinaryChromosome;
import model.strategy.ScoringTable;
import model.strategy.Strategy;
import operators.crossingover.CrossingOperator;
import operators.crossingover.impl.MixTwoPartsOfParents;
import operators.mutation.MutationOperator;
import operators.mutation.impl.ProbabilisticNegation;
import operators.selection.SelectionOperator;
import operators.selection.impl.stddeviation.SelectHigherThanAvgPlusStdDeviation;
import operators.selection.impl.stddeviation.SelectLowerThanAvgMinusStdDeviation;
import operators.selection.impl.stddeviation.SelectWithinOneStdDeviationFromAvg;

import java.util.ArrayList;
import java.util.Random;

public class OurAlgorithm {

    // create population. With random chromosomes and memory
    // calculate fitness
    // selection

    // for number of generations
    //for() {
        // crossing
        // mutation
        // fitness calculation - reset memory to random first.
        // selecting best ones
    //}

    static int turns = 3;
    static int strategyLength = 1<<turns*2;

    public void run() {

        ArrayList<Strategy> tmpStrategies = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tmpStrategies.add(new Strategy(BinaryChromosome.getRandomChromosome(strategyLength), Strategy.generateRandomTurns(turns)));
        }
        Strategy[] strategyArray =  tmpStrategies.toArray(new Strategy[tmpStrategies.size()]);

        int[][] tab = {
              //  P1  P2
                { 6 , 6  }, // 00 Both cooperated
                { 0 , 10 }, // 01 P2 betrayed
                { 10, 0  }, // 10 P1 betrayed
                { 2 , 2  }  // 11 Both betrayed
        };
        ScoringTable scoringTable = new ScoringTable(tab);
        scoringTable.print();


        // Pre Calculate fitness
        Population population = new Population(strategyArray);
        population.playWithEveryone(scoringTable);

        // Pre Selection
        SelectionOperator selection = new SelectHigherThanAvgPlusStdDeviation();
        Population overStdDev = selection.selectFromPopulation(population);

        selection = new SelectWithinOneStdDeviationFromAvg();
        Population inRangeOfStdDev = selection.selectFromPopulation(population);

        selection = new SelectLowerThanAvgMinusStdDeviation();
        Population underStdDev = selection.selectFromPopulation(population);

        MutationOperator mutator = new ProbabilisticNegation(0.001f);
        for (int i = 0; i < 100; i++) {
            // Crossing
            System.out.println("Avg of whole popul b4 cross: " + population.avgPoints);
            System.out.println("Max avg in this population " + findMaxFrom(population) );
            System.out.println("Max avg in whole project " + Population.maxAvg);

            population = cross(overStdDev,inRangeOfStdDev,underStdDev,population.prisonerStrategies.length);

            // Mutation
            for (int j = 0; j < population.prisonerStrategies.length; j++) {
                mutator.mutateChromosome(population.prisonerStrategies[j].chromosome);
            }

            // Game - fitness
            population.playWithEveryone(scoringTable);

            // Selection
            selection = new SelectHigherThanAvgPlusStdDeviation();
            overStdDev = selection.selectFromPopulation(population);

            selection = new SelectWithinOneStdDeviationFromAvg();
            inRangeOfStdDev = selection.selectFromPopulation(population);

            selection = new SelectLowerThanAvgMinusStdDeviation();
            underStdDev = selection.selectFromPopulation(population);
        }

        System.out.println("Avg of whole popul after simulation: " + population.avgPoints);
        System.out.println("Max avg in last population " + findMaxFrom(population) );
        System.out.println("Max avg in whole project " + Population.maxAvg);
    }


    private Population cross(Population overStdDev, Population inRangeOfStdDev, Population underStdDev, int populLength) {
        ArrayList<Strategy> nextGenerationStrategies = new ArrayList<>();
        CrossingOperator crosser = new MixTwoPartsOfParents();
        Random rand = new Random();


        Strategy.stratID = 0;
        int i = 0;
        while((nextGenerationStrategies.size() != populLength) && (nextGenerationStrategies.size() != overStdDev.prisonerStrategies.length*2)) {
            int randomFromBetterPopulation = Math.abs(rand.nextInt()%(overStdDev.prisonerStrategies.length));
            BinaryChromosome tmp = crosser.crossTwoChromosomes(overStdDev.prisonerStrategies[i].chromosome,
                    overStdDev.prisonerStrategies[randomFromBetterPopulation].chromosome);

            nextGenerationStrategies.add(new Strategy(tmp, Strategy.generateRandomTurns(turns)));


            randomFromBetterPopulation = Math.abs(rand.nextInt()%(overStdDev.prisonerStrategies.length));
            tmp = crosser.crossTwoChromosomes(overStdDev.prisonerStrategies[i].chromosome,
                    overStdDev.prisonerStrategies[randomFromBetterPopulation].chromosome);

            nextGenerationStrategies.add(new Strategy(tmp, Strategy.generateRandomTurns(turns)));

            i++;
        }

        int j = 0;
        while((nextGenerationStrategies.size() != populLength) && (nextGenerationStrategies.size() != (inRangeOfStdDev.prisonerStrategies.length + i*2))) {
            int randomFromBetterPopulation = Math.abs(rand.nextInt()%(inRangeOfStdDev.prisonerStrategies.length));
            BinaryChromosome tmp = crosser.crossTwoChromosomes(inRangeOfStdDev.prisonerStrategies[j].chromosome,
                    inRangeOfStdDev.prisonerStrategies[randomFromBetterPopulation].chromosome);


            nextGenerationStrategies.add(new Strategy(tmp, Strategy.generateRandomTurns(turns)));
            j++;
        }

        int k = 0;
        while((nextGenerationStrategies.size() != populLength) && (nextGenerationStrategies.size() != (underStdDev.prisonerStrategies.length + i*2 + inRangeOfStdDev.prisonerStrategies.length))) {
            nextGenerationStrategies.add(new Strategy(underStdDev.prisonerStrategies[k].chromosome, Strategy.generateRandomTurns(turns)));
            k++;
        }

        Population ret = new Population(nextGenerationStrategies.toArray(new Strategy[nextGenerationStrategies.size()]));
        return ret;
    }

    private float findMaxFrom(Population p) {
        float max =0;
        for(Strategy strat : p.prisonerStrategies) {
            if(strat.avgPoints > max) max = strat.avgPoints;
        }
        return max;
    }
}
