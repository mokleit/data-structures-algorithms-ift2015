/**
 * @author Kleit Mo
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Node implements Comparable<Node> {
    char symbol;
    int frequency;
    Node left;
    Node right;

    // Leaf Node
    Node(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    // Internal Node
    Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }

    boolean isLeaf() {
        return this.left==null && this.right==null;
    }

    // TODO À compléter : comparaison selon la fréquence d'occurrence
    @Override
    public int compareTo(Node node) {
        return this.frequency - node.frequency;
    }

    // TODO À compléter : représentation du nœud en format DOT
    @Override
    public String toString() {
        String dot;
        if(this.isLeaf()){
            dot = this.hashCode()+ " [label=\"{{\'" + this.symbol + "\'|" + this.frequency + "}}\", shape=record]";
        }
        else {
            dot = this.hashCode() + " [label="+this.frequency+", shape=rectangle, width=.5]";
        }
        return dot;
    }
}

class HuffmanCode {
    /**
     * @param text Texte à analyser
     * @return Fréquence de chaque caractère ASCII sur 8 bits
     */

    //alphabet size
    private static final int alphabet = 256;

    //Get character frequencies based on array containing 256 possible characters
    private static int[] getCharacterFrequencies(String text) {
        char[] input = text.toCharArray();
        int[] frequencies = new int[alphabet];
        for (char c : input) {
            frequencies[c]++;
        }
        return frequencies;
    }

    /**
     * @param charFreq Fréquence de caractères
     * @return Nœud racine de l'arbre
     */
    private static Node getHuffmanTree(int[] charFreq) {
        //Build priority queue with single node trees based on input characters
        PriorityQueue<Node> huffmanTree = new PriorityQueue<>(alphabet);
        for(char c=0; c < charFreq.length; c++){
            if(charFreq[c]!=0){
                huffmanTree.offer(new Node(c, charFreq[c]));
            }
        }

        //merge two smallest trees until there is only one element left in the priority queue
        while(huffmanTree.size() > 1){
            Node smallestElement = huffmanTree.poll();
            Node secondSmallestElement = huffmanTree.poll();
            huffmanTree.offer(new Node(smallestElement, secondSmallestElement));
        }
        return huffmanTree.poll();
    }

    /**
     * @param node Nœud actuel
     * @param code Code Huffman
     */
    //Traverse the tree using DFS and inorder traversal
    private static void printTable(Node node, String code) {
        if(node == null)
            return;
        printTable(node.left, code + "0");
        if(node.isLeaf()){
            System.out.println(node.symbol + " " + node.frequency + " " + code);
        }
        printTable(node.right, code + "1");
    }

    /**
     * @param node Nœud de départ
     */
    private static void printGraph(Node node) {
        if(node==null)
            return;
        System.out.println("graph {\n\tnode [style=rounded]");
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node parent = queue.poll();
            System.out.println("\t"+parent.toString());
            if(!parent.isLeaf()){
                System.out.println("\t"+parent.hashCode() + " -- " + parent.left.hashCode() + " [label=0]");
                System.out.println("\t"+parent.hashCode() + " -- " + parent.right.hashCode() + " [label=1]");
                queue.add(parent.left);
                queue.add(parent.right);
            }
        }
        System.out.println("}");
    }

    // Ne pas modifier
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node root = getHuffmanTree(getCharacterFrequencies(reader.readLine()));

        // Table
        if (args.length == 0 || Arrays.asList(args).contains("table")) {
            System.out.println("Char Freq Code\n---- ---- ----");
            printTable(root, "");
        }

        // Graphe
        if (args.length == 0 || Arrays.asList(args).contains("graph")) {
            printGraph(root);
        }
    }
}
