/**
 * File Name:    AgentSimulation.java
 * Author:       Azeem Gbolahan
 * Description:  This class simulates the interaction and movement of agents within a
 *               2D landscape. It initializes a grid, populates it with agents, and
 *               visually demonstrates their behavior through state updates.
 *
 *               Includes:
 *               - Main simulation driver with command-line arguments
 *               - Dynamic agent addition and randomized placement
 *               - Visualization updates via LandscapeDisplay
 *
 * How to Run:   Compile using `javac AgentSimulation.java` and run with
 *               `java -ea AgentSimulation <width> <height> <num_agents>`
 *               Make sure assertions are enabled with the `-ea` flag.
 */

 import java.util.Random;

 public class AgentSimulation {
 
     /**
      * The main method processes input arguments, starts the simulation,
      * and prints how many iterations it took for agents to stop moving.
      *
      * @param args Command-line arguments for width, height, and number of agents.
      * @throws InterruptedException if thread sleep is interrupted.
      */
     public static void main(String[] args) throws InterruptedException {
         // Ensure the user provides exactly three command-line arguments
         if (args.length < 3) {
             System.out.println("Usage: java AgentSimulation <width> <height> <num_agents>");
             return; // Exit early if arguments are insufficient
         }
 
         // Parse the first three command-line arguments as integers
         int width = Integer.parseInt(args[0]);   // Width of the landscape
         int height = Integer.parseInt(args[1]);  // Height of the landscape
         int numAgents = Integer.parseInt(args[2]); // Number of agents to simulate
 
         // Set a maximum number of allowed iterations to avoid infinite loops
         int maxIterations = 5000;
 
         // Run the simulation with the given number of agents and return total iterations
         int iterations = runExpt(numAgents);
 
         // Print the number of iterations before the simulation ended
         System.out.println("Simulation ended after " + iterations + " iterations.");
     }
 
     /**
      * Runs the agent simulation on a fixed-sized landscape and visualizes the process.
      *
      * @param N The number of agents to simulate.
      * @return The number of iterations before agents stopped moving.
      * @throws InterruptedException if thread sleep is interrupted.
      */
     public static int runExpt(int N) throws InterruptedException {
         // Create a landscape of fixed dimensions 500x500
         Landscape scape = new Landscape(500, 500);
 
         // Create a display window to visualize the landscape
         LandscapeDisplay display = new LandscapeDisplay(scape);
 
         // Create a random number generator for agent placement
         Random random = new Random();
 
         // Add N agents to the landscape with random (x, y) positions
         for (int i = 0; i < N; i++) {
             double x = random.nextDouble() * scape.getWidth();  // Random x-coordinate
             double y = random.nextDouble() * scape.getHeight(); // Random y-coordinate
 
             // Add a SocialAgent with a default radius of 25
             scape.addAgent(new SocialAgent(x, y, 25));
         }
 
         int iterationCount = 0; // Tracks how many iterations have occurred
         int numMoved = 1;       // Initial movement value to start the loop
 
         // Keep looping as long as at least one agent moves and limit to 5000 iterations
         while (numMoved > 0 && iterationCount < 5000) {
             Thread.sleep(50); // Slow down the visualization for human viewing
 
             // Update the state of all agents in the landscape
             numMoved = scape.updateAgents();
 
             // Refresh the visual display to reflect new agent positions
             display.repaint();
 
             // Increment the iteration counter
             iterationCount++;
         }
 
         // Return how many iterations the simulation ran before agents stabilized
         return iterationCount;
     }
 }
 
//Exploration Code 

/*public class AgentSimulation {

    public static void main(String[] args) throws InterruptedException {
        // Ensure correct number of command-line arguments
        if (args.length < 4) {
            System.out.println("Usage: java AgentSimulation <width> <height> <num_agents> <radius>");
            return;
        }

        // Parse command-line arguments
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int numAgents = Integer.parseInt(args[2]);
        double radius = Double.parseDouble(args[3]); // Accept radius from the command line

        // Run simulation and get iteration count
        int iterations = runExpt(numAgents, width, height, radius);

        // Print final iteration count
        System.out.println("Simulation ended after " + iterations + " iterations.");
    }

    /**
     * Runs the agent simulation for a given number of agents and returns the number of iterations.

    public static int runExpt(int N, int width, int height, double radius) throws InterruptedException {
        // Initialize landscape and display
        Landscape scape = new Landscape(width, height);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        Random random = new Random();

        // Add N SocialAgents with the specified radius
        for (int i = 0; i < N; i++) {
            scape.addAgent(new SocialAgent(random.nextDouble() * scape.getWidth(),
                    random.nextDouble() * scape.getHeight(), (int)radius)); // Use radius from command line
        }

        int iterationCount = 0;
        int numMoved = 1; // Initial value: assume all agents move in the first iteration

        // Main simulation loop
        while (numMoved > 0 && iterationCount < 5000) {
            Thread.sleep(50); // Pause for visualization (adjustable delay)

            // Update the state of the agents
            numMoved = scape.updateAgents();
            display.repaint();
            iterationCount++;
        }

        return iterationCount; // Return the number of iterations the loop ran
    }
}
*/