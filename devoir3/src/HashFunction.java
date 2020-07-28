/**
 * @author Mo Kleit
 */

public class HashFunction {

    private final int numberOfHashes;
    private final int[] hashes;
    private final long FNV_64_OFFSET_BASIS = 0xcbf29ce484222325L;
    private final long FVN_64_PRIME = 0x00000100000001B3L;

    public HashFunction(int numHashes){
        this.numberOfHashes = numHashes;
        this.hashes = new int[numHashes];
    }

    /**
     * Compute single hash using FNV-1 hash algorithm.
     * 
     * @param data for which a single hash will be generated.
     */
    private long generateHash(final byte[] data) {
        long hash = FNV_64_OFFSET_BASIS;
        for(byte data_byte: data){
            hash = hash * FVN_64_PRIME;
            hash = hash^data_byte;
        }
        return hash;
    }

    /**
     * Compute {@link HashFunction#numberOfHashes} of hashes.
     *
     * @param data for which which the hashes will be generated.
     */
    public int[] generateHashes(final byte[] data){
        int[] hash = splitHash(generateHash(data));
        int firstHash = hash[0];
        int secondHash = hash[1];

        for(int i=0; i < this.numberOfHashes; i++){
            firstHash += this.numberOfHashes * secondHash;
            this.hashes[i] = firstHash;
        }
        return this.hashes;
    }

    /**
     * Split hash in two.
     *
     * @param hash 64 bits hash.
     */
    private int[] splitHash(final long hash) {
        int[] splitHash = new int[2];
        splitHash[0] = (int) hash;
        splitHash[1] = (int) (hash >> 32);
        return splitHash;
    }
}
