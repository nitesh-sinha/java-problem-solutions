//        One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
//
//            _9_
//           /   \
//          3     2
//         / \   / \
//        4   1  #  6
//       / \ / \   / \
//       # # # #   # #
//        For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
//
//        Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
//
//        Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
//
//        You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
//
//        Example 1:
//
//        Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//        Output: true
//        Example 2:
//
//        Input: "1,#"
//        Output: false
//        Example 3:
//
//        Input: "9,#,#,1"
//        Output: false

package com.nitesh.stack;

import java.util.Stack;

public class isValidSerialization {
    public boolean isValidSerializationFn(String preorder) {
        String[] nodes = preorder.split(",");
        Stack<String> verifStack = new Stack<>();

        for(String node : nodes) {
            if(node.equals("#")) {
                while(!verifStack.isEmpty() && verifStack.peek().equals("#")) {
                    verifStack.pop(); // remove the # from top of stack
                    if(verifStack.isEmpty())
                        return false; // since two # should be preceeded by a root node
                    verifStack.pop(); // remove the root node(of #) from top of stack
                    // if top of stack is still a # it means thats a # from some left subtree
                    // and we have just verified the correctness of a right subtree
                    // so continue this process of popping the # and nodes until this
                    // condition is true
                }
            }
            // we add the node if it is not a #
            // Also when two # are seen in input, after removing necessary nodes from stack,
            // the second # needs to be added back to stack. This second # in stack, in essence,
            // now denotes that we have verified the correctness at the subtree rooted at this second #.
            verifStack.push(node);
        }
        // This final # in stack is symbolic of verified correctness at the root of input tree.
        return (verifStack.size() == 1 && verifStack.peek().equals("#"));
    }
}
