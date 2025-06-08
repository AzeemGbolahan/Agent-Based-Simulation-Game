/**
 * File Name:    LandscapeTests.java
 * Author:       Ike Lage
 * Last Modified: 03/04/2025
 * Description:  This class performs unit tests on the Landscape class,
 *               including verification of the constructor, getWidth(), and getHeight().
 *
 * How to Run:   Compile with `javac LandscapeTests.java` and run using `java -ea LandscapeTests`
 *               Ensure assertions are enabled using the `-ea` (enable assertions) flag.
 */

 public class LandscapeTests {

    /**
     * Runs a series of unit tests on the Landscape class.
     *
     * @return the score earned from passing tests
     */
    public static double landscapeTests() {
        double score = 0.;

        // case 1: testing Landscape(int, int)
        {
            // setup: Create a landscape with dimensions 100x200
            Landscape landscape = new Landscape(100, 200);

            // verify: Check if the width and height are set correctly
            if (landscape.getWidth() == 100 && landscape.getHeight() == 200) {
                System.out.println("1");
                score += 1.0;
            } else {
                System.out.println("Failed Case 1: Landscape constructor");
            }
        }

        // case 2: testing getWidth()
        {
            // setup: Create a landscape with dimensions 300x500
            Landscape landscape = new Landscape(300, 500);

            // verify: Check if getWidth() returns the correct width (300)
            if (landscape.getWidth() == 300) {
                System.out.println("2");
                score += 1.0;
            } else {
                System.out.println("Failed Case 2: getWidth()");
            }
        }

        // case 3: testing getHeight()
        {
            // setup: Create a landscape with dimensions 400x600
            Landscape landscape = new Landscape(400, 600);

            // verify: Check if getHeight() returns the correct height (600)
            if (landscape.getHeight() == 600) {
                System.out.println("3");
                score += 1.0;
            } else {
                System.out.println("Failed Case 3: getHeight()");
            }
        }

        return score;
    }

    /**
     * Main method that triggers the landscape test cases.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(landscapeTests());
    }
}

