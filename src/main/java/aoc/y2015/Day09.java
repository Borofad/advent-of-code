package aoc.y2015;

import aoc.util.Input;

import java.util.*;

public class Day09 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 9);

        Map<String, Map<String, Integer>> adjMap = createAdjMap(input);
        System.out.println(findMinWeightToVisitAll(adjMap));

        Map<String, Map<String, Integer>> invAdjMap = new HashMap<>(adjMap);
        for (Map<String, Integer> map : invAdjMap.values()) {
            map.replaceAll((k, v) -> -map.get(k));
        }

        System.out.println(-findMinWeightToVisitAll(adjMap));
    }

    private static int findMinWeightToVisitAll(Map<String, Map<String, Integer>> adjMap) {
        int res = Integer.MAX_VALUE;
        for (String start : adjMap.keySet()) {
            res = Math.min(res, findMinWeightToVisitAll(adjMap, start));
        }

        return res;
    }

    private static int findMinWeightToVisitAll(Map<String, Map<String, Integer>> adjMap, String start) {
        Set<String> visited = new HashSet<>();
        visited.add(start);
        return dfs(adjMap, start, visited, 0);
    }

    private static int dfs(Map<String, Map<String, Integer>> adjMap, String curr, Set<String> visited, int currentDist) {
        if (visited.size() == adjMap.size()) {
            return currentDist;
        }

        int minDist = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : adjMap.get(curr).entrySet()) {
            String next = entry.getKey();
            if (!visited.contains(next)) {
                visited.add(next);
                minDist = Math.min(minDist, dfs(adjMap, next, visited, currentDist + entry.getValue()));
                visited.remove(next);
            }
        }

        return minDist;
    }

    private static Map<String, Map<String, Integer>> createAdjMap(List<String> input) {
        Map<String, Map<String, Integer>> adjMap = new HashMap<>();
        for (String str : input) {
            String[] tokens = str.split(" ");
            adjMap.computeIfAbsent(tokens[0], k -> new HashMap<>()).put(tokens[2], Integer.parseInt(tokens[4]));
            adjMap.computeIfAbsent(tokens[2], k -> new HashMap<>()).put(tokens[0], Integer.parseInt(tokens[4]));
        }

        return adjMap;
    }
}
