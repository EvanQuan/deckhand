package com.github.evanquan.deckhand.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

class SceneManager {

    /**
     * The .fxml file is placed with resources directory as the root.
     */
    private static final String MAIN_SCENE_FILE = "/main_scene.fxml";
    private static SceneManager instance = new SceneManager();
    private static Scene startScene;
    private static Scene gameScene;
    private static Scene warningScene;
    private static Scene cardScene;

    private SceneManager() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(MAIN_SCENE_FILE));
            assert root != null;
            gameScene = new Scene(root);
//        gameScene = new Scene(FXMLLoader.load(getClass().getResource(MAIN_SCENE_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static SceneManager getInstance() {
        return instance;
    }

    Scene getScene(SceneType type) {
        switch (type) {
            case START:
            default:
                return startScene;
            case GAME:
                return gameScene;
            case WARNING:
                return warningScene;
            case CARD:
                return cardScene;
        }
    }

    public enum SceneType {
        START,
        GAME,
        WARNING,
        CARD,
    }
}
