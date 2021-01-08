import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

    @Test
    void willReturnTotalNumberOfBitsEffectivelyUsable() {
        final BitSet bitSet = new BitSet(32);
        assertEquals(32, bitSet.size());
    }

    @Test
    void willReturnTotalNumberOfAccessibleArrayIndices() {
        final BitSet bitSetOne = new BitSet(31);
        final BitSet bitSetTwo = new BitSet(32);
        final BitSet bitSetThree = new BitSet(64);
        final BitSet bitSetFour = new BitSet(65);
        assertEquals(1, bitSetOne.length());
        assertEquals(1, bitSetTwo.length());
        assertEquals(2, bitSetThree.length());
        assertEquals(3, bitSetFour.length());
    }

    @Test
    void willSetBitSetAtSpecificIndex() {
        final BitSet bitSet = new BitSet(32);
        bitSet.set(10);
        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(31);
        bitSet.set(15);
        assertTrue(bitSet.get(10));
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(1));
        assertTrue(bitSet.get(31));
        assertTrue(bitSet.get(15));
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