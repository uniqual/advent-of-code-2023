package solutions.day03;

import solutions.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static void main(String[] args) {
        List<String> fileLines = InputReader.getAllFileLines("day03.txt");
        List<Line> lines = buildLines(fileLines);
        System.out.println(new Part1Solution().part1(lines));
        System.out.println(new Part2Solution().part2(lines));
    }

    private static List<Line> buildLines(List<String> fileLines) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < fileLines.size(); i++) {
            lines.add(new Line(i, fileLines.get(i)));
        }
        return lines;
    }
}
