package pairmatching;

import pairmatching.controller.MatchingController;

public class Application {
    public static void main(String[] args) {
        MatchingController matchingController = new MatchingController();
        matchingController.run();
    }
}
