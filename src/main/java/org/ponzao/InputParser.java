package org.ponzao;

import java.util.Scanner;

/**
 * Used to read input from the standard input. Not unit tested due to System not
 * being mockable.
 * 
 * @author vesa
 * 
 */
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
