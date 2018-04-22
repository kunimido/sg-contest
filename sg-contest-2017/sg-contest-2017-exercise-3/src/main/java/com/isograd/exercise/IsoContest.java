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
import java.util.Optional;
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

        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());

        int count = 0;
        int low = Integer.MAX_VALUE;
        int city = 0;
        while (scanner.hasNextLine()) {
            final String[] heights = scanner.nextLine().split(" ");
            final Optional<Integer> highest = Arrays.stream(heights).map(Integer::parseInt).max(Integer::compareTo);
            if (highest.get().intValue() < low) {
                low = highest.get().intValue();
                city = count;
            }
            count++;
        }
        out.print("" + city);
    }
}