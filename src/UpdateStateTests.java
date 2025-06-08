/**
 * File Name:    UpdateStateTests.java
 * Author:       Ike Lage
 * Modified by:  Azeem Gbolahan
 * Last Modified: 03/04/2025
 * Description:  This class tests the behavior of SocialAgent and AntiSocialAgent
 *               when their updateState method is called under different conditions.
 *               Each test checks whether the agent moves based on the number of nearby agents.
 *
 * How to Run:   Compile using `javac UpdateStateTests.java` and run using `java -ea UpdateStateTests`
 *               Ensure assertions are enabled with the `-ea` flag.
 */

 public class UpdateStateTests {

    /**
     * Runs a series of tests on the updateState() method for both SocialAgent and AntiSocialAgent.
     * @return total score from all tests
     */
    public static double updateStateTests() {
        double score = 0.0;

        // Case 1: SocialAgent should not move when surrounded by 4 or more neighbors
        {
            Landscape landscape = new Landscape(100, 100);
            SocialAgent a1 = new SocialAgent(10, 10, 5);
            landscape.addAgent(a1);

            landscape.addAgent(new SocialAgent(12, 10, 5));
            landscape.addAgent(new SocialAgent(8, 10, 5));
            landscape.addAgent(new SocialAgent(10, 12, 5));
            landscape.addAgent(new SocialAgent(10, 8, 5));

            a1.updateState(landscape);

            if ((a1.getX() == 10) && (a1.getY() == 10) && (!a1.getMoved())) {
                System.out.println("1");
                score += 0.75;
            }
        }

        // Case 2: AntiSocialAgent should move when surrounded by 4 neighbors
        {
            Landscape landscape = new Landscape(100, 100);
            AntiSocialAgent a1 = new AntiSocialAgent(10, 10, 5);
            landscape.addAgent(a1);

            landscape.addAgent(new SocialAgent(12, 10, 5));
            landscape.addAgent(new SocialAgent(8, 10, 5));
            landscape.addAgent(new SocialAgent(10, 12, 5));
            landscape.addAgent(new SocialAgent(10, 8, 5));

            a1.updateState(landscape);
            System.out.println(a1.getX());
            System.out.println(a1.getY());
            System.out.println(a1.getMoved());
            System.out.println(landscape.getNeighbors(10, 10, 5).size());

            if ((a1.getX() != 10) && (a1.getY() != 10) && (a1.getMoved())) {
                System.out.println("2");
                score += 0.75;
            }
        }

        // Case 3: SocialAgent should move if fewer than 4 neighbors
        {
            Landscape landscape = new Landscape(100, 100);
            SocialAgent a1 = new SocialAgent(10, 10, 5);
            landscape.addAgent(a1);

            a1.updateState(landscape);

            if ((a1.getX() != 10) && (a1.getY() != 10) && (a1.getMoved())) {
                System.out.println("3");
                score += 0.75;
            }
        }

        // Case 4: AntiSocialAgent should NOT move if fewer than 2 neighbors
        {
            Landscape landscape = new Landscape(100, 100);
            AntiSocialAgent a1 = new AntiSocialAgent(10, 10, 5);
            landscape.addAgent(a1);

            a1.updateState(landscape);

            if ((a1.getX() == 10) && (a1.getY() == 10) && (!a1.getMoved())) {
                System.out.println("4");
                score += 0.75;
            }
        }

        return score;
    }

    /**
     * Main method to run the updateState tests.
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(updateStateTests());
    }
}

