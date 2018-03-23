package linearStructure.linkedList;

import linearStructure.position.Position;

public class Node implements Position {
    private Object element;
    private Node next;

    public Node(){
        this(null, null);
    }

    public Node(Object element, Node next) {
        this.element = element;
        this.next = next;
    }

    @Override
    public Object getElem() {
        return this.element;
    }

    @Override
    public Object setElem(Object e) {
        Object elem = this.element;
        this.element = e;
        return elem;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node newNext){
        this.next = newNext;
    }

    public static void main(String[] args){

    }


}
