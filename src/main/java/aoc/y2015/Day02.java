package aoc.y2015;

import aoc.util.Input;

import java.util.List;

public class Day02 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 2);

        System.out.println(findTotalPaperNeeded(input));
        System.out.println(findTotalRibbonNeeded(input));
    }

    private static int findTotalRibbonNeeded(List<String> input) {
        int res = 0;
        for (String line : input) {
            String[] dimensions = line.split("x");

            int l = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            int h = Integer.parseInt(dimensions[2]);

            int per1 = 2 * (l + w);
            int per2 = 2 * (l + h);
            int per3 = 2 * (w + h);
            int minPer = Math.min(per1, Math.min(per2, per3));

            res += minPer + l * w * h;
        }

        return res;
    }

    private static int findTotalPaperNeeded(List<String> input) {
        int res = 0;
        for (String line : input) {
            String[] dimensions = line.split("x");

            int l = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            int h = Integer.parseInt(dimensions[2]);

            int area1 = l * w;
            int area2 = l * h;
            int area3 = w * h;
            int minArea = Math.min(area1, Math.min(area2, area3));

            res += 2 * (area1 + area2 + area3) + minArea;
        }

        return res;
    }
}
