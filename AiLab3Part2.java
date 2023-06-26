
/*
Ai Lab3 - Part II
David Quintana, Ben Cerrato
CSC207, Spring 2023 Eliott, PhD
Acknowledgements:
Fernanda Elliot, Class mentors, AI Lab 3

Description:

Part1:

AiLab3 Part 1 consists of 4 different networks: 2 Given Examples, a user made Random Network
and a user made Lattice Network. As allowed, the two examples given in class are Hard Coded
into 2D Arrays where the position of the Array is the node and the numbers in the Array are
the connections. The Random Network is made using an ArrayList. Connections are randomly assigned
based on the user's probability input. After connections are assigned, it is added to the ArrayList.
The Lattice Network is also user driven. The user submits the number of nodes and the program decides
what the rows and columns will be. Based on the Rows and Columns a Lattice Network is created. All of these
ways to make a network print a .txt file based on the directions.

Part 2:

AiLab3 Part 2 consists of scanning in files given by the user. The program scans in the file and the
connections are added into an ArrayList. Based on the number of connections (amount of 1s or 2s .... Ns)
We will determine the score of the game. The score follows the rules presented where k =
number of neighbors, r = 1/k, R = r1 + r2 ..., T is given by the user, the defector was given by the instructor and
m is given by the instructor. The program will print out a .txt file for the user stating if the Network
broke or is stable based on the elimination rule R < T.

IMPORTANT!!!
May have to change file directory for the tests to make sure the program runs properly!!!
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.io.*;

public class AiLab3Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        PrintStream o = new PrintStream(new File("AiPartB.txt")); // writing to a AiPartB.txt
        PrintStream console = System.out;// writing to a file
        System.setOut(console);// writing to console
        Scanner in = new Scanner(System.in);// creates scanner

        System.out.println("Which test do you want to run? (1-7)");// interface

        int testNum = in.nextInt();// records choice
        if (testNum == 1) {
            System.setOut(o);
            test(12, 5, 5, 1, "ExampleOne.txt"); // Test 1 --> please enter your location
        }
        if (testNum == 2) {
            System.setOut(o);
            test(12, 5, 10, 1, "ExampleTwo.txt"); // Test 2 --> please enter your location
        }
        if (testNum == 3) {
            System.setOut(o);
            test(1000, 500, 3, 1, "rndmNetwork3.txt"); // Test 3 --> please enter your location
        }
        if (testNum == 4) {
            System.setOut(o);
            test(1000, 500, 10, 1, "rndmNetwork4.txt"); // Test 4 --> please enter your location
        }
        if (testNum == 5) {
            System.setOut(o);
            test(999, 500, 3, 0.5, "lattice5.txt"); // Test 5 --> please enter your location
        }
        if (testNum == 6) {
            System.setOut(o);
            test(1000, 500, 5, 0.75, "lattice6.txt"); // Test 6--> please enter your location
        }
        if (testNum == 7) {
            System.out.println("Whats your H value?: ");
            int H = in.nextInt(); // nodes test 7
            System.out.println("Whats your T value?: ");
            int T = in.nextInt(); // nodes test 7
            System.out.println("Whats your M value?: ");
            int M = in.nextInt(); // nodes test 7
            System.setOut(o);
            test(999, H, M, T, "option7.txt");
        } else {
            System.out.println("Invalid Choice, Rerun code."); // make sure user inputs correct code
        }
        in.close();
    }// main class

    static void test(int numNodes, int h, int m, double t, String filename)
            throws FileNotFoundException {
        ArrayList<Integer> nodes = new ArrayList<Integer>(); // ArrayList of all the nodes
        ArrayList<Integer> connection = new ArrayList<Integer>(); // ArrayList of the connections
        ArrayList<Integer> All = new ArrayList<Integer>(); // ArrayList of the connections

        String s = ""; // creates String s

        File file = new File(filename);// file
        // path
        Scanner sc = new Scanner(file);// scanner

        while (sc.hasNextLine()) {
            s += sc.nextLine() + " ";// reads in the file into String s
        }
        String[] s2 = s.split("\\s");// splits the connections and nodes into s2

        int cnt = 0;// counter
        while (cnt != s2.length) {
            All.add(Integer.parseInt(s2[cnt]));// ArrayList of all connections and nodes
            if (cnt % 2 == 0) {
                nodes.add(Integer.parseInt(s2[cnt]));// ArrayList of only nodes
            } else {
                connection.add(Integer.parseInt(s2[cnt]));// ArrayList of only connections
            }
            cnt++;// counter

        }

        // makes a no connection (-1) into 0
        for (int i = 0; i < connection.size(); i++) {
            if (connection.get(i) == -1) {
                connection.set(i, 0);
            }
        }

        cnt = 0; // resets counter

        // for (int i = 0; i < All.size(); i++) {
        // System.out.println(All.get(i));
        // }

        for (int i = 0; i < connection.size(); i++) { // checks for defector and delets from both arrays
            if (nodes.get(i) == h || connection.get(i) == h) {
                connection.set(i, 0); // delete from array connection
                nodes.set(i, 0); // delete from array nodes
            }
        }

        int counter = 0;// counter
        int continue1 = 0;// helper
        int continue2 = -1;// helper2

        // while loop until the same output happens twice
        while (continue1 != continue2) {
            continue2 = continue1;// helper2 = helper1
            continue1 = 0;// helper1 = 0

            for (int i = 1; i <= numNodes; i++) { // loops num of nodes
                for (int z = 0; z < nodes.size(); z++) { // loops size of nodes
                    if (i == nodes.get(z) && connection.get(z) > 0) {
                        counter = counter + 1;// if there is a connection add one to counter
                    } else if (i == connection.get(z) && nodes.get(z) > 0) {
                        counter = counter + 1; // adds one to counter
                    } else {
                    }

                }
                System.out.println(i + " has " + counter + " connections");// prints connection
                if (counter < t) { // if the nodes do not have enough connections
                    System.out.println("Your value for " + i + " is less than the t value: " + t
                            + "...\nthis node and all connections to this node will be eliminated");
                    for (int o = 1; o <= numNodes; o++) {// loops into num of nodes
                        for (int q = 0; q < nodes.size(); q++) {// loops into size of nodes
                            if (nodes.get(q) == i || connection.get(q) == i) {
                                nodes.set(q, 0);// if equal to i, disconnevt
                                connection.set(q, 0);// disconnect
                            }
                        }
                    }
                }

                for (int p = 0; p < nodes.size(); p++) {// loops size of nodes

                    continue1 = continue1 + (connection.get(p) + nodes.get(p));
                }
                counter = 0;
            }
            // System.out.println(continue1 + "" + continue2); //this is a checker to see if
            // the counter will still run the game
        }
        System.out.println("^ The test has reached its ending points, check here are your results above ^"); // end of
                                                                                                             // test
                                                                                                             // notification
        sc.close(); // scanner close
    }

}
