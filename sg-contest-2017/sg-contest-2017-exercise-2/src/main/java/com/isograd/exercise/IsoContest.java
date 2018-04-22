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

        String[] header = scanner.nextLine().split(" ");
        int dealer = 0;
        for (String h : header) {
            if (Arrays.asList("J", "Q", "D", "R").contains(h)) {
                dealer += 10;
            } else {
                dealer += Integer.parseInt(h);
            }
        }

        header = scanner.nextLine().split(" ");
        int me = 0;
        for (String h : header) {
            if (Arrays.asList("J", "Q", "D", "R").contains(h)) {
                me += 10;
            } else {
                me += Integer.parseInt(h);
            }
        }

        if (me == 21) {
            out.print("BLACK JACK");
        } else if (me < 21 && (me >= dealer || dealer > 21)) {
            out.print("WIN");
        } else {
            out.print("LOSE");
        }
    }
}