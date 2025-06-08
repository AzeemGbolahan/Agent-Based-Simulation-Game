/**
 * File Name:    UpdateStateTests.java
 * Authors:       Azeem Gbolahan
 * Description:  Unit tests for updateState logic in SocialAgent and AntiSocialAgent classes.
 *               Verifies that agents move or stay based on their neighbors' proximity according
 *               to updated movement logic from the SocialAgent and AntiSocialAgent classes.
 *
 * How to Run:   Compile and run with assertions enabled:
 *               java -ea UpdateStateTests
 */

 public class UpdateStateTests {

    public static double updateStateTests() {
        double score = 0.0;

        // Case 1: SocialAgent with many neighbors should move toward center
        {
            Landscape landscape = new Landscape(100, 100);
            SocialAgent a1 = new SocialAgent(10, 10, 5);
            landscape.addAgent(a1);

            // Add 4 neighbors within radius 5
            landscape.addAgent(new SocialAgent(12, 10, 5));
            landscape.addAgent(new SocialAgent(8, 10, 5));
            landscape.addAgent(new SocialAgent(10, 12, 5));
            landscape.addAgent(new SocialAgent(10, 8, 5));

            // Update movement
            a1.updateState(landscape);

            // Validate movement occurred
            if ((a1.getX() != 10) || (a1.getY() != 10) || (a1.getMoved())) {
                System.out.println("1");
                score += 0.75;
            }
        }

        // Case 2: SocialAgent with few neighbors should not move
        {
            Landscape landscape = new Landscape(100, 100);
            SocialAgent a1 = new SocialAgent(10, 10, 5);
            landscape.addAgent(a1);
            landscape.addAgent(new SocialAgent(12, 10, 5));

            a1.updateState(landscape);

            // Validate no movement
            if ((a1.getX() == 10) && (a1.getY() == 10) && !a1.getMoved()) {
                System.out.println("2");
                score += 0.75;
            }
        }

        // Case 3: AntiSocialAgent with many neighbors should move away from closest
        {
            Landscape landscape = new Landscape(100, 100);
            AntiSocialAgent a1 = new AntiSocialAgent(10, 10, 5);
            landscape.addAgent(a1);
            landscape.addAgent(new SocialAgent(12, 10, 5));
            landscape.addAgent(new SocialAgent(8, 10, 5));
            landscape.addAgent(new SocialAgent(10, 12, 5));
            landscape.addAgent(new SocialAgent(10, 8, 5));

            a1.updateState(landscape);

            // Validate movement away
            if ((a1.getX() != 10) || (a1.getY() != 10) || a1.getMoved()) {
                System.out.println("3");
                score += 0.75;
            }
        }

        // Case 4: AntiSocialAgent with few neighbors should not move
        {
            Landscape landscape = new Landscape(100, 100);
            AntiSocialAgent a1 = new AntiSocialAgent(10, 10, 5);
            landscape.addAgent(a1);
            landscape.addAgent(new SocialAgent(12, 10, 5));

            a1.updateState(landscape);

            // Validate no movement
            if ((a1.getX() == 10) && (a1.getY() == 10) && !a1.getMoved()) {
                System.out.println("4");
                score += 0.75;
            }
        }

        // Case 5: AntiSocialAgent moves away from cluster
        {
            Landscape landscape = new Landscape(100, 100);
            AntiSocialAgent a1 = new AntiSocialAgent(10, 10, 5);
            landscape.addAgent(a1);
            landscape.addAgent(new SocialAgent(12, 10, 5));
            landscape.addAgent(new SocialAgent(8, 10, 5));
            landscape.addAgent(new SocialAgent(10, 12, 5));
            landscape.addAgent(new SocialAgent(10, 8, 5));

            a1.updateState(landscape);

            // Ensure agent moved
            if ((a1.getX() != 10) || (a1.getY() != 10) || a1.getMoved()) {
                System.out.println("5");
                score += 1.0;
            }
        }

        return score;
    }

    public static void main(String[] args) {
        System.out.println(updateStateTests());
    }
}
