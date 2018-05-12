package operators.mutation;

import model.genetics.BinaryChromosome;

public interface MutationOperator {

    void mutateChromosome(BinaryChromosome toMutate);

}
