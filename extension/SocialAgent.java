/**
 * File Name:    SocialAgent.java
 * Author:       Azeem Gbolahan
 * Description:  This class implements the Social type of Agents.
 *               Social agents are attracted to nearby agents and move towards them
 *               if there are more than one in the defined radius. This class extends
 *               the Agent base class and includes logic for movement, rendering, and radius control.
 *
 * How to Run:   Compile using `javac SocialAgent.java` and execute as part of the agent simulation.
 *
 * Example Usage:
 *      SocialAgent agent = new SocialAgent(10.0, 20.0, 25);
 *      agent.updateState(landscape);
 *      agent.draw(graphics);
 */

 import java.awt.*; // Import AWT for rendering

 public class SocialAgent extends Agent {
     private int radius; // Radius within which the agent checks for neighbors
 
     /**
      * Constructor to create a SocialAgent at a given position with a defined radius.
      * @param x0 Initial x-coordinate
      * @param y0 Initial y-coordinate
      * @param radius Radius for detecting neighbors
      */
     public SocialAgent(double x0, double y0, int radius){
         super(x0, y0, radius); // Call the superclass constructor
         this.radius = radius; // Initialize radius
     }
 
     /**
      * Sets the interaction radius of the agent.
      * @param radius The new radius value
      */
     public void setRadius(int radius){
         this.radius = radius;
     }
 
     /**
      * Returns the current interaction radius of the agent.
      * @return The radius value
      */
     public int getRadius(){
         return this.radius;
     }
 
     /**
      * Draws the SocialAgent as a circle, with color based on movement status.
      * @param g Graphics object for drawing
      */
     public void draw(Graphics g){
         if(!moved) {
             g.setColor(new Color(0, 0, 255)); // Blue when not moved
         } else {
             g.setColor(new Color(125, 125, 255)); // Light blue when moved
         }
         g.fillOval((int) getX(), (int) getY(), 5, 5); // Draw agent as small circle
     }
 
     /**
      * Updates the agent's position based on the average position of its neighbors.
      * @param scape The landscape containing other agents
      */
     public void updateState(Landscape scape) {
         LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius); // Find nearby agents
 
         if (neighbors.size() > 1) { // Only act if more than one neighbor exists
             double avgX = 0;
             double avgY = 0;
 
             for (Agent neighbor : neighbors) {
                 avgX += neighbor.getX(); // Sum x-positions
                 avgY += neighbor.getY(); // Sum y-positions
             }
 
             avgX /= neighbors.size(); // Compute average x
             avgY /= neighbors.size(); // Compute average y
 
             double deltaX = avgX - this.getX(); // Compute direction to average x
             double deltaY = avgY - this.getY(); // Compute direction to average y
 
             double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Euclidean distance
             if (distance > 0) {
                 deltaX /= distance; // Normalize x
                 deltaY /= distance; // Normalize y
             }
 
             this.setX(this.getX() + deltaX * 3); // Move toward group in x
             this.setY(this.getY() + deltaY * 3); // Move toward group in y
 
             moved = true; // Mark as moved
         } else {
             moved = false; // No movement if alone
         }
     }
 }
 