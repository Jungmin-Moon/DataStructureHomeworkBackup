
package binarysearchtreeexample;

public class BinarySearchTree {
    
    public class Node {
        int data; 
        Node left;
        Node right;
        
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
        public String toString() {
            return data + "";
        }
    }
    
    public Node root;
    
    BinarySearchTree() {
        root = null;
    }
    
    public void insert(int data) {
        Node newNode = new Node(data);
        
        if(root == null) {
            root = newNode;
            return;
        } else {
            Node current = root, parent = null;
            
            while(true) {
                parent = current;
                
                if(data < current.data) {
                    current = current.left;
                    if(current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if(current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    
    public Node minNode(Node root) {
        if(root.left != null) 
            return minNode(root.left);
        else 
            return root;
    }
    
    public Node maxNode(Node root) {
        if(root.right != null) 
            return maxNode(root.right);
        else 
            return root;
    }
    
    public Node deleteNode(Node node, int value) {
        if(node == null) {
            return null;
        } else {
            if(value < node.data) {
                node.left = deleteNode(node.left, value);
            } else if(value > node.data) {
                node.right = deleteNode(node.right, value);
            } else {
                if(node.left == null && node.right == null) {
                    node = null;
                } else if(node.left == null) {
                    node = node.right;
                } else if(node.right == null) {
                    node = node.left;
                } else {
                    
                    Node temp = minNode(node.right);
                    
                    node.data = temp.data;
                    
                    node.right = deleteNode(node.right, temp.data);
                }
            }
            return node;
        }
        
    }
    
    public void inorderTraversal(Node node) {
        if(root == null) {
            System.out.println("Tree is empty.");
            return;
        } else {
            if(node.left != null) 
                inorderTraversal(node.left);
            
            System.out.print(node.data + " ");
            
            if(node.right != null)
                inorderTraversal(node.right);
        }
    }
    
    public void postOrder(Node node) {
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    
}
