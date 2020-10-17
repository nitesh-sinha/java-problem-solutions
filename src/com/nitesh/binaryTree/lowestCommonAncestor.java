//        Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
//
//        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p
//        and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant
//        of itself).”
//                _______3______
//               /              \
//            ___5__          ___1__
//           /      \        /      \
//          6      _2       0       8
//                /  \
//                7   4
//
//               For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3.
//               Another example is LCA of nodes 5 and 4 is 5, since a
//               node can be a descendant of itself according to the LCA definition.
//
//     Note:
//
//        All of the nodes' values will be unique.
//        p and q are different and both values will exist in the BST.

// Time complexity: O(log N) where N = no. of nodes in BST


package com.nitesh.binaryTree;

public class lowestCommonAncestor {
    public TreeNode lowestCommonAncestorFn(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        return findLCA(root, p, q);
    }

    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = root;
        if(root.val==p.val)
            return p;
        if(root.val==q.val)
            return q;

        // Check if nodes p and q on opposite sides of root
        if(p.val<root.val && root.val<q.val)
            return root;
        if(q.val<root.val && root.val<p.val)
            return root;

        // p and q are on same side of root
        // Check of they are on left subtree or right subtree
        if(p.val<root.val) // q is also < root.val
            lca = findLCA(root.left, p, q);
        if(p.val>root.val) // q is also > root.val
            lca = findLCA(root.right, p, q);

        return lca;
    }
}
