/**
 * File Name:    AntiSocialAgent.java
 * Author:       Azeem Gbolahan
 * Description:  This class defines the behavior of an AntiSocial agent, a type of agent
 *               that moves away when there are too many neighbors nearby. The agent
 *               will randomly relocate if more than one neighbor is detected within a
 *               specified radius. 
 *
 *               Includes:
 *               - Random movement logic based on neighbor count
 *               - Drawing logic for visual representation
 *               - Accessor and mutator methods for radius
 *
 * How to Run:   Compile using `javac AntiSocialAgent.java` and run within a simulation framework.
 */

 import java.awt.*;
 import java.util.Random;
 
 public class AntiSocialAgent extends Agent {
     int radius; // radius to detect neighbors
 
     /**
      * Constructs a new AntiSocialAgent with given position and detection radius.
      * @param x0 the initial x-coordinate of the agent
      * @param y0 the initial y-coordinate of the agent
      * @param radius the interaction radius for neighbor detection
      */
     public AntiSocialAgent(double x0, double y0, int radius){
         super(x0, y0, radius);       // initialize parent Agent class with position and radius
         this.radius = radius;       // set the radius for this AntiSocialAgent
     }
 
     /**
      * Updates the detection radius of the agent.
      * @param radius the new radius to be set
      */
     public void setRadius(int radius){
         this.radius = radius; // update radius field
     }
 
     /**
      * Gets the detection radius of the agent.
      * @return the current radius
      */
     public int getRadius(){
         return this.radius; // return radius value
     }
 
     /**
      * Draws the agent as a small red circle, color changes if it moved.
      * @param g the graphics context
      */
     public void draw(Graphics g){
         if(!moved) g.setColor(new Color(255, 0, 0));       // red if not moved
         else g.setColor(new Color(255, 125, 125));         // lighter red if moved
 
         g.fillOval((int) getX(), (int) getY(), 5, 5);      // draw a circle at agent's position
     }
 
     /**
      * Updates the state of the agent. If more than 1 neighbor is found in the radius,
      * the agent relocates to a random nearby position within the landscape bounds.
      * @param scape the landscape containing the agent and its neighbors
      */
     public void updateState(Landscape scape) {
         LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius); // get nearby agents
 
         if (neighbors.size() > 1) { // if more than 1 neighbor exists
             Random random = new Random(); // random number generator
             double deltaX, deltaY, newX, newY;
 
             do {
                 deltaX = random.nextDouble() * 20 - 10; // generate a random offset in x (-10 to 10)
                 deltaY = random.nextDouble() * 20 - 10; // generate a random offset in y (-10 to 10)
 
                 newX = this.getX() + deltaX; // calculate new x position
                 newY = this.getY() + deltaY; // calculate new y position
             } while (newX < 0 || newX > scape.getWidth() || newY < 0 || newY > scape.getHeight());
 
             this.setX(newX); // update x coordinate
             this.setY(newY); // update y coordinate
 
             moved = true; // mark the agent as moved
         } else {
             moved = false; // mark the agent as not moved if no action taken
         }
     }
 }
 
