import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BitSetTest {

    @Test
    void willCreateBitSetOfSpecifiedSize() {
        final BitSet bitSet = new BitSet(32);
        assertEquals(32, bitSet.size());
    }

    @Test
    void willSetBitSetAtspecificIndex() {
        final BitSet bitSet = new BitSet(32);
        bitSet.set(10);
        assertTrue(bitSet.get(10));
    }

    @Test
    void willThrowExceptionWhenSettingOutOfBoundsIndex() {
        final BitSet bitSet = new BitSet(32);
        assertThrows(RuntimeException.class, () -> {bitSet.get(32);});
    }

    @Test
    void willThrowExceptionWhenGettingOutOfBoundsIndex() {
        final BitSet bitSet = new BitSet(15);
        assertThrows(RuntimeException.class, () -> {bitSet.get(-1);});
    }

    @Test
    void willClearIndexAtSpecificIndex() {
        final BitSet bitSet = new BitSet(10);
        bitSet.set(1);
        assertTrue(bitSet.get(1));
        bitSet.clear(1);
        assertFalse(bitSet.get(1));
    }

    @Test
    void willCountNumberOfBitsSet() {
        final BitSet bitSet = new BitSet(10);
        bitSet.set(1);
        bitSet.set(8);
        bitSet.set(5);

        assertEquals(3, bitSet.countBitsSet());
    }

    @Test
    void willClearAllIndexes() {
        final BitSet bitSet = new BitSet(10);
        bitSet.set(1);
        bitSet.set(8);
        bitSet.set(5);
        bitSet.clear();

        assertEquals(0, bitSet.countBitsSet());
    }
}