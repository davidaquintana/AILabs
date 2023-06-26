/*
Ai Lab - Questions 1-5
David Quintana, Ben Cerrato
CSC207, Spring 2023 Eliott, PhD
Acknowledgements: 
In this lab, we explore AI and agents given certain obstacles. The movement percentages are given by the lab

Our first test we create a 2x3 grid, start at the potition 1 in the top left, and randomly move around the board until we reach position 6 in the bottom right

Our second test we create a 2x3 grid with two walls surrounding the initial starting square which randomly open and close 50/50

Our third test we created a 3x3 grid where the agent starts at 1 and has to get to 9. There are no walls to block movement.

Our fourth test we created a 3X10 grid where the agent starts at 30 and has to get to 1. There are no walls to block movement.

Our fifth test we create a 3x3 grid walls surrounding every square which randomly open and close with a %50 probability
*/

import java.util.*;
import java.lang.Math;

public class AiLab {
    public static void main(String[] args) {
        int testnum = -1;
        Scanner in = new Scanner(System.in);
        System.out.println(
                "Welcome to AI Lab 1! Enter '1' to be presented with Test1,\n '2' to be presented with Test2,\n '3' to be presented with Test3,\n '4' to be p;resented with Test4,\n '5' to be presented with Test5 and,\n '6' to be presented with all Test Cases.");

        testnum = in.nextInt();

        if (testnum == 1) {
            Test1(3, 1, 2, 1, 3);
            Test1(1, 3, 1, 3, 2);
            Test1(2, 2, 2, 2, 2);
        } else if (testnum == 2) {
            Test2(3, 1, 2, 1, 3);
            Test2(1, 3, 1, 3, 2);
            Test2(2, 2, 2, 2, 2);
        } else if (testnum == 3) {
            Test3(3, 1, 2, 1, 3);
            Test3(1, 3, 1, 3, 2);
            Test3(2, 2, 2, 2, 2);
        } else if (testnum == 4) {
            Test4(3, 1, 2, 1, 3);
            Test4(1, 3, 1, 3, 2);
            Test4(2, 2, 2, 2, 2);
        } else if (testnum == 5) {
            Test5(3, 1, 2, 1, 3);
            Test5(1, 3, 1, 3, 2);
            Test5(2, 2, 2, 2, 2);
        } else if (testnum == 6) {
            Test1(3, 1, 2, 1, 3);
            Test1(1, 3, 1, 3, 2);
            Test1(2, 2, 2, 2, 2);

            Test2(3, 1, 2, 1, 3);
            Test2(1, 3, 1, 3, 2);
            Test2(2, 2, 2, 2, 2);

            Test3(3, 1, 2, 1, 3);
            Test3(1, 3, 1, 3, 2);
            Test3(2, 2, 2, 2, 2);

            Test4(3, 1, 2, 1, 3);
            Test4(1, 3, 1, 3, 2);
            Test4(2, 2, 2, 2, 2);

            Test5(3, 1, 2, 1, 3);
            Test5(1, 3, 1, 3, 2);
            Test5(2, 2, 2, 2, 2);
        } else {
            System.out.println("That was an incorrect response");
        }

        in.close();// closes scanner
    }

    static void Test1(int up, int down, int left, int right, int still) {
        System.out.println("\nTest 1:");
        int max = 10; // for defining random number
        int min = 1; // for defining random number
        int counter = 1000; // number of trials
        int moves = 0; // counter for number of moves
        int finish = 6; // the final end square number
        int total = 0; // a place holder for calculating average move
        int best = Integer.MAX_VALUE; // a place holder for what is best test case
        while (counter != 0) {
            int position = 1;
            while (position != finish) {
                int rndm = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                if (rndm >= 0 && rndm <= up) {
                    if ((position - 3) >= 0) {
                        position = position - 3; // go up one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > up && rndm <= (up + down)) {
                    if ((position + 3) <= 7) {
                        position = position + 3; // go down one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down) && rndm <= (up + down + left)) {
                    if (position != 1 && position != 4) {
                        position = position - 1; // go left one space
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down + left) && rndm <= (up + down + left + right)) {
                    if (position != 3) {
                        position = position + 1; // go right one space
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > (up + down + left + right) && rndm <= (up + down + left + right + still)) {
                    moves = moves + 1; // increase number of moves by 1
                }

            }
            total += moves; // this is for calculating the final average cases
            if (best >= moves) { // finding the most efficent test trial
                best = moves;
            }
            moves = 0; // reset moves
            counter -= 1;
        }
        System.out.println("The average number of moves is " + (total / 1000));// prints average moves
        System.out.println("The most optimal route the program found is " + best + " moves");// prints optimal moves
    }

    static void Test2(int up, int down, int left, int right, int still) {
        System.out.println("\nTest 2:");
        int max = 10; // for defining random number
        int min = 1; // for defining random number
        int counter = 1000; // the number of tests
        int moves = 0; // counter for how many moves
        int finish = 6; // the final ending square
        int total = 0; // a counter used in calculating the average moves
        int best = Integer.MAX_VALUE; // a placeholder for the best possible move
        int initial = 1; // initial position for help with walls
        while (counter != 0) {
            int position = 1;
            while (position != finish) {
                int rndm = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                int wallrand = (int) Math.floor(Math.random() * (2) + 1);// random number 1-2
                if (rndm >= 0 && rndm <= up) {
                    if ((position - 3) >= 0) {
                        position = position - 3; // go up one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > up && rndm <= (up + down)) {
                    if ((position + 3) <= 7) {
                        if (position == initial && wallrand == 1) {
                            // go down one
                        } else {
                            position = position + 3;
                        }

                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down) && rndm <= (up + down + left)) {
                    if ((position % 3) != 1) {
                        position = position - 1; // go left one space
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down + left) && rndm <= (up + down + left + right)) {
                    if ((position % 3) != 0) {
                        if (position == initial && wallrand == 1) {
                            // go right one space
                        } else {
                            position = position + 1;
                        }
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > (up + down + left + right) && rndm <= (up + down + left + right + still)) {
                    moves = moves + 1; // increase number of moves by 1
                }

            }
            total += moves;// total num of moves

            if (best >= moves) {
                best = moves;
            } // finds best number of moves (smallest to get to final spot)

            moves = 0;// resets moves
            counter -= 1; // prints total moves
        }
        System.out.println("The average number of moves is " + (total / 1000));// prints average num of moves
        System.out.println("The most optimal route the program found is " + best + " moves"); // prints most efficent
                                                                                              // test moves
    }

    static void Test3(int up, int down, int left, int right, int still) {
        System.out.println("\nTest 3:");
        int max = 10; // for declaring random numbers
        int min = 1; // for declaring random numbers
        int counter = 1000; // this is the number of test trials we run
        int moves = 0; // a counter for the number of moves the trials run
        int finish = 9; // the final ending spot for the tests
        int total = 0; // this is used for finding the average moves per trial
        int best = Integer.MAX_VALUE; // used for finding the most efficent test case
        while (counter != 0) {
            int position = 1;
            while (position != finish) {
                int rndm = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                if (rndm >= 0 && rndm <= up) {
                    if ((position - 3) >= 0) {
                        position = position - 3; // go up one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > up && rndm <= (up + down)) {
                    if ((position + 3) <= 10) {
                        position = position + 3; // go down one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down) && rndm <= (up + down + left)) {
                    if ((position % 3) != 1) {
                        position = position - 1; // go left one space
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down + left) && rndm <= (up + down + left + right)) {
                    if ((position % 3) != 0) {
                        position = position + 1; // go right one space
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > (up + down + left + right) && rndm <= (up + down + left + right + still)) {
                    moves = moves + 1; // increase number of moves by 1
                }

            }
            total += moves;// total num of moves

            if (best >= moves) {
                best = moves;
            } // finds best number of moves (smallest to get to final spot)

            moves = 0;// resets moves
            counter -= 1; // prints total moves
        }
        System.out.println("The average number of moves is " + (total / 1000));// prints average num of moves
        System.out.println("The most optimal route the program found is " + best + " moves"); // prints best attempt
    }

    static void Test4(int up, int down, int left, int right, int still) {
        System.out.println("\nTest 4:");// presentation
        int max = 10;// for rndm
        int min = 1;// for rndm
        int counter = 1000;// allows program to run 1000 times
        int moves = 0;// initial num of moves
        int finish = 1;// where agent must finish
        int total = 0;// total num of moves
        int best = Integer.MAX_VALUE;// max value to compare for best number of moves

        while (counter != 0) {// runs program until counter (1000) is at 0
            int position = 30;// initial position
            while (position != finish) {
                int rndm = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                if (rndm >= 0 && rndm <= up) {
                    if ((position - 3) >= 0) {
                        position = position - 3; // go up one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > up && rndm <= (up + down)) {
                    if ((position + 3) <= 31) {
                        position = position + 3; // go down one
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down) && rndm <= (up + down + left)) {
                    if ((position % 3) != 1) {
                        position = position - 1; // go left one space
                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down + left) && rndm <= (up + down + left + right)) {
                    if ((position % 3) != 0) {
                        position = position + 1; // go right one space
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > (up + down + left + right) && rndm <= (up + down + left + right + still)) {
                    moves = moves + 1; // increase number of moves by 1
                }

            }
            total += moves;// total num of moves

            if (best >= moves) {
                best = moves;
            } // finds best number of moves (smallest to get to final spot)

            moves = 0;// resets moves
            counter -= 1; // prints total moves
        }
        System.out.println("The average number of moves is " + (total / 1000));// prints average num of moves
        System.out.println("The most optimal route the program found is " + best + " moves"); // prints best attemp
    }

    static void Test5(int up, int down, int left, int right, int still) {
        System.out.println("\nTest 5:");// presentation
        int max = 10;// for rndm
        int min = 1;// for rndm
        int counter = 1000;// allows the program to be repeated 1000 times
        int moves = 0;// initial number of moves
        int finish = 9;// finishing point for agent
        int total = 0;// counts total number of moves
        int best = Integer.MAX_VALUE;// max value to compare for best number of moves

        while (counter != 0) {// while the counter is not at 0
            int position = 1;// initital position
            while (position != finish) {
                int rndm = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                int wallrand = (int) Math.floor(Math.random() * (2) + 1);// random number 1-2
                if (rndm >= 0 && rndm <= up) {
                    if ((position - 3) >= 0) {
                        if (wallrand == 1) {// wall is up 50% of the time

                        } else {
                            position = position - 3; // go up one
                        }
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > up && rndm <= (up + down)) {
                    if ((position + 3) <= 7) {
                        if (wallrand == 1) {// wall is up 50% of the time

                        } else {
                            position = position + 3; // go down one
                        }

                    }
                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down) && rndm <= (up + down + left)) {
                    if ((position % 3) != 1) {
                        if (wallrand == 1) {// wall is up 50% of the time
                            // go down one
                        } else {
                            position = position - 1; // go left one space
                        }
                    }

                    moves = moves + 1; // increase number of moves by 1
                }
                if (rndm > (up + down + left) && rndm <= (up + down + left + right)) {
                    if ((position % 3) != 0) {
                        if (wallrand == 1) {// wall is up 50% of the time
                            // go right one space
                        } else {
                            position = position + 1;
                        }
                    }
                    moves = moves + 1; // increase number of moves by 1

                }
                if (rndm > (up + down + left + right) && rndm <= (up + down + left + right + still)) {
                    moves = moves + 1; // increase number of moves by 1
                }

            }
            total += moves;// total num of moves

            if (best >= moves) {
                best = moves;
            } // finds best number of moves (smallest to get to final spot)

            moves = 0;// resets moves
            counter -= 1; // prints total moves
        }
        System.out.println("The average number of moves is " + (total / 1000));// prints average num of moves
        System.out.println("The most optimal route the program found is " + best + " moves"); // prints best attemp
    }
}