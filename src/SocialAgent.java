/**
 * File Name:    SocialAgent.java
 * Author:       Azeem Gbolahan
 * Description:  This file defines the SocialAgent class, which extends the abstract Agent class.
 *               A SocialAgent moves randomly only when the number of neighboring agents within
 *               a given radius is less than 4. Otherwise, it remains in its current position.
 *               The agent also changes its visual appearance based on whether it moved.
 *
 *               Includes:
 *               - Movement logic based on neighbor count
 *               - Dynamic drawing based on movement state
 *               - Radius configuration and interaction with the simulation environment
 *
 * How to Run:   Compile using `javac SocialAgent.java` and integrate with a Landscape simulation.
 */

 import java.awt.*;
 import java.util.Random;
 
 public class SocialAgent extends Agent {
     private int radius; // specific radius used for neighbor detection
 
     // Constructor that initializes the agent with coordinates and radius
     public SocialAgent(double x0, double y0, int radius){
         super(x0, y0, radius); // call the superclass constructor to set position and radius
         this.radius = radius; // store radius locally for future use
     }
 
     // Setter method to update the radius
     public void setRadius(int radius){
         this.radius = radius; // update the radius field
     }
 
     // Getter method to retrieve the current radius
     public int getRadius(){
         return this.radius; // return current radius value
     }
 
     // Method to render the agent visually on the screen
     public void draw(Graphics g){
         if(!moved) {
             g.setColor(new Color(0, 0, 255)); // blue if agent has not moved
         }
         else {
             g.setColor(new Color(125, 125, 255)); // lighter blue if agent has moved
         }
         g.fillOval((int) getX(), (int) getY(), 5, 5); // draw a small circle at agent's location
     }
 
     // Method to update the agent's state based on nearby agents
     public void updateState(Landscape scape) {
         // retrieve neighbors within the specified radius
         LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius);
 
         // If fewer than 4 neighbors are found, move randomly
         if (neighbors.size() < 4) {
             Random random = new Random(); // create random number generator
             double deltaX, deltaY, newX, newY; // variables to hold new positions
 
             do {
                 deltaX = random.nextDouble() * 20 - 10;  // random X movement between -10 and 10
                 deltaY = random.nextDouble() * 20 - 10;  // random Y movement between -10 and 10
 
                 newX = this.getX() + deltaX; // calculate new X coordinate
                 newY = this.getY() + deltaY; // calculate new Y coordinate
             } while (newX < 0 || newX > scape.getWidth() || newY < 0 || newY > scape.getHeight());
 
             this.setX(newX); // update x position of agent
             this.setY(newY); // update y position of agent
 
             moved = true; // indicate that the agent has moved
         } else {
             moved = false; // agent stays in place if there are enough neighbors
         }
     }
 }
 