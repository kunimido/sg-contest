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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main(final String... args) throws Exception {
        new IsoContest(System.in, System.out).run();
    }

    private static class Graph {
        final Map<String, Node> nodes = new HashMap<>();

        Node getNode(final String label) {
            return nodes.get(label);
        }

        Set<Node> getNodes() {
            return new HashSet<>(nodes.values());
        }

        Node addNode(final String label) {
            return nodes.computeIfAbsent(label, key -> new Node(this, key));
        }
    }

    private static class Node {
        final Graph graph;

        final String label;

        final Map<Node, Double> neighbors = new HashMap<>();

        Node(final Graph graph, final String label) {
            this.graph = graph;
            this.label = label;
        }

        void addNeighbor(final Node node, final double distance) {
            neighbors.put(node, distance);
        }

        List<Node> getShortestPathTo(final Node target) {
            final Set<Node> unvisited = new TreeSet<>(Comparator.comparing(o -> o.label));
            unvisited.addAll(graph.getNodes());
            final Map<Node, Double> distances = new HashMap<>();
            unvisited.forEach(node -> distances.put(node, equals(node) ? 0.0 : Double.MAX_VALUE));
            final Map<Node, Node> previous = new HashMap<>();

            while (!unvisited.isEmpty()) {
                final Node current = unvisited.stream().min(Comparator.comparingDouble(distances::get)).get();
                unvisited.remove(current);
                current.neighbors.forEach((neighbor, distance) -> {
                    final double alt = distances.get(current) + distance;
                    if (alt < distances.get(neighbor)) {
                        distances.put(neighbor, alt);
                        previous.put(neighbor, current);
                    }
                });
            }

            final List<Node> result = new LinkedList<>();
            Node u = target;
            while (previous.get(u) != null) {
                result.add(0, u);
                u = previous.get(u);
            }
            result.add(0, u);

            return result;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Node node = (Node) o;
            return Objects.equals(label, node.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
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

        final int l = Integer.parseInt(scanner.nextLine());
        final String beginning = scanner.nextLine();
        final String end = scanner.nextLine();
        final int n = Integer.parseInt(scanner.nextLine());

        final Graph graph = new Graph();
        graph.addNode(beginning);
        graph.addNode(end);
        while (scanner.hasNextLine()) {
            graph.addNode(scanner.nextLine());
        }

        final Set<Node> nodes = graph.getNodes();
        nodes.forEach(n1 ->
                nodes.forEach(n2 -> {
                    int count = 0;
                    for (int i = 0; i < n2.label.length(); i++) {
                        if (n2.label.charAt(i) != n1.label.charAt(i)) {
                            count++;
                        }
                    }
                    if (count == 1) {
                        n1.addNeighbor(n2, 1.0);
                    }
                })
        );

        final List<Node> path = graph.getNode(beginning).getShortestPathTo(graph.getNode(end));
        if (path.size() < 2) {
            out.print("IMPOSSIBLE");
        } else {
            out.print(path.stream().map(node -> node.label).collect(Collectors.joining(" ")));
        }
    }
}