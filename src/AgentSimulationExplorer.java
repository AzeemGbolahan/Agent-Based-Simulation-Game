//Exploration Code 

import java.util.Random;

public class AgentSimulationExplorer {

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
     * Runs the simulation and returns the number of iterations until stabilization.
     *
     * @param N Number of agents
     * @param width Width of the landscape
     * @param height Height of the landscape
     * @param radius Radius of interaction for agents
     * @return Number of iterations until no agents move or limit is reached
     * @throws InterruptedException if thread sleep is interrupted
     */
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

