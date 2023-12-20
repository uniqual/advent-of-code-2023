package solutions.day03;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Line(String line) {

    private static final Pattern numberPattern = Pattern.compile("[0-9]+");
    private static final Pattern symbolPattern = Pattern.compile("[^\\.0-9]");

    public List<Number> getNumbers() {
        Matcher numberMatcher = numberPattern.matcher(line);
        return numberMatcher.results()
                .map(matchResult -> {
                    int number = Integer.parseInt(matchResult.group(0));
                    int xPos = matchResult.start();
                    int yPos = matchResult.end() - 1;
                    return new Number(number, xPos, yPos);
                }).toList();
    }

    public List<Symbol> getSymbols() {
        Matcher symbolMatcher = symbolPattern.matcher(line);
        return symbolMatcher.results()
                .map(matchResult -> {
                    char symbol = matchResult.group(0).charAt(0);
                    int position = matchResult.start();
                    return new Symbol(symbol, position);
                }).toList();
    }
}
