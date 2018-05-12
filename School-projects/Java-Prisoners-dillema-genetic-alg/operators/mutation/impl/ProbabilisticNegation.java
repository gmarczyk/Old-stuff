package operators.mutation.impl;

import model.genetics.BinaryAllel;
import model.genetics.BinaryChromosome;
import model.genetics.BinaryGene;
import operators.mutation.MutationOperator;

import java.util.Random;


public class ProbabilisticNegation implements MutationOperator {

    public float percentage;

    public ProbabilisticNegation(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public void mutateChromosome(BinaryChromosome toMutate) {
        for(int i = 0; i< toMutate.genes.length; i++){
            Random r = new Random();
            if(r.nextFloat() <= percentage){
                BinaryAllel geneValue = toMutate.genes[i].geneVal;
                toMutate.genes[i].geneVal = (geneValue == BinaryAllel.ONE) ? BinaryAllel.ZERO : BinaryAllel.ONE;
                //System.out.println(i + "chromosome mutated");
            }
        }
    }

}
