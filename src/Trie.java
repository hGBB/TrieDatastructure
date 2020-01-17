/**
 *  The Trie program implements methods to create and manage a data structure
 */
public class Trie extends Node {

    private Node root = new Node();

    /**
     * Empty constructor to initialise a Trie
     */
    public Trie() {
    }

    /**
     * checks if path is already created
     * adds a new path to the Trie stores Integer at the last entry
     * @param key       String path at which value is stored
     * @param points    Integer stored in Trie
     * @return      true for successfully created path
     */
    public  boolean add(String key, Integer points) {
        Node currentNode;
        currentNode = root;
        try {
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (currentNode.getChild(ch) == null) {
                    Node child = new Node(ch, currentNode);
                    currentNode = currentNode.getChild(ch);
                } else {
                    currentNode = currentNode.getChild(ch);
                }
            }
            if (currentNode.hasPoints()) {
                error(key + " exists already");
                return false;
            } else {
                currentNode.setPoints(points);
                return true;
            }
        } catch (NullPointerException npe) {
            error("Could not add " + key);
            return false;
        }
    }

    /**
     * removes the value stored at a certain Position
     * @param key   String path to value
     * @return      true if Integer stored at key is successfully deleted
     */
    public boolean remove(String key) {
        Node currentNode = find(key);
        if (currentNode == null) {
            error(key + " not found");
            return false;
        }
        if (!currentNode.hasPoints()) {
            error("No points stored at " + key);
            return false;
    } else {
            currentNode.removePoints();
            cleanup(currentNode);
            return true;
        }
    }

    /**
     * Checks if Node is redundant aka has no further children and is safe to
     * delete. Recursive approach cleans up chain of entries until the root is
     * reached, another value is located at one of the Nodes or a Node contains
     * further children
     * @param currentNode   Node from which the process starts
     */
    public void cleanup(Node currentNode) {
        while (true) {
            if (currentNode.hasChildren() || currentNode.hasPoints()
                    || currentNode == root) {
                return;
            } else {
                char ch = currentNode.getCh();
                currentNode = currentNode.getParent();
                currentNode.removeChild(ch);
                cleanup(currentNode);
            }
        }
    }

    /**
     * checks if exists and returns Node at given path of a given Key
     * @param key   String leading to certain Node
     * @return      Node at Position
     */
    public Node find(String key) {
        Node currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (currentNode.getChild(ch) == null) {
                error(" no entry " + key);
                return null;
            } else {
                currentNode = currentNode.getChild(ch);
            }
        }
        if (!currentNode.hasPoints()) {
            error(key + " has no points");
            return null;
        }
        return currentNode;
    }

    /**
     * changes Integer stored at certain location
     * @param key  String at which location the value is located
     * @param points    the new value as Integer
     */
    public void changePoints(String key, Integer points) {
        try {
            find(key).setPoints(points);
        } catch (NullPointerException n) {
            error(key + " has no points ");
        }
    }

    /**
     * Returns the value stored at a specific key in the Trie
     * @param key  String at which position the value is located
     * @return      the Integer stored at the position
     */
    public Integer getPoints(String key) {
        Node currentNode = find(key);
        if (currentNode.hasPoints()) {
            return currentNode.getPoints();
        }
        return null;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    /**
     * Method to standardize error messages
     * @param message String given to create a unique error message
     */
    public static void error(String message) {
        System.out.println("Error! " + message);
    }

    /**
     * Method checks UserInput for certain given criteria
     * @param parts UserInput a String giving the basic commands to the shell
     * @return      true for valid input
     */
    public static boolean checkInput(String[] parts) {
        if (parts.length > 1) {
        if (parts[0].equals("") || (parts[1].equals("")
                && parts.length > 1) || parts[1].length() > 255) {
            error("key must not be empty or over 255 characters long");
            return false;
        }
            char[] compareTo = parts[1].toUpperCase().toCharArray();
            char[] compareThis = parts[1].toCharArray();
            for (int i = 0; i < parts[1].length(); i++) {
                if (compareThis[i] == compareTo[i]) {
                    error("Key may only contain lowercase letters");
                    return false;
                }
            }
            if (parts.length == 3) {
                try {
                    //noinspection ResultOfMethodCallIgnored
                    Integer.parseInt(parts[2]);
                    return true;
                } catch (NumberFormatException e) {
                    error("Enter a valid number! ");
                    return false;
                }
            }
        }
        return true;
    }
}