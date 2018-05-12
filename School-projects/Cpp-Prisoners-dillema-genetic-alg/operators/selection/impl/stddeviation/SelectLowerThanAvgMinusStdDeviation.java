package operators.selection.impl.stddeviation;

import model.Population;
import model.strategy.Strategy;
import operators.selection.SelectionOperator;

import java.util.ArrayList;

public class SelectLowerThanAvgMinusStdDeviation implements SelectionOperator {

    @Override
    public Population selectFromPopulation(Population toSelectFrom) {
        ArrayList<Strategy> listOfStrategies = new ArrayList<>();
        for(Strategy singleStrat : toSelectFrom.prisonerStrategies) {
            if(singleStrat.avgPoints < toSelectFrom.avgPoints - toSelectFrom.stdDeviation) {
                listOfStrategies.add(singleStrat);
            }
        }
        Strategy[] strategyArrays =  listOfStrategies.toArray(new Strategy[listOfStrategies.size()]);
        return new Population(strategyArrays);
    }
}
