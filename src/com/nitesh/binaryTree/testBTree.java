package com.nitesh.binaryTree;

import java.util.List;

public class testBTree {
    public void testBTreeFn() {
        Codec codec = new Codec();
        //String preOrderserialzedTree = "1,2,N,N,3,4,N,N,5,N,8,N,9,10,11,N,N,N,N";
        //TreeNode root = codec.deserialize(preOrderserialzedTree);
        //System.out.println(codec.serialize(root));

        // How to print a tree given its root?
        // BTreePrinter.printNode(root);

        // test height balanced tree class
        String preOrderserialzedTree = "3,9,N,N,20,15,N,N,7,N,N";
        TreeNode root = codec.deserialize(preOrderserialzedTree);
        BTreePrinter.printNode(root);
        isBalanced x = new isBalanced();
        if(x.isBalancedFn(root))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");
    }
}
