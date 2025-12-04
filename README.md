# generic-binary-tree-morse-decoder
Generic binary tree implementation in Java with a Morse code decoder built on top of it.

# Generic Binary Tree & Morse Decoder (Java)

This project implements a **generic binary tree** in Java and uses it to build
a **Morse code decoder**. The tree is completely generic (`BTree<E>` and `Node<E>`),
and the `MorseDecoder` class uses `BTree<String>` to map Morse sequences to letters,
digits, and symbols. :contentReference[oaicite:1]{index=1}

## 🧱 Data Structures
### `Node<E>`
A single node in a generic binary tree.
- Fields:
  - `E data` – the value stored in this node
  - `Node<E> leftSon` – reference to the left child
  - `Node<E> rightSon` – reference to the right child
- Methods:
  - Constructor `Node(E data, Node<E> left, Node<E> right)`
  - Getters and setters for all fields

### `BTree<E>`
Represents a generic binary tree.
- Fields:
  - `Node<E> root` – reference to the root node
  - `int size` – number of nodes in the tree
- Constructor:
  - Empty constructor that initializes an empty tree (`root = null`, `size = 0`)
- Methods:
  - `boolean addByPath(E data, String path)`  
    Adds a new node at the position specified by the path string `L` or `R`.
    - `""` (empty string) adds the root (if tree is empty)
    - `L` / `R` characters traverse left/right
    - Fails (`false`) if path is illegal or target is not `null`
  - `String pre()`  
    Returns a comma-separated string of all node values in **pre-order**:  
    `root, left subtree, right subtree`
  - `int getSize()`  
    Returns the number of nodes in the tree.
  - `E findByPath(String path)`  
    Returns the data found at the node reached by the `L`/`R` path, or `null` if
    The path is illegal or leads to `null`.

## 🔡 MorseDecoder
Builds a Morse code decoding tree on top of `BTree<String>`.
- Field:
  - `BTree<String> morseTree` – binary tree representing the Morse alphabet
- Constructor:
  - Fills the tree according to standard Morse code rules:
    - `.` (dot) = go **left**
    - `-` (dash) = go **right**
    - The root holds an empty string (`""`)
    - Nodes hold letters (A–Z), digits (0–9), etc.
- Methods:
  - `public String toString()`  
    Returns the pre-order traversal of the Morse tree as a comma-separated string,
    starting with the root (`""`) followed by `E, I, S, H, ...`
  - `public String decode(String morseStr)`  
    Decodes a single Morse sequence (e.g. `"...", ".-", "-.-."`) into a letter
    or digit.  
    - Returns `""` if the string contains invalid characters or does not correspond
      to any node in the tree.
