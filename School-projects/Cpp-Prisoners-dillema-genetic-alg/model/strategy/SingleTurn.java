package model.strategy;


import model.genetics.BinaryGene;

public class SingleTurn {

    public BinaryGene playerOneChoice;
    public BinaryGene playerTwoChoice;

    public SingleTurn(BinaryGene p1, BinaryGene p2) {
        this.playerOneChoice = p1;
        this.playerTwoChoice = p2;
    }

}
