package solutions.day03;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Line(int lineNumber, String line) {

    public List<Number> getNumbers() {
        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher numberMatcher = numberPattern.matcher(line);
        return numberMatcher.results()
                .map(matchResult -> {
                    var number = Integer.parseInt(matchResult.group(0));
                    var xPos = matchResult.start();
                    var yPos = matchResult.end() - 1;
                    return new Number(number, xPos, yPos);
                }).toList();
    }

    public List<Symbol> getSymbols() {
        Pattern symbolPattern = Pattern.compile("[^\\.0-9]");
        Matcher symbolMatcher = symbolPattern.matcher(line);
        return symbolMatcher.results()
                .map(matchResult -> {
                    var symbol = matchResult.group(0);
                    var position = matchResult.start();
                    return new Symbol(symbol, position);
                }).toList();
    }

    public List<Symbol> getGearRatioSymbols() {
        Pattern symbolPattern = Pattern.compile("[\\*]");
        Matcher symbolMatcher = symbolPattern.matcher(line);
        return symbolMatcher.results()
                .map(matchResult -> {
                    var symbol = matchResult.group(0);
                    var position = matchResult.start();
                    return new Symbol(symbol, position);
                }).toList();
    }
}
