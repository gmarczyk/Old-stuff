import algorithms.OurAlgorithm;
import model.Population;
import model.strategy.ScoringTable;
import model.strategy.SingleTurn;
import model.strategy.Strategy;
import model.genetics.BinaryChromosome;
import operators.crossingover.CrossingOperator;
import operators.crossingover.impl.MixTwoPartsOfParents;
import operators.mutation.MutationOperator;
import operators.mutation.impl.ProbabilisticNegation;
import operators.selection.SelectionOperator;
import operators.selection.impl.stddeviation.SelectHigherThanAvgPlusStdDeviation;
import operators.selection.impl.stddeviation.SelectLowerThanAvgMinusStdDeviation;
import operators.selection.impl.stddeviation.SelectWithinOneStdDeviationFromAvg;

public class Main {



    public static void main(String[] args) {

        OurAlgorithm oa = new OurAlgorithm();
        oa.run();

    }

}
