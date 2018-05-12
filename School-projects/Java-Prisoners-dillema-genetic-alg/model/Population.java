package model;

import model.strategy.ScoringTable;
import model.strategy.Strategy;

public class Population {

    public Strategy[] prisonerStrategies;
    public float stdDeviation;
    public float avgPoints;

    public static float maxAvg = 0.0f;

    public Population(Strategy[] prisonerStrategies) {
        this.prisonerStrategies = prisonerStrategies;
    }

    public void playWithEveryone(ScoringTable scoringTable) {

        //System.out.println("Game started, here are the players");
        for (Strategy strat : prisonerStrategies) {
            strat.print();
        }

        for (int i = 0; i< prisonerStrategies.length; i++) {
            for (int j = 0; j< prisonerStrategies.length; j++) {
                if(j>i) {
                   // System.out.println("Player ID: " + (j+1) + "vs" + (i+1));
                    prisonerStrategies[j].playWith(prisonerStrategies[i],scoringTable);
                }
            }
        }
        this.calculateAvgPoints();
        this.calculateStdDeviation();
        //System.out.println("++" + (this.avgPoints+this.stdDeviation) + " --" + (this.avgPoints-this.stdDeviation));
        for(Strategy strat:prisonerStrategies) {
           // System.out.println("ID: " + strat.id + " AVG = " + strat.avgPoints);
        }
    }

    public void calculateAvgPoints() {
        float tmp = 0;
        for (Strategy singleStrat : prisonerStrategies) {
            tmp += singleStrat.avgPoints;
        }
        this.avgPoints = tmp/prisonerStrategies.length;
       // System.out.println(this.avgPoints);
    }

    public void calculateStdDeviation() {
        float sumAvgPopulationMinusAvgStrat = 0;
        for (Strategy singleStrat : prisonerStrategies) {
            float tmp = this.avgPoints - singleStrat.avgPoints;
            tmp = (float) Math.pow((double)tmp,(double)2);
            sumAvgPopulationMinusAvgStrat += tmp;
        }
        this.stdDeviation = sumAvgPopulationMinusAvgStrat/prisonerStrategies.length;
        this.stdDeviation = (float)Math.sqrt(this.stdDeviation);
     //   System.out.println(this.stdDeviation);
    }

    public void prepareForNextRound() {
        this.avgPoints = 0.0f;
        this.stdDeviation = 0.0f;
        for(Strategy singleStrat : prisonerStrategies) {
            singleStrat.points = 0;
            singleStrat.avgPoints = 0.0f;
        }
    }




}
