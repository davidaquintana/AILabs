
/*
Ai Lab3 - Part I
David Quintana, Ben Cerrato
CSC207, Spring 2023 Eliott, PhD
Acknowledgements:
Fernanda Elliot, Class mentors, AI Lab 3

chrome-extension://efaidnbmnnnibpcajpcglclefindmkaj/https://barabasi.com/f/624.pdf
Network 1, Example 1 from (BARABASI 2014) Network 2, Example 2 from (BARABASI 2014)
provided by Fernanda Elliot || Accessed May 2023 || Page 7 Section 3.3

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


Extra/Additional Info:

We included a class called threeByThree at the end of our project.
This class represents the thought process of our work on this project.
As you can see this is a hard coded array of elements, but we took a different
approach to creating a lattice for this class. We thought it would be interesting
to see how far we could get using arrays rather than for loops, and after a while
got stuck on figuring out how to read and write to nested loops.
Ultimately though, this failure was what fuled us to successfully finish part
one as we were able to use similar thought process to complete this project
without arrays.
*/
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class AiLab3 {
    public static void main(String[] args)
            throws FileNotFoundException {
        PrintStream o1 = new PrintStream(new File("rndmNetwork.txt")); // writing to a rndmNetwork.txt
        PrintStream o2 = new PrintStream(new File("lattice.txt")); // writing to a lattice.txt
        PrintStream o3 = new PrintStream(new File("examples.txt")); // writing to a examples.txt
        PrintStream console = System.out;// writing to a file
        System.setOut(console);// writing to console
        Scanner in = new Scanner(System.in); // Creation of Scanner in
        System.out.println(
                "Info: here are the filenames for your choices...\n1) rndmNetwork.txt\n2) lattice.txt\n3) examples.txt\n"); // some
                                                                                                                            // help
                                                                                                                            // reguarding
                                                                                                                            // filenames
        System.out.println(
                "Would you like to see the random connections between nodes(1) the connections of a lattice of your creation(2) or the hard coded examples(3)?"); // asks
        // for
        // user
        // input
        int trial = in.nextInt(); // takes user input and stores into int trial

        if (trial == 1) { // check which trial the user wants to run
            int nodes = 0;
            while (nodes <= 11 || nodes >= 1001) {
                System.out.println("What is your number of nodes? (12-1000)"); // asks for how many nodes the user wants
                                                                               // to include
                nodes = in.nextInt(); // scans user input and stores data in int nodes
            }
            System.out.println("What is the probability of picking a node pair [1/x, 1]?"); // asks for probability
            int probability = in.nextInt(); // scans user input and stores data in int probability

            System.setOut(o1);// writing to text file
            testOne(nodes, probability); // call to class testOne
        } else if (trial == 2) {// check which trial the user wants to run

            int nodes = 0;

            while (nodes <= 8 || nodes >= 1000) {
                System.out.println("What is your number of nodes? (9-1000)"); // asks for how many nodes the user wants
                                                                              // to include
                nodes = in.nextInt(); // scans user input and stores data in int nodes
            }

            System.setOut(o2);// writing to text file
            testTwo(nodes); // call to class testTwo

        } else if (trial == 3) {// check which trial the user wants to run

            System.setOut(o3);// writing to text file
            testThree(); // call to class testThree

        } else {// check which trial the user wants to run, if its not 1 or 2, the program tells
                // the user something went wrong
            System.out.println("That is not a correct input, run the program again");
            // threeByThree(); // a program we messed around and created to help us better
            // understand the project
        }
        in.close(); // close the scanner in
    }

    static void testOne(int nodes, int probability) {// Test One Class, takes in int nodes and int probability
        ArrayList<Integer> connections = new ArrayList<Integer>(); // create an array list to store already connnected
                                                                   // nodes
        for (int i = 1; i <= nodes; i++) { // nested for loop because there is a grid and we want to access x,y of the
                                           // grid

            for (int x = 1; x <= nodes; x++) {
                // connections for 1 and 2
                int randConnect = (int) Math.floor(Math.random() * (probability) + 1);// random number 1-probability
                if (randConnect == 1 && i < x) {
                    System.out.println(i + " " + x); // create the connections
                    connections.add(i);
                    connections.add(x);
                }
            }
        }

        for (int y = 1; y <= nodes; y++) {
            int checker = 0;// checker variable
            for (int z = 0; z < connections.size(); z++) {// forloop of ArrayList connections
                if (connections.get(z) == y) {// checks for duplicates
                    checker += 1;
                }
            }
            if (checker == 0) {
                System.out.println(y + " -1");// if no connection, -1
            }
        }
    }

    static void testTwo(int nodes) {// Test Two Class, takes in int nodes
        int x = 0;
        int y = 0;
        double dnodes = nodes;
        double sqnodes = Math.sqrt(dnodes);// for sqrt nodes
        int floor = (int) Math.floor(sqnodes);// for sqrt nodoes

        if (floor * (floor + 1) == nodes) {// creates the lattice grid
            x = floor;// rows
            y = (floor + 1);// cols
            System.out.println("Your grid will look like: x = " + x + " and y = " + y);// states the lattice grid
        } else if ((floor * floor) == nodes) {// creates another lattice grid option
            x = floor;
            y = floor;
            System.out.println("Your grid will look like: x = " + x + " and y = " + y);// states lattice grid
        } else {
            if ((floor + 1) * (floor + 1) > 1000) { // make sure the number of nodes does not exceed 1000
                x = 31;
                y = 31; // a 31x31 grid is the largest square grid that we can build without exceeding
                        // 1000 nodes
            } else {
                if (Math.abs((floor * floor) - nodes) > (Math.abs(((floor + 1) * (floor + 1)) - nodes))) {
                    x = floor + 1;
                    y = floor + 1;
                } else {// creates proper grid if the given nodes are not easily usable to create one
                    y = floor;
                    x = floor;
                }
                System.out.println("Your number is awkward, we will use " + x * y + " instead");// fixes user input if
                                                                                                // lattice grid is not
                                                                                                // acceptable
                System.out.println("Your grid will look like: x = " + x + " and y = " + y);// states lattice grid
            }
        }

        nodes = x * y;// Make sure the number of total nodes reflects the actual number in the new
                      // lattice

        for (int value = 1; value <= nodes; value++) {
            if (value <= x) {
                System.out.println(value + " " + (value + ((y - 1) * x))); // one up if on top most row
            } else {
                System.out.println(value + " " + (value - x)); // one up
            }

            if (value % x == 0) {
                System.out.println(value + " " + (value - (x - 1))); // one right if on right most edge
            } else {
                System.out.println(value + " " + (value + 1)); // one to the right
            }
        }
    }

    static void testThree() { // test 3 no params
        // Introducatary info
        System.out.println(
                "Welcome to Examples one and Two. This simply prints in terminal and a TXT file the connections of nodes in the network.");

        // arrays to represent each node
        // each array and cell represents connections and the positioning of the area
        // represents the node
        int[][] exampleOne = { { 4, 7, 10 }, { 7, 9, 11 }, { -1 }, { 5 }, {}, { -1 }, {}, { 10 },
                { 12, 10 }, {}, {}, {} };
        int[][] exampleTwo = { { 3, 11 }, { 11 }, { 8 }, { -1 }, { 9, 10 }, { 11, 12 }, { -1 }, { 9 }, { 10 }, {}, {},
                {} };

        // Returns example 1 connections
        System.out.println("Example One: ");
        for (int i = 0; i < exampleOne.length; i++) {
            for (int j = 0; j < exampleOne[i].length; j++) {
                System.out.println(i + 1 + " " + exampleOne[i][j]);
            }
        }

        // Returns example 2 connections
        System.out.println("Example Two: ");
        for (int i = 0; i < exampleTwo.length; i++) {
            for (int j = 0; j < exampleTwo[i].length; j++) {
                System.out.println(i + 1 + " " + exampleTwo[i][j]);
            }
        }
    }

    // This is threeByThree, a coded visualization of our initial thought process
    // We thought it would be interesting to include this because it can be helpful
    // if we
    // were to use this for a coding interview to explain how we went about solving
    // the
    // problem. And it can reveal other solutions that we may have missed.

    static void threeByThree() {
        // 2D4N Array Creator
        int[][] TwoFour = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // Hard Coded Array
        for (int i = 0; i < 3; i++) { // go through each element of the array
            int up = -1; // define up
            int left = -1; // define left
            // int down = 1; <- this is not needed because we only need half of the
            // connections
            // int right = 1; <- this is not needed because we only need half of the
            // connections
            for (int x = 0; x < 3; x++) {// go through each element of the interior arrays
                if (i == 0) {
                    System.out.println(TwoFour[i][x] + " " + TwoFour[2][x]);
                } else {
                    System.out.println(TwoFour[i][x] + " " + TwoFour[i + up][x]);
                }
                // if (i == 2) { <- this is not needed because we only need half of the
                // connections
                // System.out.println(TwoFour[i][x] + " " + TwoFour[0][x]);
                // } else {
                // System.out.println(TwoFour[i][x] + " " + TwoFour[i + down][x]);
                // }
                if (x == 0) {
                    System.out.println(TwoFour[i][x] + " " + TwoFour[i][2]);
                } else {
                    System.out.println(TwoFour[i][x] + " " + TwoFour[i][x + left]);
                }
                // if (x == 2) { <- this is not needed because we only need half of the
                // connections
                // System.out.println(TwoFour[i][x] + " " + TwoFour[i][0]);
                // } else {
                // System.out.println(TwoFour[i][x] + " " + TwoFour[i][x + right]);
                // }
            }
        }

    }
}
