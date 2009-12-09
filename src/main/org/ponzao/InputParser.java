package org.ponzao;

import java.util.Scanner;

public class InputParser {
    public InputParser() {

    }

    public String readInput() {
        final Scanner in = new Scanner(System.in);
        final StringBuilder sb = new StringBuilder();

        while (in.hasNextLine()) {
            sb.append(in.nextLine());
            sb.append('\n');
        }

        return sb.toString();
    }
}
