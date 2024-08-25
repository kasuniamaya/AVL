package avl;

public class AVLinsert {

	static class AVLNode {
        int value, height;
        AVLNode left, right;

        AVLNode(int value) {
            this.value = value;
            this.height = 1;
            this.left = this.right = null;
        }
    }

    public static class AVLTree {
        private AVLNode root;

        private int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        private int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        public void insert(int value) {
            root = insertRec(root, value);
        }

        private AVLNode insertRec(AVLNode node, int value) {
            if (node == null) {
                return new AVLNode(value);
            }

            if (value < node.value) {
                node.left = insertRec(node.left, value);
            } else if (value > node.value) {
                node.right = insertRec(node.right, value);
            } else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            if (balance > 1 && value < node.left.value) {
                return rightRotate(node);
            }

            if (balance < -1 && value > node.right.value) {
                return leftRotate(node);
            }

            if (balance > 1 && value > node.left.value) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            if (balance < -1 && value < node.right.value) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        public void inOrder() {
            inOrderRec(root);
            System.out.println();
        }

        private void inOrderRec(AVLNode root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.print(root.value + " ");
                inOrderRec(root.right);
            }
        }

        public void printBalanceFactors() {
            printBalanceFactorsRec(root);
            System.out.println();
        }

        private void printBalanceFactorsRec(AVLNode node) {
            if (node != null) {
                printBalanceFactorsRec(node.left);
                System.out.print("Node: " + node.value + " Balance Factor: " + getBalance(node) + " ");
                printBalanceFactorsRec(node.right);
            }
        }
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AVLTree avl = new AVLTree();

        int[] values = {21, 26, 30, 9, 4, 14, 28, 18, 15, 10, 2, 3, 7};
        for (int value : values) {
            avl.insert(value);
            System.out.println("After inserting " + value + ":");
            avl.inOrder();
            avl.printBalanceFactors();
        }

	}
}
