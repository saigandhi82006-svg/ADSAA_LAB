import java.util.Scanner;

public class AVLTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConstructAVLTree avl = new ConstructAVLTree();

        while (true) {
            System.out.println("1. Insert an element");
            System.out.println("2. Search for an element");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Preorder Traversal");
            System.out.println("5. Postorder Traversal");
            System.out.println("6. Delete an element");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter element:");
                    int item = sc.nextInt();
                    avl.insert(item);
                    break;

                case 2:
                    System.out.println("Enter the key element to search");
                    int key = sc.nextInt();
                    avl.search(key);
                    break;

                case 3:
                    System.out.println("Inorder Traversal:");
                    avl.inorderTraversal();
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Preorder Traversal:");
                    avl.preorderTraversal();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Postorder Traversal:");
                    avl.postorderTraversal();
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Enter element to delete:");
                    int del = sc.nextInt();
                    avl.delete(del);
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid entry");
            }
        }
    }
}

class Node {
    int data;
    int h;
    Node leftChild;
    Node rightChild;

    public Node(int value) {
        data = value;
        h = 0;
        leftChild = null;
        rightChild = null;
    }
}

class ConstructAVLTree {
    private Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private int getHeight(Node node) {
        return (node == null) ? -1 : node.h;
    }

    private int max(int l, int r) {
        return (l > r) ? l : r;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 :
                getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // INSERTION
    private Node insertRec(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.data)
            node.leftChild = insertRec(node.leftChild, value);
        else if (value > node.data)
            node.rightChild = insertRec(node.rightChild, value);
        else
            return node;

        node.h = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild));

        int balance = getBalance(node);

        // Rotations
        if (balance > 1 && value < node.leftChild.data)
            return rotateWithLeftChild(node);

        if (balance < -1 && value > node.rightChild.data)
            return rotateWithRightChild(node);

        if (balance > 1 && value > node.leftChild.data) {
            node.leftChild = rotateWithRightChild(node.leftChild);
            return rotateWithLeftChild(node);
        }

        if (balance < -1 && value < node.rightChild.data) {
            node.rightChild = rotateWithLeftChild(node.rightChild);
            return rotateWithRightChild(node);
        }

        return node;
    }

    // DELETION
    private Node deleteRec(Node node, int value) {
        if (node == null)
            return node;

        if (value < node.data)
            node.leftChild = deleteRec(node.leftChild, value);
        else if (value > node.data)
            node.rightChild = deleteRec(node.rightChild, value);
        else {
            // Node with one or no child
            if ((node.leftChild == null) || (node.rightChild == null)) {
                Node temp = (node.leftChild != null) ? node.leftChild : node.rightChild;

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // Node with two children
                Node temp = minValueNode(node.rightChild);
                node.data = temp.data;
                node.rightChild = deleteRec(node.rightChild, temp.data);
            }
        }

        if (node == null)
            return node;

        node.h = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild));

        int balance = getBalance(node);

        // Rebalancing
        if (balance > 1 && getBalance(node.leftChild) >= 0)
            return rotateWithLeftChild(node);

        if (balance > 1 && getBalance(node.leftChild) < 0) {
            node.leftChild = rotateWithRightChild(node.leftChild);
            return rotateWithLeftChild(node);
        }

        if (balance < -1 && getBalance(node.rightChild) <= 0)
            return rotateWithRightChild(node);

        if (balance < -1 && getBalance(node.rightChild) > 0) {
            node.rightChild = rotateWithLeftChild(node.rightChild);
            return rotateWithRightChild(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.leftChild != null)
            current = current.leftChild;
        return current;
    }

    // ROTATIONS
    private Node rotateWithLeftChild(Node k2) {
        Node k1 = k2.leftChild;
        k2.leftChild = k1.rightChild;
        k1.rightChild = k2;

        k2.h = 1 + max(getHeight(k2.leftChild), getHeight(k2.rightChild));
        k1.h = 1 + max(getHeight(k1.leftChild), getHeight(k1.rightChild));

        return k1;
    }

    private Node rotateWithRightChild(Node k1) {
        Node k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        k2.leftChild = k1;

        k1.h = 1 + max(getHeight(k1.leftChild), getHeight(k1.rightChild));
        k2.h = 1 + max(getHeight(k2.leftChild), getHeight(k2.rightChild));

        return k2;
    }

    // TRAVERSALS
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.leftChild);
            System.out.print(node.data + " ");
            inorderTraversal(node.rightChild);
        }
    }

    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.leftChild);
            preorderTraversal(node.rightChild);
        }
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.leftChild);
            postorderTraversal(node.rightChild);
            System.out.print(node.data + " ");
        }
    }

    public void search(int key) {
        Node n = search(root, key);
        if (n == null)
            System.out.println("Key is not found in the Tree");
        else
            System.out.println("Key is found");
    }

    private Node search(Node node, int key) {
        if (node == null || node.data == key)
            return node;
        else if (key < node.data)
            return search(node.leftChild, key);
        else
            return search(node.rightChild, key);
    }
}
