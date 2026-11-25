package aoc.y2015;

import aoc.util.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 {
    public static void main(String[] args) {
        Map<String, String[]> rules = parseRules(Input.lines(2015, 7));

        int part1 = evaluate("a", rules, new HashMap<>());
        System.out.println("Part 1: " + part1);

        Map<String, Integer> part2Cache = new HashMap<>();
        part2Cache.put("b", part1);
        int part2 = evaluate("a", rules, part2Cache);
        System.out.println("Part 2: " + part2);
    }

    private static Map<String, String[]> parseRules(List<String> lines) {
        Map<String, String[]> rules = new HashMap<>(lines.size());
        for (String line : lines) {
            String[] parts = line.split(" -> ");
            rules.put(parts[1], parts[0].split(" "));
        }
        return rules;
    }

    private static int evaluate(String token, Map<String, String[]> rules, Map<String, Integer> cache) {
        if (Character.isDigit(token.charAt(0))) {
            return Integer.parseInt(token);
        }

        Integer cached = cache.get(token);
        if (cached != null) {
            return cached;
        }

        String[] expr = rules.get(token);
        if (expr == null) {
            throw new IllegalArgumentException("Unknown wire: " + token);
        }

        int rawResult = switch (expr.length) {
            case 1 -> evaluate(expr[0], rules, cache);
            case 2 -> ~evaluate(expr[1], rules, cache);
            case 3 -> {
                int left = evaluate(expr[0], rules, cache);
                int right = evaluate(expr[2], rules, cache);
                yield switch (expr[1]) {
                    case "AND"    -> left & right;
                    case "OR"     -> left | right;
                    case "LSHIFT" -> left << right;
                    case "RSHIFT" -> left >>> right;
                    default -> throw new IllegalArgumentException("Unknown op: " + expr[1]);
                };
            }
            default -> throw new IllegalArgumentException("Invalid expression for wire: " + token);
        };

        int result = rawResult & 0xFFFF;
        cache.put(token, result);
        return result;
    }
}