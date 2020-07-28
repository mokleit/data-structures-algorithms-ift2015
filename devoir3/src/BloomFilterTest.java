import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {

    @Test
    void willSuccessfullyCreateBloomFilterOfSpecificSize() {
        final BloomFilter bloomFilter = new BloomFilter(575, 10);
        assertEquals(575, bloomFilter.size());
    }

    @Test
    void willSuccessfullyCreateBloomFilterBasedOnExpectedElementsAndProbability() {
        final BloomFilter bloomFilter = new BloomFilter(575, 0.1);
        assertEquals(2756, bloomFilter.size());
    }

    @Test
    void willSuccessfullyAddWordToBloomFilter() {
        final BloomFilter bloomFilter = new BloomFilter(32, 3);
        final byte[] word = "macarena".getBytes();
        bloomFilter.add(word);
        assertTrue(bloomFilter.contains(word));
    }

    @Test
    void willNotFindWordThatWasNotAddedToBloomFilter() {
        final BloomFilter bloomFilter = new BloomFilter(100, 0.2);
        final byte[] word1 = "skriniar".getBytes();
        final byte[] word2 = "lukaku".getBytes();
        final byte[] word3 = "martinez".getBytes();
        final byte[] word4 = "eriksen".getBytes();
        bloomFilter.add(word1);
        bloomFilter.add(word2);
        bloomFilter.add(word3);
        bloomFilter.add(word4);
        assertFalse(bloomFilter.contains("conte".getBytes()));
    }

    @Test
    void willSuccessfullyResetBloomFilter() {
        final BloomFilter bloomFilter = new BloomFilter(100, 0.2);
        final byte[] word1 = "skriniar".getBytes();
        final byte[] word2 = "lukaku".getBytes();

        bloomFilter.reset();

        assertEquals(0, bloomFilter.count());
        assertEquals(0, bloomFilter.countBitsSet());
    }

    @Test
    void willReturnSizeOfBloomFilterBitSet() {
        final BloomFilter bloomFilter = new BloomFilter(50, 2);
        assertEquals(50, bloomFilter.size());
    }

    @Test
    void willReturnNumberOfInsertedElements() {
        final BloomFilter bloomFilter = new BloomFilter(50, 2);
        final byte[] word1 = "macguire".getBytes();
        final byte[] word2 = "pogba".getBytes();
        final byte[] word3 = "fernandes".getBytes();
        final byte[] word4 = "rashford".getBytes();
        bloomFilter.add(word1);
        bloomFilter.add(word2);
        bloomFilter.add(word3);
        bloomFilter.add(word4);
        assertEquals(4, bloomFilter.count());
    }

}