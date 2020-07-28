import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashFunctionTest {

    @Test
    void willReturnArrayOfKHashes() {
        final HashFunction hashFunction = new HashFunction(5);
        final byte[] word = "hashhash".getBytes();
        assertEquals(5, hashFunction.generateHashes(word).length);
    }

    @Test
    void willReturnSameHashForSameKey() {
        final HashFunction hashFunction = new HashFunction(1);
        final byte[] word1 = "hashhash".getBytes();
        final byte[] word2 = "hashhash".getBytes();
        assertEquals(hashFunction.generateHashes(word1)[0], hashFunction.generateHashes(word2)[0]);
    }

}