package com.hs.slz.common.algorithm;

import java.util.*;
import java.util.stream.Collectors;


public class CheapestFlight {
    public static void main(String[] args) {
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int cheapestPrice = new CheapestFlight().findCheapestPrice(3, flights, 0, 2, 1);
        System.out.println(cheapestPrice);
    }

    public static class Flight {
        public int fromId;
        public int toId;
        public int cost;

        public Flight() {
        }

        public Flight(int fromId, int toId, int cost) {
            this.fromId = fromId;
            this.toId = toId;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Flight>> flightMap = Arrays.stream(flights)
                .map(e -> new Flight(e[0], e[1], e[2]))
                .collect(Collectors.groupingBy(e -> e.fromId));
        Map<Integer, Integer> id2Step = new HashMap();
        Map<Integer, Integer> id2Dis = new HashMap();
        Map<Integer, Integer> step2Cos = new HashMap<>();
        Queue<Integer> queue = new LinkedList();
        queue.add(src);
        id2Step.put(src, -1);
        id2Dis.put(src, 0);
        while (queue.size() > 0) {
            int curPos = queue.poll();
            List<Flight> flightsList = flightMap.get(curPos);
            if (flightsList == null) {
                continue;
            }
            for (Flight flight : flightsList) {
                int toId = flight.toId;
                queue.add(toId);
                int stepTemp = id2Step.get(curPos) + 1;
                id2Step.put(toId, stepTemp);
                int newCost = id2Dis.get(curPos) + flight.cost;
                id2Dis.put(toId, newCost);
                if (toId == dst) {
                    step2Cos.put(stepTemp, newCost);
                }
            }
        }
        return step2Cos.entrySet().stream().filter(e -> e.getKey() <= K)
                .map(Map.Entry::getValue).mapToInt(e -> e).min().orElse(-1);
    }
}
