package aoc.y2015;

import aoc.util.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day06 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 6);

        System.out.println(countLitAfterInstructions1(input));
        System.out.println(countLitAfterInstructions2(input));
    }

    private static int countLitAfterInstructions2(List<String> input) {
        int[][] grid = new int[1000][1000];

        for (String instruction : input) {
            String[] instructionArr = instruction.split("[ ,]");
            if (Objects.equals(instructionArr[1], "on")) {
                int sr = Integer.parseInt(instructionArr[2]);
                int sc = Integer.parseInt(instructionArr[3]);
                int er = Integer.parseInt(instructionArr[5]);
                int ec = Integer.parseInt(instructionArr[6]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        grid[row][col]++;
                    }
                }
            } else if (Objects.equals(instructionArr[1], "off")) {
                int sr = Integer.parseInt(instructionArr[2]);
                int sc = Integer.parseInt(instructionArr[3]);
                int er = Integer.parseInt(instructionArr[5]);
                int ec = Integer.parseInt(instructionArr[6]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        if (grid[row][col] > 0) {
                            grid[row][col]--;
                        }
                    }
                }
            } else {
                int sr = Integer.parseInt(instructionArr[1]);
                int sc = Integer.parseInt(instructionArr[2]);
                int er = Integer.parseInt(instructionArr[4]);
                int ec = Integer.parseInt(instructionArr[5]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        grid[row][col] += 2;
                    }
                }
            }
        }

        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                res += grid[row][col];
            }
        }

        return res;
    }

    private static int countLitAfterInstructions1(List<String> input) {
        boolean[][] grid = new boolean[1000][1000];

        for (String instruction : input) {
            String[] instructionArr = instruction.split("[ ,]");
            if (Objects.equals(instructionArr[1], "on")) {
                int sr = Integer.parseInt(instructionArr[2]);
                int sc = Integer.parseInt(instructionArr[3]);
                int er = Integer.parseInt(instructionArr[5]);
                int ec = Integer.parseInt(instructionArr[6]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        grid[row][col] = true;
                    }
                }
            } else if (Objects.equals(instructionArr[1], "off")) {
                int sr = Integer.parseInt(instructionArr[2]);
                int sc = Integer.parseInt(instructionArr[3]);
                int er = Integer.parseInt(instructionArr[5]);
                int ec = Integer.parseInt(instructionArr[6]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        grid[row][col] = false;
                    }
                }
            } else {
                int sr = Integer.parseInt(instructionArr[1]);
                int sc = Integer.parseInt(instructionArr[2]);
                int er = Integer.parseInt(instructionArr[4]);
                int ec = Integer.parseInt(instructionArr[5]);

                for (int row = sr; row <= er; row++) {
                    for (int col = sc; col <= ec; col++) {
                        grid[row][col] = !grid[row][col];
                    }
                }
            }
        }

        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col]) {
                    res++;
                }
            }
        }

        return res;
    }
}
