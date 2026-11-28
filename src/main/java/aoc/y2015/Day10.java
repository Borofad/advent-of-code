package aoc.y2015;

import aoc.util.Input;

import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 10);

        System.out.println(applyNStepsOfLookAndSay(input.get(0), 40).length());
        System.out.println(applyNStepsOfLookAndSay(input.get(0), 50).length());
    }

    private static String applyNStepsOfLookAndSay(String start, int count) {
        String res = start;
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < count; i++) {
            curr.setLength(0);
            int charCount = 1;
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    charCount++;
                } else {
                    curr.append(charCount).append(res.charAt(j - 1));
                    charCount = 1;
                }
            }

            curr.append(charCount).append(res.charAt(res.length() - 1));

            res = curr.toString();
        }

        return res;
    }

}
