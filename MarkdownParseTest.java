import static org.junit.Assert.*;


import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/*
    javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
    java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest
 */

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void parse() throws IOException {
        ArrayList<String>[] list = new ArrayList[8];
        list[0] = new ArrayList();
        list[1] = new ArrayList();
        list[2] = new ArrayList();
        list[3] = new ArrayList();
        list[4] = new ArrayList();
        list[5] = new ArrayList();
        list[6] = new ArrayList();
        list[7] = new ArrayList();
        list[0].add("http://something.com");
        list[0].add("http://somemorething.com");
        list[1].add("https://something.com");
        list[1].add("some-page.html");
        list[2].add("www.something.com");
        list[2].add("www.something.com");
        list[7].add("a link on the first line");

        for (int i = 0; i < 8; i++) {
            String file = load("markdown" + (i + 1) + ".md");
            assertEquals(list[i],MarkdownParse.getLinks(file));
        }
    }

    private String load(String words) throws IOException {
		Path fileName = Path.of(words);
	    String contents = Files.readString(fileName);
        return contents;
    }


    @Test
    public void snippet1Test() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("`google.com");
        list.add("google.com");
        list.add("ucsd.edu");
        assertEquals(list,MarkdownParse.getLinks(load("snippet1.md")));
    
    }
    @Test
    public void snippet2Test() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("a.com");
        list.add("a.com(())");
        list.add("example.com");
        assertEquals(list,MarkdownParse.getLinks(load("snippet2.md")));
    
    }
    @Test
    public void snippet3Test() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://ucsd-cse15l-w22.github.io/");
        assertEquals(list,MarkdownParse.getLinks(load("snippet3.md")));
    
    }
}

