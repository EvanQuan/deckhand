package com.github.evanquan.deckhand.io;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Deck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * Reads card information from a CSV and image files to generate a {@link Deck}
 * of {@link Card}s.
 *
 * @author Evan Quan
 */
public class DeckReader {

    private static final String ZERO_WIDTH_NO_BREAK_SPACE =
            Character.toString((char) 65279);

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

        for (File file : Objects.requireNonNull(files)) {
            if (isImage(file)) {
                images.put(stripExtension(file.getName()), file);
            }
        }
        return images;
    }

    private static final ArrayList<String> VALID_IMAGE_EXTENSIONS =
            new ArrayList<>(Arrays.asList("png", "jpg"));
    private static final String COMMA_DELIMITER = ",";
    private static final String DEFAULT_CARD_DESCRIPTION = "";
    private static final int DEFAULT_CARD_QUANTITY = 1;

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
     * @throws IOException if the CSV cannot be found.
     */
    private ArrayList<CardInfo> getCardInfo(String csvPath) throws IOException {

        ArrayList<CardInfo> cardInfo = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(csvPath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = sanitize(line);
                String[] values = line.split(COMMA_DELIMITER);
                String cardImageName =
                        values[CardInfoIndex.CARD_IMAGE_NAME.getValue()];
                String cardName =
                        values[CardInfoIndex.CARD_NAME.getValue()];
                String cardDescription =
                        values.length > CardInfoIndex.CARD_DESCRIPTION.getValue() ?
                                values[CardInfoIndex.CARD_DESCRIPTION.getValue()] :
                                DEFAULT_CARD_DESCRIPTION;
                int cardQuantity =
                        values.length > CardInfoIndex.CARD_QUANTITY.getValue() ?
                                Integer.parseInt(values[CardInfoIndex.CARD_QUANTITY.getValue()]) :
                                DEFAULT_CARD_QUANTITY;
                cardInfo.add(new CardInfo(cardImageName, cardName,
                        cardDescription, cardQuantity));
            }

        }

        return cardInfo;
    }

    /**
     * Sometimes CSVs contain zero width no-break spaces in them.
     * https://stackoverflow.com/questions/9691771/why-is-65279-appearing-in-my-html
     *
     * @param line read from CSV
     * @return sanitized line
     */
    private String sanitize(String line) {
        return line.replace(ZERO_WIDTH_NO_BREAK_SPACE, "");
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

        checkAllCardsHaveExistingImage(images, cardInfo);

        return buildDeck(images, cardInfo);
    }

    private Deck buildDeck(HashMap<String, File> images,
                           ArrayList<CardInfo> cardInfo) {
        Deck deck = new Deck();

        for (CardInfo info : cardInfo) {
            for (int i = 0; i < info.getQuantity(); i++) {
                deck.add(new Card(info.getCardName(), info.getCardDescription(),
                        images.get(info.getImageName())));
            }
        }

        return deck;
    }

    /**
     * Check if the card info gathered from the files is of a valid count in
     * order to generate card info.
     *
     * @param images       gathered from image files
     * @throws Exception ifl the images, names, and descriptions do not have
     * the correct counts in order to create a {@link Deck} of {@link Card}s.
     */
    private void checkAllCardsHaveExistingImage(HashMap<String, File> images,
                                                ArrayList<CardInfo> cardInfo) throws Exception {
        for (CardInfo info : cardInfo) {
            if (!images.containsKey(info.getImageName())) {
                throw new Exception(
                        "Card: " + info.getCardName() + ": \""
                                + info.getCardDescription()
                                + " Quantity: " + info.getQuantity()
                                + " does not have an existing image: \"" + info.getImageName() + "\""
                                + ". All existing images are: " + images.keySet());
            }
        }
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
            return name.substring(name.lastIndexOf(".") + 1);
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
        CARD_IMAGE_NAME(0),
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
