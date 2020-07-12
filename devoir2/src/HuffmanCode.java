/**
 * @author Prénom Nom - Matricule
 * @author Prénom Nom - Matricule
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
        String dot="";
        if(this.isLeaf()){
            dot = this.hashCode()+ "[label=\"{{\'" + this.symbol + "\'|" + this.frequency + "}}\",shape=record]";
        }
        else {
            dot = this.hashCode() + "[label="+this.frequency+",shape=rectangle, width=.5";
        }
        return dot;
    }
}

class HuffmanCode {
    /**
     * @param text Texte à analyser
     * @return Fréquence de chaque caractère ASCII sur 8 bits
     */
    private static int[] getCharacterFrequencies(String text) {
        return null; // TODO À compléter
    }

    /**
     * @param charFreq Fréquence de caractères
     * @return Nœud racine de l'arbre
     */
    private static Node getHuffmanTree(int[] charFreq) {
        return null; // TODO À compléter
    }

    /**
     * @param node Nœud actuel
     * @param code Code Huffman
     */
    private static void printTable(Node node, String code) {
        // TODO À compléter
    }

    /**
     * @param node Nœud de départ
     */
    private static void printGraph(Node node) {
        // TODO À compléter
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
