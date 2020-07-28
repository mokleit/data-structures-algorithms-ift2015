/**
 * @author Mo Kleit
 */
public class BloomFilter {

    private final BitSet bitSet;
    private final int numHashes;
    private final HashFunction hash;
    private int insertedElementsCount = 0;

    /**
     * Crée un filtre de Bloom basé sur la taille de l'ensemble de bits et du
     * nombre de fonctions de hachage.
     *
     * @param numBits taille de l'ensemble de bits
     * @param numHashes nombre de fonctions de hachage
     */
    public BloomFilter(int numBits, int numHashes) {
        this.bitSet = new BitSet(numBits);
        this.numHashes = numHashes;
        this.hash = new HashFunction(numHashes);
    }

    /**
     * Crée un filtre de Bloom basé sur le nombre d'éléments attendus et de la
     * probabilité de faux positifs désirée.
     *
     * @param numElems nombre d'éléments à insérer
     * @param falsePosProb probabilité de faux positifs
     */
    public BloomFilter(int numElems, double falsePosProb) {
        int nbits = computeNumberOfBits(numElems, falsePosProb);
        int numHashes = computeNumberOfHashes(falsePosProb);
        this.bitSet = new BitSet(nbits);
        this.numHashes = numHashes;
        this.hash = new HashFunction(numHashes);
    }

    /**
     * Compute number of hashes required based on the filter's
     * tolerable probability of false positives.
     *
     * @param falsePosProb expected probability of false positives
     */
    private int computeNumberOfHashes(double falsePosProb) {
        return (int) Math.ceil(-Math.log(falsePosProb) /Math.log(2));
    }

    /**
     * Compute number of bits required for the bit set based on the filter's expected number of elements
     * and the expected probability of false positives.
     *
     * @param numElems expected number of elements the filter will hold
     * @param falsePosProb expected probability of false positives
     * @return
     */
    private int computeNumberOfBits(int numElems, double falsePosProb) {
        return (int) Math.ceil(-numElems * Math.log(falsePosProb) / Math.pow(Math.log(2), 2));
    }

    /**
     * Ajoute un élément au filtre de Bloom.
     *
     * @param key l'élément à insérer
     */
    public void add(byte[] key) {
        this.insertedElementsCount++;
        int[] hashes = this.hash.generateHashes(key);
        for(int hash: hashes){
            int bitIndex = (hash & 0xfffffff) % this.size();
            this.bitSet.set(bitIndex);
        }
    }

    /**
     * Cherche pour l'élément dans le filtre de Bloom.
     *
     * @param key l'élément à trouver
     * @return si l'élément est possiblement dans le filtre
     */
    public boolean contains(byte[] key) {
        int[] hashes = this.hash.generateHashes(key);
        boolean present = true;
        for(int hash: hashes){
            int bitIndex = (hash & 0xfffffff) % this.size();
            if(!this.bitSet.get(bitIndex)){
                present = false;
                break;
            }
        }
        return present;
    }

    /**
     * Remet à zéro le filtre de Bloom.
     */
    public void reset() {
        this.bitSet.clear();
        this.insertedElementsCount = 0;
    }

    /**
     * Retourne le nombre de bits du filtre de Bloom.
     *
     * @return nombre de bits
     */
    public int size() {
        return this.bitSet.size();
    }

    /**
     * Retourne le nombre d'éléments insérés dans le filtre de Bloom.
     *
     * @return nombre d'éléments insérés
     */
    public int count() {
        return this.insertedElementsCount;
    }

    /**
     * Compute, in a probabilistic way, the number of elements inserted based on the number of bits
     * set in the filter so far.
     *
     * @return approximation of the number of elements inserted.
     */
    private int computeNumberOfInsertedElements() {
        int bitsSet = this.bitSet.countBitsSet();
        double denominator = this.numHashes * Math.log((double)(this.size()-1)/this.size());
        double numerator = Math.log((double)(this.size()-bitsSet)/this.size());
        return (int) Math.ceil(numerator/denominator);
    }

    /**
     * Retourne la probabilité actuelle de faux positifs.
     *
     * @return probabilité de faux positifs
     */
    public double fpp() {
        double cont = 1 - 1/(double)this.size();
        double power = Math.pow(cont, this.numHashes*this.count());
        return Math.pow(1-power, this.numHashes);
    }

    /**
     * Counts the number of bits set to 1 in the filter.
     */
    public int countBitsSet() {
        return this.bitSet.countBitsSet();
    }
}
