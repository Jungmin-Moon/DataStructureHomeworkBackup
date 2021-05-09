
package binarysearchtreeexample;

import binarysearchtreeexample.BinarySearchTree.Node;


public class BinarySearchTreeExample {

    
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(60);
        tree.insert(10);
        tree.insert(90);
        
        System.out.println("After inserting new elements");
        tree.inorderTraversal(tree.root);
        
        System.out.println("Post order traversal");
        tree.postOrder(tree.root);
        
        tree.insert(56);
        tree.insert(34);
        
        System.out.println("Inserting two new nodes");
        tree.inorderTraversal(tree.root);
        
        System.out.println("Post order version of above");
        tree.postOrder(tree.root);
        
        Node deletedNode = tree.deleteNode(tree.root, 70);
        
        System.out.println("Tree after deleteing 70");
        tree.inorderTraversal(tree.root);
        
        System.out.println();
        System.out.println("The max value node is: " + tree.maxNode(tree.root));
        
    }
    
}
