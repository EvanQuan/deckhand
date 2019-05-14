package com.github.evanquan.deckhand.io;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Deck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Reads card information from a CSV and image files to generate a {@link Deck}
 * of {@link Card}s.
 *
 * @author Evan Quan
 */
public class DeckReader {

    /**
     * Get images from image files
     *
     * @param directoryToSearch for images
     * @return map of all image names with their corresponding image in the
     * specified directory
     */
    private HashMap<String, File> getImages(String directoryToSearch) {
        HashMap<String, File> images = new HashMap<>();
        File imageDirectory = new File(directoryToSearch);

        File[] files = imageDirectory.listFiles();

        assert files != null;

        for (File file : files) {
            if (isImage(file)) {
                images.put(stripExtension(file.getName()), file);
            }
        }
        return images;
    }

    private static final ArrayList<String> VALID_IMAGE_EXTENSIONS =
            new ArrayList<>(Arrays.asList("png", "jpg"));

    private static DeckReader instance = new DeckReader();

    private DeckReader() {
    }

    public static DeckReader getInstance() {
        return instance;
    }

    /**
     * Get card info from CSV.
     * @param csvPath      for card info
     * @return csv info
     */
    private ArrayList<CardInfo> getCardInfo(String csvPath) {

        ArrayList<CardInfo> cardInfo = new ArrayList<>();

        return cardInfo;
    }

    /**
     * @param directoryToSearch for images and csv
     * @param csvName containing card info
     * @return a {@link Deck} of {@link Card}s gathered from the image files and
     * CSV values.
     * @throws Exception if the information from the files is not configured
     *                   correctly.
     */
    public Deck getDeck(String directoryToSearch,
                        String csvName) throws Exception {
        HashMap<String, File> images = getImages(directoryToSearch);
        ArrayList<CardInfo> cardInfo =
                getCardInfo(getCSVPath(directoryToSearch, csvName));

        if (cardInfoIsValid(images, cardInfo)) {
            return buildDeck(images, cardInfo);
        }

        throw new Exception("Incorrect image configuration.");

    }

    private Deck buildDeck(HashMap<String, File> images,
                           ArrayList<CardInfo> cardInfo) {
        Deck deck = new Deck();

        for (CardInfo info : cardInfo) {
            deck.add(new Card(info.getCardName(), info.getCardDescription(),
                    images.get(info.getCardName())));
        }

        return deck;
    }

    /**
     * Check if the card info gathered from the files is of a valid count in
     * order to generate card info.
     *
     * @param images       gathered from image files
     * @return true if the images, names, and descriptions have the correct
     * counts in order to create a {@link Deck} of {@link Card}s.
     */
    private boolean cardInfoIsValid(HashMap<String, File> images,
                                    ArrayList<CardInfo> cardInfo) {
        for (CardInfo info : cardInfo) {
            if (!images.containsKey(info.getCardName())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param file to get the extension from
     * @return the file extension of the file, or empty string if there is no
     * file extension.
     */
    private String getExtension(File file) {
        try {
            String name = file.getName();
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
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

    /**
     * @param name of file
     * @return the name without the file extension
     */
    private String stripExtension(String name) {
        try {
            return name.substring(0, name.lastIndexOf("."));
        } catch (Exception e) {
            return name;
        }
    }

    /**
     * @param directoryToSearch of CSV
     * @param csvName           of the CSV to read
     * @return the path of the csvFile
     */
    private String getCSVPath(String directoryToSearch, String csvName) {
        return directoryToSearch
                + (directoryToSearch.endsWith("/") ? "" : "/")
                + csvName;
    }

    /**
     * Index values from CSV
     */
    private enum CardInfoIndex {
        CARD_FILE_NAME(0),
        CARD_NAME(1),
        CARD_DESCRIPTION(2),
        CARD_QUANTITY(3),
        ;

        private final int value;

        CardInfoIndex(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }


}
