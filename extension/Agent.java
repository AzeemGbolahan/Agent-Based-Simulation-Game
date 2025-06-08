/**
 * File Name:    Agent.java
 * Author:       Azeem Gbolahan
 * Description:  This file defines an abstract class 'Agent' that models an entity in a 2D spatial
 *               simulation environment. Each agent maintains coordinates (x, y), a radius,
 *               and a movement flag to indicate state changes.
 *
 *               Subclasses must implement:
 *               - updateState(Landscape scape): to define dynamic behavior per update cycle
 *               - draw(Graphics g): to define how the agent is visually rendered
 *
 *               Includes:
 *               - Position management via get/set methods
 *               - Formatted string representation of position
 *
 * How to Run:   Compile using `javac Agent.java` and integrate into a simulation framework.
 */

 import java.awt.*;

 public abstract class Agent {
     // fields for Agent class
 
     private double y; // a property to indicate the y position of the agent in the window
     private double x; // a property to indicate the x position of the agent in the window
     protected int radius; // // a property to indicate the radius of the agent
     protected boolean moved; // a property to indicate the movement state of the agent - whether it has moved or not
 
     // constructor for initializing the position (x, y) and radius of the agent
     public Agent(double x0, double y0, int radius){
         this.x = x0; // set initial x-coordinate
         this.y = y0; // set initial y-coordinate
         this.radius = radius; // set radius of the agent
         this.moved = false; // initially the agent has not moved
     }
 
     // getter method for x-coordinate
     public double getX(){
         return this.x; // return current x position
     }
 
     // getter method for y-coordinate
     public double getY(){
         return this.y; // return current y position
     }
 
     // getter method for radius
     public int getRadius() {
         return this.radius; // return the radius of the agent
     }
 
     // getter method for movement status
     public boolean getMoved() {
         return this.moved; // return true if the agent has moved, false otherwise
     }
 
     // setter method for x-coordinate
     public void setX(double newX){
         this.x = newX; // update x to the new value
     }
 
     // setter method for y-coordinate
     public void setY(double newY){
         this.y = newY; // update y to the new value
     }
 
     // returns the position of the agent formatted to 3 decimal places
     public String toString(){
         return String.format("(%.3f, %.3f)", x, y); // formatted string for coordinates
     }
 
     // abstract method to be implemented by subclasses that defines how an agent updates its state
     public abstract void updateState(Landscape scape);
 
     // abstract method to be implemented by subclasses that defines how an agent is drawn on screen
     public abstract void draw(Graphics g);
 
 }
 