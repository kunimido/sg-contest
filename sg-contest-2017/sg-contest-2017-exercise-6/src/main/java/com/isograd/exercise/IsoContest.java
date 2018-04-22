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

        List<Node> children = new ArrayList<>();

        Node(final String name) {
            this.name = name;
        }

        List<String> visit() {
            return visit(null);
        }

        List<String> visit(final String parentPath) {
            final List<String> result;
            final String path = parentPath == null ? name : parentPath + "\\" + name;
            if (children.isEmpty()) {
                result = new ArrayList<>();
                result.add(path);
            } else {
                result = children.stream()
                        .map(node -> node.visit(path))
                        .reduce((l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        })
                        .get();
            }
            return result;
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

        final int count = Integer.parseInt(scanner.nextLine());

        final Map<String, Node> nodes = new HashMap<>();
        while (scanner.hasNextLine()) {
            final String[] link = scanner.nextLine().split(" ");
            final Node parent = nodes.computeIfAbsent(link[0], name -> new Node(name));
            final Node child = nodes.computeIfAbsent(link[1], name -> new Node(name));
            parent.children.add(child);
        }

        out.print(nodes.get("C:").visit().stream().max(Comparator.comparingInt(String::length)).get());
    }
}