package com.github.evanquan.deckhand.io;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Deck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Reads card information from a CSV and image files to generate a {@link Deck}
 * of {@link Card}s.
 *
 * @author Evan Quan
 */
public class DeckReader {

    private static final String DIRECTORY_TO_SEARCH = ".";

    private static final ArrayList<String> VALID_IMAGE_EXTENSIONS =
            new ArrayList<>(Arrays.asList("png", "jpg"));

    private static DeckReader instance = new DeckReader();

    private DeckReader() {
    }

    public static DeckReader getInstance() {
        return instance;
    }

    private ArrayList<File> getImages() {
        ArrayList<File> images = new ArrayList<>();
        File imageDirectory = new File(DeckReader.DIRECTORY_TO_SEARCH);

        File[] files = imageDirectory.listFiles();

        assert files != null;

        for (File file : files) {
            if (isImage(file)) {
                images.add(file);
            }
        }
        return images;
    }

    /**
     * @param names        of cards
     * @param descriptions of cards
     */
    private void getCardInfo(ArrayList<String> names,
                             ArrayList<String> descriptions) {

    }

    /**
     * @return a {@link Deck} of {@link Card}s gathered from the image files and
     * CSV values.
     * @throws Exception if the information from the files is not configured
     *                   correctly.
     */
    private Deck getDeck() throws Exception {
        ArrayList<File> images = getImages();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        getCardInfo(names, descriptions);

        if (hasCorrectCount(images, names, descriptions)) {
            Deck deck = new Deck();
            for (int i = 0; i < names.size(); i++) {
                deck.add(new Card(names.get(i), descriptions.get(i), images.get(i)));
            }
            return deck;
        }

        throw new Exception("Incorrect image configuration.");

    }

    /**
     * Check if the card info gathered from the files is of a valid count in
     * order to generate card info.
     *
     * @param images       gathered from image files
     * @param names        gathered from the CSV
     * @param descriptions gathered from the CSV
     * @return true if the images, names, and descriptions have the correct
     * counts in order to create a {@link Deck} of {@link Card}s.
     */
    private boolean hasCorrectCount(ArrayList<File> images,
                                    ArrayList<String> names,
                                    ArrayList<String> descriptions) {
        return (images.size() >= names.size())
                && (names.size() == descriptions.size());
    }

    /**
     * @param file to check if it is an image
     * @return true if the file has the file file extension to count as an
     * image.
     */
    private boolean isImage(File file) {
        return file.isFile()
                && VALID_IMAGE_EXTENSIONS.contains(getExtension(file).toLowerCase());
    }

    private String getExtension(File file) {
        String extension = "";
        try {
            String name = file.getName();
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }


}
