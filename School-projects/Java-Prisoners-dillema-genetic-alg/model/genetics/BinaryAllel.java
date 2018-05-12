package model.genetics;

import java.util.Random;

public enum BinaryAllel {

    ZERO,
    ONE;

    public String getStringRepresentation() {
        switch(this) {
            case ZERO:
                return "0";
            case ONE:
                return "1";
            default:
                throw new RuntimeException("Nope");
        }
    }

    public int getIntRepresentation(){
        switch(this) {
            case ZERO:
                return 0;
            case ONE:
                return 1;
            default:
                throw new RuntimeException("Nope");
        }
    }


    public static BinaryAllel getRandomAllel() {
        Random rand = new Random();
        return (Math.abs(rand.nextInt())%2) == 0 ? ZERO : ONE;
    }
}
