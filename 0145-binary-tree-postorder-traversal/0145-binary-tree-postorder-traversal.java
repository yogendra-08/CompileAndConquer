/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> result = new ArrayList<>();

        search(root, result);   
        return result;
    }

    private void search(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }
        search(root.left, result);      
        search(root.right, result);
        result.add(root.val);    
    }
}