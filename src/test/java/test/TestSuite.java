package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.io.DeckReaderTest;

/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeckReaderTest.class,
})
public class TestSuite {
}
