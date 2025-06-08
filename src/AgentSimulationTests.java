/**
 * File Name:    AgentSimulationTests.java
 * Authors:      Ike Lage
 * Last Modified: 03/04/2025
 * 
 * Description:  This class tests the AgentSimulation behavior by running
 *               experiments with a specific number of agents and checking
 *               that the simulation stabilizes within an expected range
 *               of iterations.
 * 
 * How to Run:   Compile with `javac AgentSimulationTests.java` and run with
 *               assertions enabled: `java -ea AgentSimulationTests`
 */

 import java.util.Random;

 public class AgentSimulationTests {
 
     /**
      * Runs a simulation experiment with N social agents and returns the number
      * of iterations required until agents stop moving or max iteration is reached.
      * 
      * @param N the number of social agents to simulate
      * @return the number of iterations until the simulation stabilizes
      */
     public static int runExpt(int N) {
         Landscape scape = new Landscape(500, 500); // Create a 500x500 simulation space
         Random gen = new Random(); // Random generator for agent placement
 
         // Populate the landscape with N social agents with radius 25
         for (int i = 0; i < N; i++) {
             scape.addAgent(new SocialAgent(gen.nextDouble() * scape.getWidth(),
                                            gen.nextDouble() * scape.getHeight(),
                                            25));
         }
 
         int numMoved = 10; // Initial arbitrary value to enter loop
         int count = 0;     // Tracks iteration count
 
         // Run simulation until all agents stop moving or max 5000 iterations
         while (numMoved > 0 && count < 5000) {
             numMoved = scape.updateAgents(); // Update agents and get number that moved
             count++;                         // Increment iteration counter
         }
 
         return count; // Return how many iterations were needed
     }
 
     /**
      * Executes multiple simulation tests with known expected behavior to
      * evaluate correctness of AgentSimulation.
      * 
      * @return a score representing how many tests passed
      */
     public static double agentSimulationTests() {
         double score = 1.0; // Base score
 
         // Test cases with expected average iteration counts
         int[] nums = { 50, 100 }; // Number of agents
         int[] correctAnswers = { 234, 541 }; // Expected averages from benchmark runs
 
         for (int i = 0; i < nums.length; i++) {
             int output = 0;
             for (int j = 0; j < 20; j++) {
                 output += runExpt(nums[i]); // Run 20 simulations for each agent count
             }
             output /= 20; // Compute average iteration count
             System.out.println(output); // Print average for debugging
 
             // Score 1 point if output is within +-100 of expected average
             if (output < correctAnswers[i] + 100 && output > correctAnswers[i] - 100) {
                 score += 1.0;
             }
         }
 
         // Total max score is 3.0 (1 base + 2 tests)
         return score;
     }
 
     /**
      * Main method that runs the simulation tests and prints the total score.
      * 
      * @param args not used
      */
     public static void main(String[] args) {
         System.out.println(agentSimulationTests()); // Output final score
     }
 } // End of AgentSimulationTests
 
