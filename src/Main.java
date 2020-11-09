import java.util.*;

public class Main {

    static class TreeNode {

      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    }


    public static void main(String[] args) {

        TreeNode root =     new TreeNode(3);
        root.left  =        new TreeNode(9);
        root.right =        new TreeNode(20);
        root.right.left =   new TreeNode(15);
        root.right.right =  new TreeNode(7);

        System.out.println(verticalTraversal(root));

    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {

        TreeMap<Integer, TreeSet<int[]>> map = new TreeMap<>();
        List<List<Integer>> res = new LinkedList<>();

        populate(root, 0, 0, map);

        for (int i : map.keySet()) {

            List<Integer> list = new LinkedList<>();

            for (int[] j : map.get(i))
                list.add(j[0]);

            res.add(list);

        }

        return res;
    }

    private static void populate(TreeNode root, int order, int level , Map<Integer, TreeSet<int[]>> map) {

        if (root == null) return;

        if (!map.containsKey(order))

            /*
            nested TreeSet is sorted by smallest 'level', if two nodes have the same position,
            then the value of the node that is reported first is the value that is smaller,
            read the question: If two nodes have the same position,
            then the value of the node that is reported first is the value that is smaller, see this:

                if : b[0] - a[0]
                then:
                Input
                [1,2,3,4,5,6,7]
                Output
                [[4],[2],[1,6,5],[3],[7]]
                Expected
                [[4],[2],[1,5,6],[3],[7]]
            */

            map.put(order, new TreeSet<int[]>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));

        map.get(order).add(new int[]{root.val, level});
        
        //order-1 => left
        //order+1 => right
        //level+1 => down (next level down, roots of current node)
        populate(root.left, order - 1, level + 1 , map);
        populate(root.right, order + 1, level + 1, map);
    }

}
