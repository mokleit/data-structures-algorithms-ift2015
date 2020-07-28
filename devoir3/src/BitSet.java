/**
 * @author Mo Kleit
 */
public class BitSet {
    private final int[] bitSet;
    private final int totalBitsNumber;
    private static final int ADDRESS_BITS_PER_ARRAY_INDEX = 5;

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
        this.totalBitsNumber = nbits;
        bitSet = new int[findRequiredNumberOfIndexes(nbits)];
    }

    private int findRequiredNumberOfIndexes(final int nbits) {
        //Decrease number of bits by 1 to handle case where nbits is a multiple of BITS_PER_ARRAY_INDEX
        int indexes = (nbits-1) >> ADDRESS_BITS_PER_ARRAY_INDEX;
        return indexes + 1;
    }

    /**
     * Retourne la valeur du bit à l'index spécifié.
     *
     * @param bitIndex l'index du bit
     * @return la valeur du bit à l'index spécifié
     */
    public boolean get(int bitIndex) {
        checkIndexOutOfBounds(bitIndex);
        int arrayIndex = bitIndex >> ADDRESS_BITS_PER_ARRAY_INDEX;
        return (this.bitSet[arrayIndex] & (1 << bitIndex)) != 0;
    }

    /**
     * Définit le bit à l'index spécifié comme {@code true}.
     *
     * @param bitIndex l'index du bit
     */
    public void set(int bitIndex) {
        checkIndexOutOfBounds(bitIndex);
        int arrayIndex = bitIndex >> ADDRESS_BITS_PER_ARRAY_INDEX;
        this.bitSet[arrayIndex] |= (1 << bitIndex);
    }

    /**
     * Check if bit to access is out of bounds.
     *
     * @param bitIndex
     */
    private void checkIndexOutOfBounds(int bitIndex) {
        if(bitIndex < 0 || bitIndex >= this.totalBitsNumber) {
            throw new RuntimeException("Bit index is out of bounds. Indexes go from 0 to " + (this.totalBitsNumber-1));
        }
    }

    /**
     * Définit le bit à l'index spécifié comme {@code false}.
     *
     * @param bitIndex l'index du bit
     */
    public void clear(int bitIndex) {
        checkIndexOutOfBounds(bitIndex);
        int arrayIndex = bitIndex >> ADDRESS_BITS_PER_ARRAY_INDEX;
        this.bitSet[arrayIndex] &= ~(1 << bitIndex);
    }

    /**
     * Return number of bits accessible for use in the BitSet.
     */
    public int size() {
        return this.totalBitsNumber;
    }

    /**
     * Return length of the array holding the bits or equivalently, the number of accessible indexes.
     */
    public int length() {
        return this.bitSet.length;
    }

    /**
     * Clear entire bitset.
     */
    public void clear(){
        for(int i = 0; i < this.bitSet.length; i++) {
            this.bitSet[i] = 0;
        }
    }

    /**
     * Count number of bits set.
     */
    public int countBitsSet() {
        int count=0;
        for(int i = 0; i < this.length(); i++) {
            int j = this.bitSet[i];
            while(j != 0) {
                j = j & (j-1);
                count++;
            }
        }
        return count;
    }
}
