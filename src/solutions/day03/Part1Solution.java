package solutions.day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Part1Solution {

    public int part1(List<Line> lines) {
        List<Integer> adjacentNumbers = new ArrayList<>();
        collectAdjacentNumbers(lines, adjacentNumbers);
        return adjacentNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void collectAdjacentNumbers(List<Line> lines, List<Integer> adjacentNumbers) {
        for (Line line : lines) {
            List<Symbol> symbols = Stream.of(line.getSymbols(),
                            getPrevLineSymbols(lines, line.lineNumber()),
                            getNextLineSymbols(lines, line.lineNumber()))
                    .flatMap(Collection::stream)
                    .toList();
            for (var number : line.getNumbers()) {
                if (isAdjacentNumber(symbols, number)) {
                    adjacentNumbers.add(number.number());
                }
            }
        }
    }

    private List<Symbol> getPrevLineSymbols(List<Line> lines, int index) {
        if (index > 0) {
            index = index - 1;
            return lines.get(index).getSymbols();
        }
        return Collections.emptyList();
    }

    private List<Symbol> getNextLineSymbols(List<Line> lines, int index) {
        index = index + 1;
        if (index < lines.size()) {
            return lines.get(index).getSymbols();
        }
        return Collections.emptyList();
    }

    private boolean isAdjacentNumber(List<Symbol> symbols, Number number) {
        return symbols.stream()
                .anyMatch(symbol -> (number.xPos() - 1) <= symbol.position() && (number.yPos() + 1) >= symbol.position());
    }
}
