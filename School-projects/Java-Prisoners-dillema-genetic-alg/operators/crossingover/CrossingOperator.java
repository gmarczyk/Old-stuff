package operators.crossingover;

import model.genetics.BinaryChromosome;

public interface CrossingOperator {

    BinaryChromosome crossTwoChromosomes(BinaryChromosome b1, BinaryChromosome b2);

}
