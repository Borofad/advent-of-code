package aoc.y2015;

import aoc.util.Input;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Day03 {
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 3);

        System.out.println(countAtLeastOnePresent(input.get(0)));
        System.out.println(countAtLeastOnePresentWithRobo(input.get(0)));
    }

    private static int countAtLeastOnePresentWithRobo(String input) {
        Set<Point> points = new HashSet<>();
        Point curr = new Point(0, 0);
        points.add(new Point(curr.x, curr.y));
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < inputArr.length; i += 2) {
            char c = inputArr[i];
            switch (c) {
                case '<' -> curr.x--;
                case '^' -> curr.y--;
                case '>' -> curr.x++;
                case 'v' -> curr.y++;
            }

            points.add(new Point(curr.x, curr.y));
        }

        curr.x = 0;
        curr.y = 0;
        for (int i = 1; i < inputArr.length; i += 2) {
            char c = inputArr[i];
            switch (c) {
                case '<' -> curr.x--;
                case '^' -> curr.y--;
                case '>' -> curr.x++;
                case 'v' -> curr.y++;
            }

            points.add(new Point(curr.x, curr.y));
        }

        return points.size();
    }

    private static int countAtLeastOnePresent(String input) {
        Set<Point> points = new HashSet<>();
        Point curr = new Point(0, 0);
        points.add(new Point(curr.x, curr.y));
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<' -> curr.x--;
                case '^' -> curr.y--;
                case '>' -> curr.x++;
                case 'v' -> curr.y++;
            }

            points.add(new Point(curr.x, curr.y));
        }

        return points.size();
    }
}
