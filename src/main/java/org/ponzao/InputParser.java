import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class InputParser {
    public InputParser() {

    }
//
//    public String readInput(final DataInputStream input) {
//        final StringBuffer result = new StringBuffer();
//
//        try {
//            while (input.available() != 0) {
//                result.append((char)input.readChar());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return result.toString();
//    }
//

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
        final InputParser inputParser = new InputParser();
        System.out.println(inputParser.readInput());
    }
}
