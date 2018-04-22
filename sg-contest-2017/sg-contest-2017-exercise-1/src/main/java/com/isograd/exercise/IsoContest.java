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
        final String header = scanner.nextLine();
        final int n = Integer.parseInt(header);

        int count = 0;
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            final int v = Integer.parseInt(line);
            count += v;
        }

        final StringBuilder result = new StringBuilder();
        result.append(count);

        if (result.length() > 0) {
            out.print(result.toString().trim());
        } else {
            out.print("");
        }
    }
}