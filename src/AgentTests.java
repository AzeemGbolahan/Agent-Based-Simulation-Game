/*
 * File Name:    AgentTests.java
 * Authors:      Ike Lage
 * Last Modified: 03/04/2025
 *
 * Description:
 * This file contains unit tests for both SocialAgent and AntiSocialAgent classes.
 * It verifies correct initialization, getter/setter functionality, and internal state handling.
 *
 * How to Run:   java -ea AgentTests
 */

 public class AgentTests {

    /**
     * Executes a suite of tests to validate the behavior of SocialAgent and AntiSocialAgent.
     * 
     * @return the total score based on passed test cases (1 point per case)
     */
    public static double agentTests() {
        double score = 0.0; // Track total score

        // Test Case 1: Constructor and getters
        {
            SocialAgent sAgent = new SocialAgent(5.0, 10.0, 3);    // Create SocialAgent
            AntiSocialAgent aAgent = new AntiSocialAgent(7.0, 14.0, 4);  // Create AntiSocialAgent

            // Check each property is initialized correctly
            assert sAgent.getX() == 5.0 : "SocialAgent X position incorrect";
            assert sAgent.getY() == 10.0 : "SocialAgent Y position incorrect";
            assert sAgent.getRadius() == 3 : "SocialAgent Radius incorrect";

            assert aAgent.getX() == 7.0 : "AntiSocialAgent X position incorrect";
            assert aAgent.getY() == 14.0 : "AntiSocialAgent Y position incorrect";
            assert aAgent.getRadius() == 4 : "AntiSocialAgent Radius incorrect";

            score += 1; // Award point for successful test
        }

        // Test Case 2: getX()
        {
            SocialAgent sAgent = new SocialAgent(12.0, 8.0, 2);  // Create SocialAgent
            AntiSocialAgent aAgent = new AntiSocialAgent(18.0, 22.0, 5); // Create AntiSocialAgent

            // Verify getX() returns correct values
            assert sAgent.getX() == 12.0 : "getX() failed for SocialAgent";
            assert aAgent.getX() == 18.0 : "getX() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 3: getY()
        {
            SocialAgent sAgent = new SocialAgent(9.0, 15.0, 4);
            AntiSocialAgent aAgent = new AntiSocialAgent(5.0, 19.0, 3);

            assert sAgent.getY() == 15.0 : "getY() failed for SocialAgent";
            assert aAgent.getY() == 19.0 : "getY() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 4: getRadius()
        {
            SocialAgent sAgent = new SocialAgent(2.0, 3.0, 7);
            AntiSocialAgent aAgent = new AntiSocialAgent(6.0, 5.0, 9);

            assert sAgent.getRadius() == 7 : "getRadius() failed for SocialAgent";
            assert aAgent.getRadius() == 9 : "getRadius() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 5: setX()
        {
            SocialAgent sAgent = new SocialAgent(0.0, 0.0, 2);
            AntiSocialAgent aAgent = new AntiSocialAgent(0.0, 0.0, 2);

            sAgent.setX(10.5); // Update X value
            aAgent.setX(20.5);

            assert sAgent.getX() == 10.5 : "setX() failed for SocialAgent";
            assert aAgent.getX() == 20.5 : "setX() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 6: setY()
        {
            SocialAgent sAgent = new SocialAgent(0.0, 0.0, 2);
            AntiSocialAgent aAgent = new AntiSocialAgent(0.0, 0.0, 2);

            sAgent.setY(15.3); // Update Y value
            aAgent.setY(25.7);

            assert sAgent.getY() == 15.3 : "setY() failed for SocialAgent";
            assert aAgent.getY() == 25.7 : "setY() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 7: setRadius()
        {
            SocialAgent sAgent = new SocialAgent(0.0, 0.0, 2);
            AntiSocialAgent aAgent = new AntiSocialAgent(0.0, 0.0, 2);

            sAgent.setRadius(8); // Update radius
            aAgent.setRadius(12);

            assert sAgent.getRadius() == 8 : "setRadius() failed for SocialAgent";
            assert aAgent.getRadius() == 12 : "setRadius() failed for AntiSocialAgent";

            score += 1;
        }

        // Test Case 8: getMoved() default behavior
        {
            SocialAgent sAgent = new SocialAgent(0.0, 0.0, 2);
            AntiSocialAgent aAgent = new AntiSocialAgent(0.0, 0.0, 2);

            // Both agents should not have moved initially
            assert !sAgent.getMoved() : "getMoved() failed for SocialAgent";
            assert !aAgent.getMoved() : "getMoved() failed for AntiSocialAgent";

            score += 1;
        }

        return score;
    }

    /**
     * Main method to run all test cases and print the result.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println("Total Score: " + agentTests() + "/8");
    }
}
