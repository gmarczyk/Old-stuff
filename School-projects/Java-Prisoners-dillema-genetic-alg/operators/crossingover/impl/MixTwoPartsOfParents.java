package operators.crossingover.impl;

import model.genetics.BinaryChromosome;
import model.genetics.BinaryGene;
import operators.crossingover.CrossingOperator;

import java.util.Random;


public class MixTwoPartsOfParents implements CrossingOperator {

    @Override
    public BinaryChromosome crossTwoChromosomes(BinaryChromosome b1, BinaryChromosome b2) { // b1 and b2 genes length should be equal
        Random r = new Random();
        int cuttingPoint = (Math.abs(r.nextInt()) % (b1.genes.length -1)) +1; // for 5 elements choose indexes 1/2/3 (boundary are forbidden)
        BinaryGene[] newGenes = new BinaryGene[b1.genes.length];

        for(int i = 0; i<cuttingPoint; i++) {
            newGenes[i] = new BinaryGene(b1.genes[i].geneVal); // first parent
        }

        for(int i = cuttingPoint; i < b1.genes.length; i++) {
            newGenes[i] = new BinaryGene(b2.genes[i].geneVal); // second parent
        }

        BinaryChromosome retVal = new BinaryChromosome(newGenes);
       // System.out.println("Chosen index: " + cuttingPoint + " new gene: " + retVal.toString());
        return retVal;
    }
}
