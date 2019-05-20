package com.github.evanquan.deckhand.gui;

import javafx.stage.Stage;

/**
 * Manages non-primary {@link Stage}s. These stages are created for generating
 * new cards, or creating warning windows. The primary stage does not need to be
 * managed as it is given through the argument in {@link Main} .start() and so
 * much be configured directly in start().
 *
 * @author Evan Quan
 */
public class StageManager {

    private static StageManager ourInstance = new StageManager();

    private Stage cardStage;
    private Stage warningStage;

    private StageManager() {
        cardStage = new Stage();


        warningStage = new Stage();
    }

    public static StageManager getInstance() {
        return ourInstance;
    }

    public Stage getStage(StageType type) {
        switch (type) {
            case CARD:
                return cardStage;
            case WARNING:
                return warningStage;
            case NEW:
            default:
                return new Stage();

        }
    }

    enum StageType {
        CARD,
        WARNING,
        NEW,
    }
}
