package com.github.evanquan.deckhand.gui;

public class StageManager {

    private static StageManager ourInstance = new StageManager();

    private StageManager() {

    }

    public static StageManager getInstance() {
        return ourInstance;
    }

    enum StageType {
        MAIN,
        CARD,
        NEW,
    }

//    public Stage getStage(StageType type) {
//        switch (type) {
//            case MAIN:
//
//        }
//    }
}
