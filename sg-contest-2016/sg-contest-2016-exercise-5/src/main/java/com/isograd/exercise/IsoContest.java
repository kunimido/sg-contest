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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IsoContest {
    public static void main(final String... args) throws Exception {
        new IsoContest(System.in, System.out).run();
    }

    private static class Node {
        String name;

        int level = 0;

        List<Node> children = new ArrayList<>();

        Node(final String name) {
            this.name = name;
        }

        int visit() {
            if (level == 0) {
                if (children.isEmpty()) {
                    level = 1;
                } else {
                    level = 1 + children.stream().mapToInt(Node::visit).max().getAsInt();
                }
            }
            return level;
        }
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

        final Map<String, Node> nodes = new HashMap<>();
        while (scanner.hasNextLine()) {
            final String[] link = scanner.nextLine().split(" ");
            final Node parent = nodes.computeIfAbsent(link[0], name -> new Node(name));
            final Node child = nodes.computeIfAbsent(link[1], name -> new Node(name));
            parent.children.add(child);
        }

        out.print(nodes.values().stream().max(Comparator.comparingInt(Node::visit)).get().level);
    }
}