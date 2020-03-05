import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Antony Holshouser
 */
public class MyMiniSearchEngineTest {
    private List<String> documents() {
        return new ArrayList<String>(
                Arrays.asList(
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "seattle rains hello abc world",
                        "sunday hello world fun"));
    }

    @Test
    public void testOneWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("seattle");

        assertEquals(1, result.size());

        assertEquals(Integer.valueOf(4), result.get(0));
    }

    @Test
    public void testTwoWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("hello world");

        assertEquals(2, result.size());
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(5);
        assertEquals(expected, result);
    }

    @Test
    public void testThreeWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "rains hello abc",
                "rains Hello abc",
        };

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            List<Integer> expected = new ArrayList<>();
            expected.add(4);
            assertEquals(expected, result);
        }
    }

    @Test
    public void testFourWord() {
        // homework
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "seattle rains hello abc",
                "sunday hello world fun",
        };

        List<Integer> result = engine.search(inputs[0]);
        assertEquals(1, result.size());
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        assertEquals(expected, result);

        result = engine.search(inputs[1]);
        assertEquals(1, result.size());
        expected = new ArrayList<>();
        expected.add(5);
        assertEquals(expected, result);
    }

    @Test
    public void testWordNotFound() {
        // homework
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        List<Integer> result = engine.search("worl");

        assertEquals(0, result.size());

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    /**
     * 2 things currently wrong:
     *   repeats don't get accounted for, like "world world hello"
     *   "sunday hello world fun" for some reason is messing up and I can't figure out why
     */
    public void testGetDocumentNames() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        List<Integer> result = engine.search("world");


        assertEquals(5, engine.getDocumentNames(result).size());
        System.out.println(engine.getDocumentNames(result));
    }
}