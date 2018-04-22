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

        final String[] header = scanner.nextLine().split(" ");
        final int n = Integer.parseInt(header[0]);
        final int k = Integer.parseInt(header[1]);
        final double r = Double.parseDouble(header[2]);
        final double r2 = r * r;

        final int[][] persons = new int[n][2];
        int p = 0;
        while (scanner.hasNextLine()) {
            persons[p++] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < persons.length; i++) {
            int count = 0;
            for (int j = 0; j < persons.length; j++) {
                if (i != j) {
                    final int dx = persons[j][0] - persons[i][0];
                    final int dy = persons[j][1] - persons[i][1];
                    if (dx * dx + dy * dy <= r2) {
                        count++;
                    }
                }
            }
            if (count >= k) {
                result.append(i + 1).append(' ');
            }
        }

        if (result.length() == 0) {
            out.print("No danger");
        } else {
            out.print(result.toString().trim());
        }
    }
}