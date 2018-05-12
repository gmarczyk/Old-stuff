package model.genetics;


public class BinaryChromosome {

    public BinaryGene[] genes;

    public BinaryChromosome(BinaryGene[] genes){
        this.genes = genes;
    }

    public static BinaryChromosome getRandomChromosome(int numberOfGenes) {
        BinaryGene[] tmpGenes = new BinaryGene[numberOfGenes];
        for(int i = 0; i<numberOfGenes; i++) {
            tmpGenes[i] = BinaryGene.getRandomGene();
        }
        BinaryChromosome retChrom = new BinaryChromosome(tmpGenes);
        return retChrom;
    }

    @Override
    public String toString() {
        String tmp="";
        for(BinaryGene gene : genes) {
            tmp += gene.toString();
        }
        return tmp;
    }

}
