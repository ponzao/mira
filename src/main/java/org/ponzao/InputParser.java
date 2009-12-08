package org.ponzao;

import java.util.Scanner;

public class InputParser {
    public InputParser() {

    }

    public String readInput() {
        final Scanner in = new Scanner(System.in);
        final StringBuffer result = new StringBuffer();

        while (in.hasNextLine()) {
            result.append(in.nextLine());
            result.append('\n');
        }

        return result.toString();
    }

    public static void main(String args[]) {
        System.out.println(new InputParser().readInput());
    }
}
