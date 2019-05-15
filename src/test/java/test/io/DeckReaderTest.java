package test.io;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Deck;
import com.github.evanquan.deckhand.io.DeckReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckReaderTest {

    private static final String testPath = "./src/test/java/test/";
    private static final String noImagePath = testPath + "/noimages";
    private static final String validPath = testPath + "/valid";
    private static final String descHasCommasPath = testPath + "/descHasCommas";
    private static final String validPathTrailing = validPath + "/";
    private static final String invalidPath = "";
    private static final String csvName = "test.csv";

    private DeckReader reader;
    private Deck deck;

    @Before
    public void setUp() {
        reader = DeckReader.getInstance();
    }

    @Test(expected = Exception.class)
    public void getDeck_invalidPath_invalidCSV_throwException() throws Exception {
        deck = reader.getDeck(invalidPath, invalidPath);
    }

    @Test(expected = Exception.class)
    public void getDeck_validPath_invalidCSV_throwException() throws Exception {
        deck = reader.getDeck(validPath, invalidPath);
    }

    @Test(expected = Exception.class)
    public void getDeck_noImages() throws Exception {
        deck = reader.getDeck(noImagePath, csvName);
    }

    @Test
    public void getDeck_valid_success() throws Exception {
        deck = reader.getDeck(validPath, csvName);
        deckEqualsValidDeck(deck);
    }

    @Test
    public void getDeck_descHasCommas_success() throws Exception {
        deck = reader.getDeck(descHasCommasPath, csvName);
        deckEqualsDescHasCommasDeck(deck);
    }

    @Test
    public void getDeck_validTrailing_success() throws Exception {
        deck = reader.getDeck(validPathTrailing, csvName);
        deckEqualsValidDeck(deck);
    }

    private void deckEqualsValidDeck(Deck deck) {
        assertEquals(5, deck.size());
        Card a1 = deck.drawFromTop();
        Card a2 = deck.drawFromTop();
        Card a3 = deck.drawFromTop();
        Card b = deck.drawFromTop();
        Card c = deck.drawFromTop();

        assertEquals("1.png", a1.getImage().getName());
        assertEquals("1.png", a2.getImage().getName());
        assertEquals("1.png", a3.getImage().getName());
        assertEquals("2.png", b.getImage().getName());
        assertEquals("3.png", c.getImage().getName());

        assertEquals("name a", a1.getName());
        assertEquals("name a", a2.getName());
        assertEquals("name a", a3.getName());
        assertEquals("name b", b.getName());
        assertEquals("name c", c.getName());

        assertEquals("desc a", a1.getDescription());
        assertEquals("desc a", a2.getDescription());
        assertEquals("desc a", a3.getDescription());
        assertEquals("desc b", b.getDescription());
        assertEquals("desc c", c.getDescription());
    }

    private void deckEqualsDescHasCommasDeck(Deck deck) {
        assertEquals(5, deck.size());
        Card a1 = deck.drawFromTop();
        Card a2 = deck.drawFromTop();
        Card a3 = deck.drawFromTop();
        Card b = deck.drawFromTop();
        Card c = deck.drawFromTop();

        assertEquals("1.png", a1.getImage().getName());
        assertEquals("1.png", a2.getImage().getName());
        assertEquals("1.png", a3.getImage().getName());
        assertEquals("2.png", b.getImage().getName());
        assertEquals("3.png", c.getImage().getName());

        assertEquals("name a", a1.getName());
        assertEquals("name a", a2.getName());
        assertEquals("name a", a3.getName());
        assertEquals("name b", b.getName());
        assertEquals("name c", c.getName());

        assertEquals("desc, a", a1.getDescription());
        assertEquals("desc, a", a2.getDescription());
        assertEquals("desc, a", a3.getDescription());
        assertEquals("desc,b", b.getDescription());
        assertEquals("desc ,c", c.getDescription());
    }

}
