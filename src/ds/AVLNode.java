package ds;

/**
 * @author ZQ
 * @Date 2020/4/12
 */
public class AVLNode {
    private int data;
    private int depth;
    private int balance;
    private AVLNode parent;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        depth = 1;
    }

    public void insert(AVLNode root, int data) {
        if (data < root.data) {
            //小于则往左插
            if (root.left != null) {
                insert(root.left, data);
            }else {
                root.left = new AVLNode(data);
                root.left.parent = root;
            }
        }else {
            //大于则往右插
            if (root.right != null){
                insert(root.right, data);
            }else {
                root.right = new AVLNode(data);
                root.right.parent = root;
            }
        }

        //插入完成，计算平衡因子
        root.balance = calcBalance(root);
        //左子树高，则先右旋
        if (root.balance > 1){
            //右子孙高，先左旋
            if (root.left.balance == -1){
                left_rotate(root.left);
            }
            right_rotate(root);
        }
        //右子树高，则先左旋
        if (root.balance < -1){
            //左子孙高，则先右旋
            if (root.right.balance == 1){
                right_rotate(root.right);
            }
            left_rotate(root);
        }
        //调整完成，重新计算平衡因子和树的深度
        balance = calcBalance(root);
    }

    private void left_rotate(AVLNode parent) {
        AVLNode grandparent = parent.parent;
        AVLNode rightSon = parent.right;
        AVLNode leftGrandson = right.left;
        //右子变父
        rightSon.parent = grandparent;
        if (grandparent != null){
            if (parent == grandparent.right){
                grandparent.right = rightSon;
            }else if (parent == grandparent.left){
                grandparent.left = rightSon;
            }
        }
        rightSon.left = parent;
        //左孙变右子
        parent.right = leftGrandson;
        if (leftGrandson != null){
            leftGrandson.parent = parent;
        }
        parent.depth = calcDepth(parent);
        parent.balance = calcBalance(parent);
        rightSon.depth = calcDepth(rightSon);
        rightSon.balance = calcBalance(rightSon);
    }

    private void right_rotate(AVLNode parent) {
        //旋转涉及祖父，父亲，右儿子
        AVLNode grandparent = parent.parent;
        AVLNode leftSon = parent.left;
        AVLNode rightGrandson = leftSon.right;
        //左子变父
        leftSon.parent = grandparent;
        if (grandparent != null){
            if (parent == grandparent.left){
                grandparent.left = leftSon;
            }else if (parent == grandparent.right){
                grandparent.right = leftSon;
            }
        }
        leftSon.right = parent;
        parent.parent = leftSon;
        //右孙变左儿
        parent.left = rightGrandson;
        if (rightGrandson != null){
            rightGrandson.parent = parent;
        }
        parent.depth = calcDepth(parent);
        parent.balance = calcBalance(parent);
        leftSon.depth = calcDepth(leftSon);
        leftSon.balance = calcBalance(leftSon);
    }

    private int calcDepth(AVLNode parent) {
        int depth = 0;
        if (parent.left != null){
            depth = parent.left.depth;
        }
        if (parent.right != null && depth< parent.right.depth){
            depth = parent.right.depth;
        }
        depth++;
        return  depth;
    }

    private int calcBalance(AVLNode root) {
        int leftDepth = 0;
        int rightDepth = 0;
        //左子树深度
        if (parent.left != null){
            leftDepth = parent.left.depth;
        }
        //左子树深度
        if (parent.right != null){
            rightDepth = parent.right.depth;
        }
        return leftDepth - rightDepth;
    }
}
