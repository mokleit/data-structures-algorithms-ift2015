import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SparseVectorTest {

    private SparseVector sparseVector;

    @BeforeEach
    void setup(){
        sparseVector = new SparseVector();
    }

    @Test
    void willReturnLength(){
        assertEquals(sparseVector.length(), 10);
    }

    @Test
    void willReturnSizeWhenEmpty(){
        assertEquals(sparseVector.size(), 0);
    }

    @Test
    void willReturnSize(){
        sparseVector.set(2, 2);
        assertEquals(sparseVector.size(), 1);
        sparseVector.set(6, 1);
        assertEquals(sparseVector.size(), 2);
        sparseVector.remove(6);
        assertEquals(sparseVector.size(), 1);
    }

    @Test
    void willTReturnNullWhenIndexOutOfBounds(){
        assertNull(sparseVector.get(-1));
        assertNull(sparseVector.get(10));

    }

    @Test
    void willReturnNullWhenVectorIsEmpty(){
        assertNull(sparseVector.get(0));
    }

    @Test
    void willReturnNullWhenIndexDoesNotExistInVector(){
        sparseVector.set(2, 2);
        sparseVector.set(6, 1);
        sparseVector.set(7, 8);
        sparseVector.set(0, 100);
        assertNull(sparseVector.get(9));
    }

    @Test
    void willReturnValueOfVector(){
        sparseVector.set(2, 2);
        sparseVector.set(6, 1);
        sparseVector.set(7, 8);
        sparseVector.set(0, 100);
        assertEquals(1, sparseVector.get(6));
    }

    @Test
    void willSetElementWhenSparseVectorIsEmpty(){
        sparseVector.set(3, 45);
        assertEquals(45, sparseVector.get(3));
    }

    @Test
    void willSetElementAtTheEndOfSparse(){
        sparseVector.set(3, 45);
        sparseVector.set(7, 40);
        assertEquals(40, sparseVector.get(7));
    }

    @Test
    void willSetElementBetweenNodes(){
        sparseVector.set(3, 45);
        sparseVector.set(7, 40);
        assertEquals(2, sparseVector.size());
        sparseVector.set(5, 10);
        assertEquals(10, sparseVector.get(5));
        assertEquals(3, sparseVector.size());
    }

    @Test
    void willUpdateHead(){
        sparseVector.set(3, 45);
        assertEquals(1, sparseVector.size());
        sparseVector.set(0, 1);
        assertEquals(2, sparseVector.size());
        assertEquals(1, sparseVector.get(0));
    }

    @Test
    void willNotCrashWhenTryingToRemoveAnElementWithIndexOutOfBounds(){
        assertEquals(0, sparseVector.size());
        sparseVector.remove(-1);
        sparseVector.remove(10);
        assertEquals(0, sparseVector.size());
    }

    @Test
    void willNotModifyListWhenTryingToRemoveElementWithIndexNotInVector(){
        sparseVector.set(3, 45);
        sparseVector.set(7, 40);
        assertEquals(2, sparseVector.size());
        sparseVector.remove(9);
        assertEquals(2, sparseVector.size());
    }

    @Test
    void willRemoveLastElementSuccessfully(){
        sparseVector.set(3, 45);
        sparseVector.set(7, 40);
        assertEquals(2, sparseVector.size());
        sparseVector.remove(7);
        assertEquals(1, sparseVector.size());
        assertNull(sparseVector.get(7));
    }

    @Test
    void willRemoveOnlyElementSuccessfully(){
        sparseVector.set(3, 45);
        sparseVector.set(7, 40);
        sparseVector.set(9, 20);
        assertEquals(3, sparseVector.size());
        sparseVector.remove(7);
        assertEquals(2, sparseVector.size());
        assertNull(sparseVector.get(7));
    }

    @Test
    void willRemoveInBetweenElementSuccessfully(){
        sparseVector.set(3, 45);
        assertEquals(1, sparseVector.size());
        sparseVector.remove(3);
        assertEquals(0, sparseVector.size());
        assertNull(sparseVector.get(3));
    }
}
