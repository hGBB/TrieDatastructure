/**
 *  The Node program creates the base component of the Trie data tree
 */
public class Node {

    private char ch;
    private Node[] children;
    private Node parent;
    private Integer points;


    /**
     *   constructor with parameter
     *   creates a new Node and links it
     *   automatically to the parent Node
     * @param ch        edge to Child
     * @param parent    Parent Node at which new Node will be created
     */
    public Node(char ch, Node parent) {
        this.ch = ch;
        this.children = new Node[26];
        this.parent = parent;
        parent.setChild(ch, this);
    }

    /**
     * constructor with no parameter
     * creates the root for the Trie
     */
    public Node() {
        this.ch = '+';
        this.parent = null;
        this.children = new Node[26];
    }

    /**
     * GetterMethod for char ch
     * @return      the character from specific Node
     */
    public char getCh() {
        return this.ch;
    }

    /**
     * GetterMethod for a specific Node from the NodeArray of it's parent Node
     * @param ch    used to find Position in NodeArray
     * @return the Node located in specific position in NodeArray of parent
     */
    public Node getChild(char ch) {
        return children[ch - 'a'];
    }

    /**
     * SetterMethod for a child Node
     * Mainly used in Constructor for a new Node
     * @param ch    the character used as edge to the new Node
     * @param child the new Node set at certain position in NodeArray
     */
    public void setChild(char ch, Node child) {
        this.children[ch - 'a'] = child;
    }

    /**
     * Sets Node at certain position in NodeArray to null
     * @param ch    char used to identify position in NodeArray
     */
    public void removeChild(char ch) {
        setChild(ch, null);
    }

    /**
     * Checks if Node has any children
     * @return true if there's a entry in NodeArray which isn't null
     */
    public boolean hasChildren() {
        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * GetterMethod for Node parent
     * @return  the parent Node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Checks if Node has stored a value
     * @return  the value as Integer
     */
    public boolean hasPoints() {
        if (points != null) {
            return true;
        }
        return false;
    }

    /**
     * GetterMethod for value stored
     * @return  the points as Integer
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * SetterMethod for value stored
     * @param points    the value saved in the Node
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Sets the value stored in the Node to null
     */
    public void removePoints() {
        this.points = null;
    }

    /**
     * Method to create a visual output of the saved date on the console
     * @return  a String from the root and all stored edges and values
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(ch));

        if (hasPoints()) {
            sb.append("[" + getPoints() + "]");
        }

        if (hasChildren()) {
            sb.append("(");

            for (Node child : children) {
                if (child != null) {
                    sb.append(child);
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

}
