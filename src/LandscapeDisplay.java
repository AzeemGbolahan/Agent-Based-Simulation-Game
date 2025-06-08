/**
 * File Name:    LandscapeDisplay.java
 * Originally written by Bruce A. Maxwell.
 * Updated by:   Brian Eastwood and Stephanie Taylor
 * Modified and documented by: Azeem Gbolahan
 * 
 * Description:  Displays a Landscape graphically using Swing. It creates a window
 *               with a JPanel that visualizes a list of agents in a continuous space.
 *               The Landscape object must implement a draw method for rendering.
 * 
 * How to Run:   Compile with `javac LandscapeDisplay.java` and run using `java LandscapeDisplay <radius>`
 */

 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 import java.util.Random;
 import javax.imageio.ImageIO;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 public class LandscapeDisplay {
     protected JFrame win;            // JFrame window for displaying the panel
     protected Landscape scape;       // Landscape object to be drawn
     private LandscapePanel canvas;   // Custom JPanel used for rendering the Landscape
 
     /**
      * Constructor: Initializes the JFrame window and sets up the drawing panel.
      * 
      * @param scape the Landscape object to be displayed
      */
     public LandscapeDisplay(Landscape scape) {
         this.win = new JFrame("Agents");                        // Create the JFrame
         this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
 
         this.scape = scape; // Save the landscape instance
 
         // Create and set the custom drawing panel
         this.canvas = new LandscapePanel(this.scape.getWidth(), this.scape.getHeight());
 
         // Add the canvas to the JFrame and set visibility
         this.win.add(this.canvas, BorderLayout.CENTER);
         this.win.pack(); // Resize window to fit preferred canvas size
         this.win.setVisible(true); // Display the window
     }
 
     /**
      * Saves an image of the display to a file in supported format (e.g., .png).
      *
      * @param filename the output filename including extension
      */
     public void saveImage(String filename) {
         String ext = filename.substring(filename.lastIndexOf('.') + 1); // Extract file extension
         Component tosave = this.win.getRootPane(); // Component to capture
         BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), BufferedImage.TYPE_INT_RGB);
 
         Graphics g = image.createGraphics(); // Get graphics context
         tosave.paint(g);                     // Paint content into the image buffer
         g.dispose();                         // Release resources
 
         try {
             ImageIO.write(image, ext, new File(filename)); // Save image to file
         } catch (IOException ioe) {
             System.out.println(ioe.getMessage()); // Print error if it fails
         }
     }
 
     /**
      * Inner class providing a custom JPanel used to render the Landscape.
      */
     private class LandscapePanel extends JPanel {
 
         /**
          * Constructor: Sets preferred size and background color of the panel.
          * 
          * @param width width of the drawing panel in pixels
          * @param height height of the drawing panel in pixels
          */
         public LandscapePanel(int width, int height) {
             super();
             this.setPreferredSize(new Dimension(width, height)); // Set panel dimensions
             this.setBackground(Color.white);                     // Set background color
         }
 
         /**
          * Overridden method from JPanel to draw the Landscape onto the screen.
          * 
          * @param g the Graphics context used to draw shapes/images
          */
         public void paintComponent(Graphics g) {
             super.paintComponent(g); // Call superclass method to clear background
             scape.draw(g);           // Delegate drawing to Landscape's draw method
         }
     }
 
     /**
      * Triggers a repaint of the window and panel.
      */
     public void repaint() {
         this.win.repaint();
     }
 
     /**
      * Main method: Creates a display and populates it with social and antisocial agents.
      * 
      * @param args expects one argument: the radius of the social agents
      * @throws InterruptedException if thread sleep is interrupted
      */
     public static void main(String[] args) throws InterruptedException {
         Landscape scape = new Landscape(500, 500); // Create 500x500 landscape
         Random gen = new Random();                // For generating random positions
         double radius = Double.parseDouble(args[0]); // Read radius from input
 
         // Add 100 social and 100 antisocial agents with varying radii
         for (int i = 0; i < 100; i++) {
             scape.addAgent(new SocialAgent(
                 gen.nextDouble() * scape.getWidth(),
                 gen.nextDouble() * scape.getHeight(),
                 (int) radius));
 
             scape.addAgent(new AntiSocialAgent(
                 gen.nextDouble() * scape.getWidth(),
                 gen.nextDouble() * scape.getHeight(),
                 2 * (int) radius));
         }
 
         LandscapeDisplay display = new LandscapeDisplay(scape); // Create and show display
 
         // Simulation loop that updates and repaints the scene
         while (true) {
             Thread.sleep(10); // Delay for animation pacing
             scape.updateAgents(); // Update agent positions and states
             display.repaint();    // Refresh screen with new positions
         }
     }
 }
 
