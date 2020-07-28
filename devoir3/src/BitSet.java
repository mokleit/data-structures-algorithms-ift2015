import java.util.Arrays;

/**
 * @author Mo Kleit - 1061121
 */
public class BitSet {
    private int[] bitSet;

    /**
     * Crée un ensemble de bits, d'une certaine taille. Ils sont initialisés à
     * {@code false}.
     *
     * @param nbits taille initiale de l'ensemble
     */
    public BitSet(int nbits) {
        if(nbits < 0){
            throw new RuntimeException("BitSet can't be of negative size.");
        }
        bitSet = new int[nbits];
    }

    /**
     * Retourne la valeur du bit à l'index spécifié.
     *
     * @param bitIndex l'index du bit
     * @return la valeur du bit à l'index spécifié
     */
    public boolean get(int bitIndex) {
        checkIndexOutOfBounds(bitIndex);
        return bitSet[bitIndex] == 1;
    }

    /**
     * Définit le bit à l'index spécifié comme {@code true}.
     *
     * @param bitIndex l'index du bit
     */
    public void set(int bitIndex) {
        checkIndexOutOfBounds(bitIndex);
        bitSet[bitIndex] = 1;
    }

    private void checkIndexOutOfBounds(int bitIndex) {
        if(bitIndex < 0 || bitIndex >= this.bitSet.length) {
            throw new RuntimeException("Bit index is out of bounds. Indexes go from 0 to " + (this.bitSet.length-1));
        }
    }

    /**
     * Définit le bit à l'index spécifié comme {@code false}.
     *
     * @param bitIndex l'index du bit
     */
    public void clear(int bitIndex) {
        bitSet[bitIndex] = 0;
    }

    /**
     * Return size of BitSet.
     */
    public int size() {
        return this.bitSet.length;
    }

    /**
     * Clear entire bitset.
     */
    public void clear(){
        this.bitSet = new int[this.bitSet.length];
    }

    /**
     * Return number of true elements.
     */
    public int countBitsSet() {
        int count=0;
        for(int i = 0; i < this.size(); i++) {
            if(bitSet[i] == 1) {
                count++;
            }
        }
        return count;
    }
}
