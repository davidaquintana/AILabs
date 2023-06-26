/*
Ai Lab2 - Tests 1 and 2
David Quintana, Ben Cerrato
CSC207, Spring 2023 Eliott, PhD
Acknowledgements: 
Fernanda Elliot, Class mentors, AI Lab I

Description:

We have created a program tat creates two agents that navagate
a grid world environment at the same time. The grid is show below.
In this environment the agents have an initial state and there is an 
end state that they are trying to get to. The agents have four
possible actions to take. Up, Down, Left, or Right. The agents can not
 bump into eachother (end up on the same grid at the same time).
The agents are also blocked by two walls which are between spaces
4/7 as well as 6/9. These walls are open 50 percent of the time and will 
affect the number of attempts it takes the agent to complete their trials.
We run each of the two tests 1000 times and record the optimal steps 
taken by each agent, the average number of steps taken for each agent to 
reach the final state, the number of times the agents reached the final
state at the exact same move. And how many times each agent won the 
game, and more.

Graph environment:

   1  end(2) 3

   4    5    6
  ___       ___ <-- Two walls with a 50/50 chance of being open or closed
   A    8    B

*/
public class AiLab2 {
    public static void main(String[] args) {
        System.out.println("\n\nHere is test 1: \n");
        test(25, 25, 25, 25, 25, 25, 25, 25); // test 1
        System.out.println("Here is test 2: \n");
        test(40, 20, 20, 20, 30, 20, 30, 20); // test 2

    }

    static void test(int upA, int downA, int rightA, int leftA, int upB, int downB, int rightB, int leftB) {
        int max = 100;// for rndm
        int min = 1;// for rndm
        int counter = 1000;// allows the program to be repeated 1000 times
        int movesA = 0;// initial number of moves (agent A)
        int movesB = 0;// initial number of moves (agent B)
        int finish = 2;// finishing point for agents
        double avgA = 0;// counts average number of moves (agent A)
        double avgB = 0;// counts average number of moves (agent B)
        int bestA = Integer.MAX_VALUE;// max value to compare for best number of moves
        int bestB = Integer.MAX_VALUE;// max value to compare for best number of moves
        int finishSameA = 0; // checker if finish at the same time
        int finishSameB = 0; // checker if finish at the same time
        int sameFinish = 0; // check number of same time finishes
        int aWin = 0; // check A wins
        int bWin = 0; // check B wins
        double totAvg = 0.0; // check Average of turns for end of simulaion

        while (counter != 0) { // counter for how many trials taken
            int positionA = 7; // initial position for Agent A
            int positionB = 9; // initial position for Agent B
            while (positionA != finish || positionB != finish) {

                int rndmA = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                int rndmB = (int) Math.floor(Math.random() * (max - min + 1) + min);// random number max-min
                int wallrandA = (int) Math.floor(Math.random() * (2) + 1);// random number 1-2
                int wallrandB = (int) Math.floor(Math.random() * (2) + 1);// random number 1-2

                int HolderA = positionA; // holds the place value of the position A in case the positions overlap
                int HolderB = positionB; // holds the place value of the position B in case the positions overlap

                // A movement
                if (positionA != finish) {
                    if (rndmA <= upA && positionA >= 4) // when to move up (A)
                    {
                        if (positionA == 7 && wallrandA == 1) {// Check if wall blocks from going upwards
                        } else {
                            positionA -= 3;
                        }
                    } // movement up based on position for A
                    if (rndmA > upA && rndmA <= (upA + downA) && positionA <= 4) // when to down up (A)
                    {
                        positionA += 3;

                    } // movement down based on position for A
                    if (rndmA > (upA + downA) && rndmA <= (upA + downA + leftA) && (positionA % 3) != 1) // when to left
                                                                                                         // up (A)
                    {
                        positionA -= 1;
                    } // movement left based on position for A
                    if (rndmA > (upA + downA + leftA) && (positionA % 3) != 0) // when to right up (A)
                    {
                        positionA += 1;
                    } // movement right based on position for A
                    movesA = movesA + 1;
                } else {
                    finishSameA += 1;
                }

                // B movement
                if (positionB != finish) {
                    if (rndmB <= upB && positionB >= 4) // when to move up (B)
                    {
                        if (positionB == 9 && wallrandB == 1) { // Check if wall blocks from going upwards
                        } else {
                            positionB -= 3;
                        }
                    } // movement up based on position for B
                    if (rndmB > upB && rndmB <= (upB + downB) && positionB <= 4) // when to move down (B)
                    {
                        positionB += 3;
                    } // movement down based on position for B
                    if (rndmB > (upB + downB) && rndmB <= (upB + downB + leftB) && (positionB % 3) != 1) // when to move
                                                                                                         // left (B)
                    {
                        positionB -= 1;
                    } // movement left based on position for B
                    if (rndmB > (upB + downB + leftB) && (positionB % 3) != 0) // when to move right (B)
                    {
                        positionB += 1;
                    } // movement right based on position for B
                    movesB = movesB + 1;
                } else {
                    finishSameB += 1;
                }

                if (positionA == positionB && finishSameA == finishSameB && positionA == finish) {
                    sameFinish += 1;
                }

                // If agend A and B are in the same spot
                if (positionA == positionB && positionA != finish && positionB != finish) {
                    positionA = HolderA;
                    positionB = HolderB;
                }

            }
            if (movesB > movesA) // used to find the total average moves for all 1000 trials
            {
                totAvg += movesB;
            } else {
                totAvg += movesA;
            }
            avgA = avgA + movesA; // increment average A moves
            avgB = avgB + movesB; // increment average B moves
            if (movesA < bestA) {
                bestA = movesA;
            } // calculate the best move for the Agent A
            if (movesB < bestB) {
                bestB = movesB;
            } // calculate the best move for the Agent B
            movesA = 0; // reset move counter
            movesB = 0; // reset move counter
            if (finishSameA > finishSameB) {
                aWin += 1;
            }
            if (finishSameA < finishSameB) {
                bWin += 1;
            }
            finishSameA = 0;
            finishSameB = 0;
            counter -= 1; // Make sure the program ends after 1000 simulations - counter is used in the
                          // while loop
        }
        // Calculate and print out all final data neatly for the user
        System.out.println("\nThe average number of moves to completely finish one simulation of this test is: "
                + (totAvg / 1000.0) + "\n"); // average moves by A and B combined
        System.out.println("The average number of Agent A moves to finish this test is: " + (avgA / 1000) + "\n"); // average
                                                                                                                   // moves
                                                                                                                   // by
                                                                                                                   // A
        System.out.println("The average number of Agent B moves to finish this test is: " + (avgB / 1000) + "\n"); // average
                                                                                                                   // moves
                                                                                                                   // by
                                                                                                                   // B
        System.out
                .println("The optimal number of moves made by Agent A was: " + bestA + " by Agent B was: " + bestB
                        + "\n"); // optimal moves counter
        System.out.println("The two agents finished at the same time " + sameFinish + " out of 1000 simulations \n"); // same
        // time
        // finish
        // counter
        System.out.println("Agent A won " + aWin + " times, Agent B won " + bWin + " times\n\n\n"); // wins counter

    }
}
