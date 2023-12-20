package solutions.day03;

import solutions.InputReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Gear {

    public static void main(String[] args) {
        List<String> fileLines = InputReader.getAllFileLines("day03.txt");
        System.out.println(part1(fileLines));
    }

    private static int part1(List<String> fileLines) {
        List<Line> lines = buildLines(fileLines);
        List<Integer> adjacentNumbers = new ArrayList<>();
        collectAdjacentNumbers(lines, adjacentNumbers);
        return adjacentNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<Line> buildLines(List<String> fileLines) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < fileLines.size(); i++) {
            lines.add(new Line(i, fileLines.get(i)));
        }
        return lines;
    }

    private static void collectAdjacentNumbers(List<Line> lines, List<Integer> adjacentNumbers) {
        for (Line line : lines) {
            List<Symbol> allSymbols = Stream.of(line.getSymbols(),
                            getPrevLineSymbols(lines, line.lineNumber()),
                            getNextLineSymbols(lines, line.lineNumber()))
                    .flatMap(Collection::stream)
                    .toList();

            for (var number : line.getNumbers()) {
                if (isAdjacentNumber(allSymbols, number)) {
                    adjacentNumbers.add(number.number());
                }
            }
        }
    }

    private static List<Symbol> getPrevLineSymbols(List<Line> lines, int index) {
        if (index > 0) {
            index = index - 1;
            return lines.get(index).getSymbols();
        }
        return Collections.emptyList();
    }

    private static List<Symbol> getNextLineSymbols(List<Line> lines, int index) {
        index = index + 1;
        if (index < lines.size()) {
            return lines.get(index).getSymbols();
        }
        return Collections.emptyList();
    }

    private static boolean isAdjacentNumber(List<Symbol> symbols, Number number) {
       return symbols.stream()
                .anyMatch(symbol -> (number.xPos() - 1) <= symbol.position() && (number.yPos() + 1) >= symbol.position());
    }
}
