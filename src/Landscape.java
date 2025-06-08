/**
 * File Name:    Landscape.java
 * Author:       Azeem Gbolahan
 * Description:  This class implements a simulation environment called Landscape
 *               that holds and manages multiple Agent objects in 2D space. It
 *               supports rendering agents, updating their states, and finding
 *               neighboring agents within a given radius.
 *
 *               Includes:
 *               - Method to add and retrieve agents
 *               - Method to retrieve neighbors within a radius
 *               - Method to update agent states with random agent transformation
 *               - Drawing method to render all agents on a canvas
 *
 * How to Run:   Compile using `javac Landscape.java` and run within a simulation framework.
 */

 import java.awt.*;
 import java.lang.Math;
 import java.util.Random;
 
 public class Landscape {
 
     // fields for the Landscape class
     private int width;                  // width of the landscape in pixels
     private int height;                 // height of the landscape in pixels
     private LinkedList<Agent> agents;   // linked list to store Agent objects
 
     // constructor to initialize landscape dimensions and agent list
     public Landscape(int w, int h){
         this.width = w;                          // set the width
         this.height = h;                         // set the height
         this.agents = new LinkedList<Agent>();   // initialize the agent list
     }
 
     /**
     * Gets the height of the landscape.
     * @return the height of the landscape
     */
     public int getHeight(){
         return height; // return the height of the landscape
     }
 
     /**
     * Gets the width of the landscape.
     * @return the width of the landscape
     */
     public int getWidth(){
         return width; // return the width of the landscape
     }
 
    /**
     * Adds an agent to the landscape.
     * @param a the agent to be added
     */
     public void addAgent(Agent a){
         agents.add(a); // append the agent to the agents list
     }
 
    /**
     * Returns a string representation of the Landscape.
     * @return a string describing the number of agents
     */
     public String toString() {
         return "Landscape with " + agents.size() + " agents."; // show number of agents
     }
 
    /**
     * Returns a list of neighboring agents within a certain radius of a point.
     * @param x0 the x-coordinate of the center point
     * @param y0 the y-coordinate of the center point
     * @param radius the radius within which to search for neighbors
     * @return a linked list of agents within the given radius
     */
     public LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
         LinkedList<Agent> neighbors = new LinkedList<>();       // list to store nearby agents
         double radiusSquared = radius * radius;                 // avoid using sqrt for distance
 
         for (Agent agent : agents) {
             if (agent.getX() == x0 && agent.getY() == y0) {
                 continue; // skip the agent if it's exactly at the center
             }
 
             double dx = agent.getX() - x0; // difference in x
             double dy = agent.getY() - y0; // difference in y
             double distanceSquared = dx * dx + dy * dy; // squared Euclidean distance
 
             if (distanceSquared <= radiusSquared) { // check if within radius
                 neighbors.add(agent); // add agent to neighbors
             }
         }
 
         return neighbors; // return the list of neighboring agents
     }
 
     /**
     * Updates the state of agents in the landscape.
     * One agent is randomly converted to an AntiSocialAgent before updating.
     * @return the number of agents that moved
     */
     public int updateAgents() {
         if (agents.isEmpty()) {
             return 0; // return 0 if no agents to update
         }
 
         // Step 1: Randomly select an agent
         Random random = new Random();
         int randomIndex = random.nextInt(agents.size()); // pick random index
         Agent selectedAgent = agents.remove(randomIndex); // remove selected agent
 
         // Step 2: Create a new AntiSocialAgent with same properties
         double x = selectedAgent.getX();       // get x-position
         double y = selectedAgent.getY();       // get y-position
         double radius = selectedAgent.getRadius(); // get radius
 
         AntiSocialAgent newAgent = new AntiSocialAgent(x, y, (int)radius); // cast and recreate agent
         agents.add(newAgent); // add new antisocial agent to list
 
         // Step 3: Update state of each agent and count how many moved
         int movedCount = 0; // initialize movement counter
         for (Agent agent : agents) {
             agent.updateState(this); // update state of agent
 
             if(agent.getMoved()==true) {
                 movedCount++; // increment if agent moved
             }
         }
 
         return movedCount; // return number of moved agents
     }
 
     /**
     * Gets the list of agents in the landscape.
     * @return the list of agents
     */
     public LinkedList<Agent> getAgents() {
         return agents; // return the internal agent list
     }
 
    /**
     * Draws all agents in the landscape using the provided Graphics context.
     * @param g the graphics context used to render agents
     */
     public void draw(Graphics g) {
         for (Agent agent : agents) {
             agent.draw(g); // call draw method for each agent
         }
     }
 }
 