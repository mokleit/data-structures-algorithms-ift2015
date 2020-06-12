/**
 * @author Mohamed Kleit - 1061121
 */

public class SparseVector {

    private int length = 0;
    private int size = 0;
    private Node head;

    public class Node {
        int index;
        Object value;
        Node next;

        Node(int index, Object value){
            this.index = index;
            this.value = value;
        }
    }

    public SparseVector() {
        this(10);
    }

    public SparseVector(int length) {
        this.length = length;
    }

    // Obtenir la valeur de l'élément à la position index
    public Object get(int index) {
        Object value;
        if(index < 0 || index >= length || head == null){
            value = null;
        }
        else {
            Node current = head;
            while(current.index != index && current.next != null){
                current = current.next;
            }
            if(current.index == index){
                value = current.value;
            }
            else {
                value = null;
            }
        }
        return value;
    }

    // Ajouter ou mettre à jour l'élément à la position index
    public void set(int index, Object value) {
        if(index < 0 || index >= length){
            return;
        }
        if(head == null){
            head = new Node(index, value);
        }
        else {
            if(index < head.index){
                Node temp = head;
                head = new Node(index, value);
                head.next = temp;
            }
            else{
                Node current = head;
                while (current.next != null && current.next.index < index){
                    current=current.next;
                }
                if(current.next != null){
                    Node temp = current.next;
                    current.next = new Node(index, value);
                    current.next.next = temp;
                }
                else {
                    current.next = new Node(index, value);
                }
            }
        }
        size++;
    }

    // Supprimer l'élément à la position index
    public void remove(int index) {
        if(index < 0 || index >= length || head == null) {
            return;
        }
        if (head.index == index){
            head = null;
            size--;
        }
        else {
            Node current = head;
            while(current.next != null && current.next.index != index){
                current = current.next;
            }
            if(current.next != null){
                current.next = current.next.next;
                size--;
            }
        }
    }

    // Longueur du vecteur creux
    public int length() {
        return this.length;
    }

    // Nombre d'éléments non nuls
    public int size() {
        return this.size;
    }
}