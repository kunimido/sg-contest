/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package com.isograd.exercise;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class IsoContest {
    public static void main(final String... args) throws Exception {
        new IsoContest(System.in, System.out).run();
    }

    private final InputStream in;

    private final PrintStream out;

    public IsoContest(final InputStream in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        final Scanner scanner = new Scanner(in);

        final int s = Integer.parseInt(scanner.nextLine());
        final int n = Integer.parseInt(scanner.nextLine());

        int countA = 0;
        int countB = 0;
        while (scanner.hasNextLine()) {
            final int[] steps = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            countA += steps[0];
            countB += steps[1];

            if (countA >= s && countB >= s) {
                out.print("NO WINNER");
                break;
            } else if (countA >= s) {
                out.print("A");
                break;
            } else if (countB >= s) {
                out.print("B");
                break;
            }
        }
    }
}