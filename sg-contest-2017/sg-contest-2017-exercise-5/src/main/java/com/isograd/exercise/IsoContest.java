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

        final int blocks = Integer.parseInt(scanner.nextLine());

        int price = 0;
        int lowestPrice = 0;
        int bestProfit = 0;
        while (scanner.hasNextLine()) {
            price += Integer.parseInt(scanner.nextLine());
            if (price < lowestPrice) {
                lowestPrice = price;
            }
            int profit = price - lowestPrice;
            if (profit > bestProfit) {
                bestProfit = profit;
            }
        }

        out.print("" + bestProfit);
    }
}