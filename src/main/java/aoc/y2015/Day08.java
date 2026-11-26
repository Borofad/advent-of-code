package aoc.y2015;

import aoc.util.Input;

import java.util.List;

public class Day08 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 8);

        System.out.println(countCharactersInCode(input) - countCharactersInMemory(input));
        System.out.println(countCharactersOfEncodedInCode(input) - countCharactersInCode(input));
    }

    private static int countCharactersOfEncodedInCode(List<String> input) {
        int res = 0;
        for (String str : input) {
            res += countCharactersOfEncodedInCode(str);
        }

        return res;
    }

    private static int countCharactersOfEncodedInCode(String str) {
        // count "\" at the start and \"" at the end
        int res = 6;
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '\\') {
                // \\ or \"
                if (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '\"') {
                    i++;
                    res += 4;
                    // \xFF case
                } else {
                    i += 3;
                    res += 5;
                }

                continue;
            }

            res++;
        }

        return res;
    }

    private static int countCharactersInMemory(List<String> input) {
        int res = 0;
        for (String str : input) {
            res += countCharactersInMemory(str);
        }

        return res;
    }

    private static int countCharactersInMemory(String str) {
        int res = 0;
        // skip first and last double quotes
        for (int i = 1; i < str.length() - 1; i++) {
            res++;

            if (str.charAt(i) == '\\') {
                // \\ or \"
                if (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '\"') {
                    i++;
                // \xFF case
                } else {
                    i += 3;
                }
            }
        }

        return res;
    }

    private static int countCharactersInCode(List<String> input) {
        int res = 0;
        for (String str : input) {
            res += str.length();
        }

        return res;
    }
}
