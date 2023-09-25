package game605.test.qs;

public class Q100 {

    public boolean flag = true;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        eachEq(p, q);
        return flag;
    }

    public void eachEq(TreeNode p, TreeNode q){
        if(p == null && q == null)
            return;
        if(p==null){
            flag = false;
            return;
        }
        if(q==null){
            flag = false;
            return;
        }
        if(p.val != q.val){
            flag = false;
            return;
        }
        eachEq(p.right, q.right);
        eachEq(p.left, p.left);
    }
}
