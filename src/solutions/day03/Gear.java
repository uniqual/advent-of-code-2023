package solutions.day03;

import solutions.InputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gear {

    public static void main(String[] args) {
        List<String> fileLines = InputReader.getAllFileLines("day03.txt");
        System.out.println(part1(fileLines));
    }

    private static int part1(List<String> fileLines) {
        List<Line> lines = buildLines(fileLines);
        List<Integer> adjacentNumbers = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            List<Symbol> prevSymbols = getPrevLineSymbols(lines, i);
            Line line = lines.get(i);
            List<Symbol> nextSymbols = getNextLineSymbols(lines, i);
            adjacentNumbers.addAll(findAdjacentNumbers(line, prevSymbols, nextSymbols));
        }
        return adjacentNumbers.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Line> buildLines(List<String> fileLines) {
        List<Line> lines = new ArrayList<>();
        for (String fileLine : fileLines) {
            lines.add(new Line(fileLine));
        }
        return lines;
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

    private static List<Integer> findAdjacentNumbers(Line line, List<Symbol> prevSymbols, List<Symbol> nextSymbols) {
        List<Symbol> allSymbols = new ArrayList<>();
        allSymbols.addAll(line.getSymbols());
        allSymbols.addAll(prevSymbols);
        allSymbols.addAll(nextSymbols);

        List<Integer> integers = new ArrayList<>();

        for (var number : line.getNumbers()) {
            if (isAdjacentNumber(number, allSymbols)) {
                integers.add(number.number());
            }
        }
        return integers;
    }

    private static boolean isAdjacentNumber(Number number, List<Symbol> symbols) {
        var flag = false;
        for (var symbol : symbols) {
            if ((number.xPos() - 1) <= symbol.position() && (number.yPos() + 1) >= symbol.position()) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
