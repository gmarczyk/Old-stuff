package model.genetics;


public class BinaryGene {

    public BinaryAllel geneVal;

    public BinaryGene(BinaryAllel allelValue) {
        this.geneVal = allelValue;
    }

    public static BinaryGene getRandomGene() {
        return new BinaryGene(BinaryAllel.getRandomAllel());
    }

    @Override
    public String toString() {
        return geneVal.getStringRepresentation();
    }

}
