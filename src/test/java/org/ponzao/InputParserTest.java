package org.ponzao;

import java.io.BufferedInputStream;
import java.io.DataInputStream;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputParserTest {

    @Test
    public void testReadInput() {
        final InputParser inputParser = new InputParser();
        fail(inputParser.readInput(new DataInputStream(new BufferedInputStream(
                System.in))));
    }
}
