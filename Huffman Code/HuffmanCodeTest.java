import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodeTest {

    @Test
    @Disabled
    void willReturnArrayOfFrequencies(){
        HuffmanCode huffman = new HuffmanCode();
        String text = "test";
    }
}

class NodeTest {
    @Test
    void willReturnFalseWhenNodeIsInternal(){
        Node node = new Node(new Node('a', 1), new Node('b', 1));
        assertFalse(node.isLeaf());
    }
    @Test
    void willStoreFrequencyForInternalNode(){
        Node node = new Node(new Node('a', 1), new Node('b', 4));
        assertEquals(5, node.frequency);
    }
    @Test
    void willReturnTrueWhenNodeIsLeaf(){
        Node node = new Node('A', 3);
        assertTrue(node.isLeaf());
    }
    @Test
    void willReturnDifferenceInFrequencyBetweenTwoNodes() {
        Node firstNode = new Node('A', 3);
        Node secondNode = new Node('A', 5);
        assertEquals(-2, firstNode.compareTo(secondNode));
    }

    @Test
    void willReturnDotFormatForInternalNode(){
        Node node = new Node(new Node('A', 1), new Node('a', 2));
        assertEquals(node.hashCode() + "[label="+node.frequency+",shape=rectangle, width=.5", node.toString());
    }

    @Test
    void willReturnDotFormatForLeafNode(){
        Node node = new Node('A',5);
        assertEquals(node.hashCode()+ "[label=\"{{\'" + node.symbol + "\'|" + node.frequency + "}}\",shape=record]", node.toString());
    }
}