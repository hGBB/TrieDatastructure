

import java.util.Scanner;

/**
 The Shell program implements an application that expects User Input and
 * creates a data structure. This highly branched data tree contains a certain
 * path "Key", a String split up into characters, where a value as Integer is
 * stored
 */
public class Shell {
    /**
     * Main method for Trie. Creates a root Node and expects UserInput
     * Depending on the input executes Trie Methods creating new keys, stores,
     * changes or deletes existing entries.
     * Active until the user ends with 'quit' or you find a npe not detected by
     * the checkInput method
     * @param args  main Method
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Trie startTrie = new Trie();
        while (true) {
            System.out.println("trie> ");
            String input = reader.nextLine();
            String[] ip = input.split(" ");

            if (startTrie.checkInput(ip)) {
                char[] firstChar = ip[0].toLowerCase().toCharArray();
                switch (firstChar[0]) {
                    case 'n':   startTrie = new Trie();
                                break;
                    case 't':   System.out.println(startTrie);
                                break;
                    case 'h':   System.out.println("Enter \"new\" to create "
                                + "a new trie data structure");
                                System.out.println("Enter \"add String "
                                + "Integer\" to add an entry");
                                System.out.println("Enter \"change String "
                                + "Integer\" to edit an existing entry");
                                System.out.println("Enter \"delete  String "
                                + "Integer\" to delete an existing entry");
                                System.out.println("Enter \"points String "
                                + "Integer\" to view the points of an entry");
                                System.out.println("Enter \"trie\" to view "
                                + "the data structure");
                                break;
                    case 'a':   if (ip.length != 3) {
                                Trie.error("invalid Input");
                                break;
                                }
                                startTrie.add(ip[1], Integer.parseInt(ip[2]));
                                break;
                    case 'p':   if (ip.length != 2) {
                                Trie.error("invalid Input");
                                break;
                                }
                                if (startTrie.find(ip[1]) == null) {
                                Trie.error("no value stored at " + ip[1]);
                                break;
                                }
                                System.out.println(startTrie.getPoints(ip[1]));
                                break;
                    case 'd':   if (ip.length != 2) {
                                Trie.error("invalid Input");
                                break;
                                }
                                startTrie.remove(ip[1]);
                                break;
                    case 'c':   if (ip.length != 3) {
                                Trie.error("invalid Input");
                                break;
                                }
                                startTrie.changePoints(ip[1],
                                        Integer.parseInt(ip[2]));
                                break;
                    case 'q':   return;
                    default:    Trie.error("'Help' for a list of commands");
                                break;
                }
            }
        }
    }
}