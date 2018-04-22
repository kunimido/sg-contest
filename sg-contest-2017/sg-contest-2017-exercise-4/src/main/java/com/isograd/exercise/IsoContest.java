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
        final int n = Integer.parseInt(scanner.nextLine());
        final String cake = scanner.nextLine();
        int max = 0;
        for (int i = 0; i < n / 2; i++) {
            int count = 0;
            for (int j = i; j < i + n / 2; j++) {
                if (cake.charAt(j % n) == 'x') {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        out.print("" + max);
    }
}