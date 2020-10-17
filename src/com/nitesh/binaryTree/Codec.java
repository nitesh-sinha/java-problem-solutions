//        Serialization is the process of converting a data structure or object into a sequence of bits so that it can
//        be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed
//        later in the same or another computer environment.
//
//        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
//        serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
//        serialized to a string and this string can be deserialized to the original tree structure.
//
//        Example:
//
//        You may serialize the following tree:
//
//              1
//             / \
//            2   3
//               / \
//              4   5
//
//        as "[1,2,3,null,null,4,5]"
//
//        Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily
//        need to follow this format, so please be creative and come up with different approaches yourself.
//
//        Note: Do not use class member/global/static variables to store states. Your serialize and deserialize
//        algorithms should be stateless.

// Time complexity of both serialize() and deserialize() is O(n) where n=no. of nodes in tree


package com.nitesh.binaryTree;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // lets use a preorder serializer of tree
        StringBuilder serialOut = new StringBuilder();
        String result;
        if(root==null) {
            serialOut.append("N");
            return serialOut.toString();
        }
        getPreOrderSerialization(root, serialOut);
        result = serialOut.toString();
        return result.substring(0, result.length()-1); // strip the last comma
    }

    private void getPreOrderSerialization(TreeNode root, StringBuilder serialOut) {
        if(root==null) {
            serialOut.append("N,");
            return;
        }
        serialOut.append(root.val);
        serialOut.append(",");
        getPreOrderSerialization(root.left, serialOut);
        getPreOrderSerialization(root.right, serialOut);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataParts = data.split(",");
        // index, on JVM's heap, acting like a global variable to retain value across recursive calls of
        // doPreOrderDeserialization()
        int[] index = new int[]{0}; // one element integer array initialized to 0
        return doPreOrderDeserialization(dataParts, index);
    }

    private TreeNode doPreOrderDeserialization(String[] dataParts, int[] index) {
        if(dataParts[index[0]].equals("N"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(dataParts[index[0]]));
        index[0]++;
        root.left = doPreOrderDeserialization(dataParts, index);
        index[0]++;
        root.right = doPreOrderDeserialization(dataParts, index);
        // index[0]++;
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

