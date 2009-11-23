package org.ponzao;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class InputParser {
    public InputParser() {

    }

    public String readInput(final DataInputStream input) {
        final StringBuffer result = new StringBuffer();

        try {
            while (input.available() != 0) {
                result.append(input.readChar());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    public static void main(String args[]) {
        final InputParser inputParser = new InputParser();
        System.out.println(inputParser.readInput(new DataInputStream(new BufferedInputStream(
                System.in))));
    }
}
