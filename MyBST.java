/**
 * Name: Adi Krishnamoorthy
 * References: CSE 12 Lectures & Discussions
 * 
 * This file, MyBST, includes my implementation of the Binary Search
 * tree class. It includes a main class and several smaller methods
 * that are in the BST class.
 */
import java.util.ArrayList;

/**
 * This class, MyBST, is a generic class that has two generic variables
 * interface. It contains a subclass, MyBSTNode, which includes its
 * own set of methods.
 */
public class MyBST<K extends Comparable<K>, V> {

    //declaring instance variables
    MyBSTNode<K,V> root;
    int size;

    // Accounting for magic numbers
    private static final String NULL_MSG = "Null value";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    /**
     * This class, MyBSTNode, is a generic class with two generic 
     * variables and five instance variables representing
     * its data and connections.
     */
    static class MyBSTNode<K, V> {
        //declaring instance variables
        K key;
        V value;
        MyBSTNode<K,V> parent;
        MyBSTNode<K,V> left;
        MyBSTNode<K,V> right;

        /**
         * Constructor to initialize instance variables
         * @param key The node's key
         * @param value The value stored in the node
         * @param parent The parent node of the one in this class
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        /**
         * Returns the key of this node
         * @return the node's key
         */
        public K getKey(){
            return this.key;
        }

        /**
         * Returns the value of this node
         * @return the node's value
         */
        public V getValue(){
            return this.value;
        }

        /**
         * Returns the parent of this node
         * @return the node's parent
         */
        public MyBSTNode<K,V> getParent(){
            return this.parent;
        }

        /**
         * Returns the left child of this node
         * @return the node's left child
         */
        public MyBSTNode<K,V> getLeft(){
            return this.left;
        }

        /**
         * Returns the right child of this node
         * @return the node's right child
         */
        public MyBSTNode<K,V> getRight(){
            return this.right;
        }

        /**
         * Resets the key of this node
         * @param newKey The key that this node will be set to
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Resets the value of this node
         * @param newValue The value that this node will be set to
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Resets this node's parent
         * @param newParent This node's new parent node
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Resets this node's left child node
         * @param newParent This node's new left child node
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Resets this node's new right child node
         * @param newParent This node's new right child node
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /** Locates the successor of this node
         * @return the successor node of the current one
         */
        public MyBSTNode<K,V> successor(){
            // declaring a new Node to be the successor
            MyBSTNode<K,V> successorNode = null;
            // if there's a right child
            if (this.right != null){
                successorNode = this.right;
                while (successorNode.left != null){
                    successorNode = successorNode.left;
                }
            } 
            //if there's no right child
            else if (this.parent != null && this == this.parent.getLeft()) {
                successorNode = this.parent;
                boolean loopVar = true;
                while (loopVar){
                    //if the node is a right child
                    if (successorNode.getParent() != null && 
                            successorNode.getParent().getRight()
                            .equals(successorNode)){
                        successorNode = successorNode.getParent();
                    } else {
                        loopVar = false;
                    }
                } 
            }
            //returning the successor

            return successorNode;
        }
    }

    /**
     * Constructor to initialize instance variables
     */
    public MyBST(){
        this.root = null;
        this.size = ZERO;
    }

    /**
     * locates the successor of the node inputted
     * @param node The node for which we are looking for a successor
     * @return The successor node of the one inputted
     */
    protected MyBSTNode<K,V> successor(MyBSTNode<K,V> node){
        // Accounting for null nodes
        if (node == null){
            throw new NullPointerException(NULL_MSG);
        }
        //returning the successor node
        return node.successor();
    }

    /**
     * returns the BST's size
     * @return the number of elements in the BST
     */
    public int size(){
        //returning the size
        return this.size;
    }

    /**
     * inserts a new node into the BST
     * @param key The new node's key
     * @param value The new node's value
     * @return the value replaced by the new value
     */
    public V insert(K key, V value){
        // Accounting for null nodes
        if (key == null){
            throw new NullPointerException(NULL_MSG);
        }
        // If inserting a root
        if (this.root == null){
            this.root = new MyBSTNode<>(key, value, null);
            size += ONE;
            return null;
        }
        // Otherwise
        MyBSTNode<K,V> curNode = this.root;
        boolean loopVar = false;
        while (loopVar == false){
            //traversing rightwards
            if (key.compareTo(curNode.getKey()) > ZERO){
                //inserting if new leaf
                if (curNode.getRight() == null){
                    MyBSTNode<K,V> newNode = new MyBSTNode<>(key,
                                 value, curNode);
                    curNode.setRight(newNode);
                    size = size + ONE;
                    loopVar = true;
                    return null;
                }
                //if no new leaf at this point
                else {
                    curNode = curNode.getRight();
                }
            }
            // traversing leftwards
            else if (key.compareTo(curNode.getKey()) < ZERO){
                //inserting if new leaf
                if (curNode.getLeft() == null){
                    MyBSTNode<K,V> newNode = new MyBSTNode<>(key,
                                     value, curNode);
                    curNode.setLeft(newNode);
                    size = size + ONE;
                    loopVar = true;
                    return null;
                }
                //if no new leaf at this point
                else {
                    curNode = curNode.getLeft();
                }
            }
            // replacing value
            else {
                V val = curNode.getValue();
                curNode.setValue(value);
                return val;
            }
        }
        return null;
    }

    /**
     * Searches and returns a node with the given key. Returns
     * null if no such node exists
     * @param key The key of the node in question
     * @return the value in that node
     */
    public V search(K key){
        // null returns
        if (key == null || this.root == null){
            return null;
        }
        //Looping to find the key
        MyBSTNode<K,V> curNode = this.root;
        boolean loopVar = false;
        while (loopVar == false){
            //traversing rightwards
            if (key.compareTo(curNode.getKey()) > ZERO){
                if (curNode.getRight() == null){
                    return null;
                }
                curNode = curNode.getRight();
            } 
            // leftwards
            else if (key.compareTo(curNode.getKey()) < ZERO){
                if (curNode.getLeft() == null){
                    return null;
                }
                curNode = curNode.getLeft();
            }
            // if equal
            else {
                return curNode.getValue();
            }
        }
        return null;
    }

    /**
     * Searches, removes and returns a node with the given key. 
     * Returns null if no such node exists.
     * @param key The key of the node in question
     * @return the value of that node
     */
    public V remove (K key){
        // null returns
        if (key == null || this.root == null){
            return null;
        }

        //Looping to find the key
        MyBSTNode<K,V> curNode = this.root;
        boolean loopVar = false;
        while (loopVar == false){
            //traversing rightwards
            if (key.compareTo(curNode.getKey()) > ZERO){
                if (curNode.getRight() == null){
                    return null;
                }
                curNode = curNode.getRight();
            } 

            // traversing leftwards
            else if (key.compareTo(curNode.getKey()) < ZERO){
                if (curNode.getLeft() == null){
                    return null;
                }
                curNode = curNode.getLeft();
            }

            // if Node is found
            else {
                size = size - ONE;
                loopVar = true;
            }
        }
        V returnVar = curNode.getValue();

        // Reformatting the tree
        // First, seeing whether the currentNode is a left or right child.
        // Setting leftOrRight to zero if left child, one if right child
        int leftOrRight = ZERO;
        if (curNode.getKey().compareTo(curNode.parent.getKey()) > ZERO){
            leftOrRight = ONE;
        }
        // If node in question is a leaf node
        if (curNode.getLeft() == null && curNode.getRight() == null){
            if (leftOrRight == ZERO){
                curNode.parent.setLeft(null);
            } else {
                curNode.parent.setRight(null);
            }
            curNode.setParent(null);
        }
        // if it has a left child
        else if (curNode.getLeft() != null && curNode.getRight() == null){
            if (leftOrRight == ZERO){
                curNode.parent.setLeft(curNode.getLeft());
                curNode.left.setParent(curNode.getParent());
                curNode.setParent(null);
                curNode.setLeft(null);
            } else {
                curNode.parent.setRight(curNode.getLeft());
                curNode.left.setParent(curNode.getParent());
                curNode.setParent(null);
                curNode.setLeft(null);
            }
        }
        //if it has a right child
        else if (curNode.getLeft() == null && curNode.getRight() != null){
            if (leftOrRight == ZERO){
                curNode.parent.setLeft(curNode.getRight());
                curNode.right.setParent(curNode.getParent());
                curNode.setParent(null);
                curNode.setRight(null);
            } else {
                curNode.parent.setRight(curNode.getRight());
                curNode.right.setParent(curNode.getParent());
                curNode.setParent(null);
                curNode.setRight(null);
            }
        }
        //if it has two children
        else {
            MyBSTNode<K,V> succNode = curNode.successor();
            curNode.setKey(succNode.getKey());
            curNode.setValue(succNode.getValue());
            if (succNode == curNode.getRight()){
                curNode.setRight(succNode.getRight());
                curNode.right.setParent(curNode);
            } else {
                succNode.parent.setLeft(null);
                succNode.setParent(null);
            }
        }
        
        //returning the original value
        return returnVar;
    }

    /**
     * Creates an ArrayList of the nodes based on their keys
     * @return the ArrayList of the nodes
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        //creating the ArrayList
        ArrayList<MyBSTNode<K, V>> orderList = new ArrayList<>();
        //returning empty ArrayList if tree is empty
        if (this.root == null){
            return orderList;
        }
        // finding the node with the lowest key
        MyBSTNode<K,V> curNode = this.root;
        while (curNode.left != null){
            curNode = curNode.left;
        }
        //curNode now equals the node with the leftmost key
        //adding the nodes
        boolean addLoopVar = true;
        while (addLoopVar){
            orderList.add(curNode);
            curNode = curNode.successor();
            if (curNode == null){
                System.out.println("2");
                addLoopVar = false;
            }
        }
        //returning the completed ordered ArrayList
        return orderList;
    }
}
