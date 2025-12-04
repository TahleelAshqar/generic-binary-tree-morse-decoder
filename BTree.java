public class BTree<E> {

    private Node<E> root;
    private int size;

    public BTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean addByPath(E data, String path) {
        // Empty tree + empty path → add root
        if (path == null) {
            return false;
        }

        if (path.length() == 0) {
            if (root != null) {
                return false; // root already exists
            }
            root = new Node<>(data, null, null);
            size = 1;
            return true;
        }

        if (root == null) {
            return false; // non-empty path but no root
        }

        Node<E> current = root;
        int lastIndex = path.length() - 1;

        for (int i = 0; i <= lastIndex; i++) {
            char c = path.charAt(i);

            boolean isLast = (i == lastIndex);

            if (c == 'L') {
                if (isLast) {
                    if (current.getLeftSon() != null) {
                        return false; // target not null → illegal
                    }
                    current.setLeftSon(new Node<>(data, null, null));
                    size++;
                    return true;
                } else {
                    if (current.getLeftSon() == null) {
                        return false; // illegal path (too deep)
                    }
                    current = current.getLeftSon();
                }
            } else if (c == 'R') {
                if (isLast) {
                    if (current.getRightSon() != null) {
                        return false; // target not null → illegal
                    }
                    current.setRightSon(new Node<>(data, null, null));
                    size++;
                    return true;
                } else {
                    if (current.getRightSon() == null) {
                        return false; // illegal path (too deep)
                    }
                    current = current.getRightSon();
                }
            } else {
                // invalid character
                return false;
            }
        }

        return false;
    }

    public String pre() {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (sb.length() > 0) {
            sb.append(",");
        }
        sb.append(node.getData());

        preOrder(node.getLeftSon(), sb);
        preOrder(node.getRightSon(), sb);
    }

    public int getSize() {
        return size;
    }

    public E findByPath(String path) {
        if (path == null) {
            return null;
        }

        if (root == null) {
            return null;
        }

        if (path.length() == 0) {
            return root.getData();
        }

        Node<E> current = root;

        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == 'L') {
                current = current.getLeftSon();
            } else if (c == 'R') {
                current = current.getRightSon();
            } else {
                return null; // invalid character
            }

            if (current == null) {
                return null; // illegal path → leads to null
            }
        }

        return current.getData();
    }
}
