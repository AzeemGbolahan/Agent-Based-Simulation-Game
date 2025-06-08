/**
 * File Name:    AntiSocialAgent.java
 * Author:       Azeem Gbolahan
 * Description:  This class implements an AntiSocial agent that inherits from the 
 *               base Agent class. The AntiSocial agent is designed to move away 
 *               from nearby agents (if more than one is detected) within a given 
 *               radius, simulating avoidance behavior.
 *
 *               Key Features:
 *               - Custom updateState logic for fleeing from the closest neighbor.
 *               - Dynamic graphical rendering based on movement status.
 *               - Radius-based neighbor detection logic with helper method.
 *
 * How to Run:   Compile using `javac AntiSocialAgent.java` and integrate into 
 *               an agent-based simulation using `AgentSimulation` or similar
 *               classes with landscape support.
 *
 * Example Usage:
 *      AntiSocialAgent agent = new AntiSocialAgent(10.0, 20.0, 25);
 *      agent.updateState(landscape);
 *      agent.draw(graphics);
 */

 import java.awt.*; // Import AWT for graphical components

 /**
  * The AntiSocialAgent class models an agent that moves away from nearby agents.
  * It extends the Agent class and implements a specific escape behavior.
  */
 public class AntiSocialAgent extends Agent {
     boolean moved; // Tracks whether the agent has moved during the last update
     int radius;    // Radius within which this agent checks for neighbors
 
     /**
      * Constructor to create an AntiSocialAgent at a given position with a given radius.
      * @param x0 Initial x-coordinate
      * @param y0 Initial y-coordinate
      * @param radius Interaction radius for neighbor detection
      */
     public AntiSocialAgent(double x0, double y0, int radius){
         super(x0, y0,radius);        // Call the parent Agent constructor
         this.radius = radius;       // Initialize the radius
     }
 
     /**
      * Sets the interaction radius of the agent.
      * @param radius The new radius to set
      */
     public void setRadius(int radius){
         this.radius = radius;       // Assign new radius
     }
 
     /**
      * Gets the current interaction radius of the agent.
      * @return the radius
      */
     public int getRadius(){
         return this.radius;         // Return current radius
     }
 
     /**
      * Draws the agent on the screen. The color changes based on movement status.
      * @param g The graphics context used to draw
      */
     public void draw(Graphics g){
         if(!moved) g.setColor(new Color(255, 0, 0));        // Red if not moved
         else g.setColor(new Color(255, 125, 125));          // Lighter red if moved
         g.fillOval((int) getX(), (int) getY(), 5, 5);       // Draw the agent as a small circle
     }
 
     /**
      * Updates the state of the agent by moving it away from the closest neighbor
      * if more than one neighbor is detected within its radius.
      * @param scape The landscape containing all agents
      */
     public void updateState(Landscape scape) {
         LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius); // Get neighbors in range
 
         if (neighbors.size() > 1) {                                  // Only move if more than one neighbor
             Agent closestNeighbor = getClosestNeighbor(neighbors);  // Find the closest neighbor
 
             double deltaX = this.getX() - closestNeighbor.getX();   // Difference in x
             double deltaY = this.getY() - closestNeighbor.getY();   // Difference in y
 
             double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Euclidean distance
 
             if (distance > 0) {        // Normalize to avoid division by zero
                 deltaX /= distance;    // Normalize x-direction
                 deltaY /= distance;    // Normalize y-direction
             }
 
             this.setX(this.getX() + deltaX * 5); // Move away along x
             this.setY(this.getY() + deltaY * 5); // Move away along y
 
             moved = true;              // Mark as moved
         } else {
             moved = false;             // No move if no nearby agents
         }
     }
 
     /**
      * Helper method to determine the closest agent among a list of neighbors.
      * @param neighbors List of nearby agents
      * @return The closest neighboring agent
      */
     private Agent getClosestNeighbor(LinkedList<Agent> neighbors) {
         Agent closest = neighbors.get(0); // Start with first agent as closest
         double minDist = Math.sqrt(Math.pow(this.getX() - closest.getX(), 2) +
                                    Math.pow(this.getY() - closest.getY(), 2)); // Initial minimum distance
 
         for (Agent neighbor : neighbors) { // Loop through all neighbors
             double dist = Math.sqrt(Math.pow(this.getX() - neighbor.getX(), 2) +
                                    Math.pow(this.getY() - neighbor.getY(), 2)); // Distance to this neighbor
             if (dist < minDist) {      // If closer, update
                 minDist = dist;
                 closest = neighbor;
             }
         }
         return closest; // Return the closest neighbor
     }
 }
 