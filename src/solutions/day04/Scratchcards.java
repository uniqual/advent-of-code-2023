package solutions.day04;

import solutions.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scratchcards {

    public static void main(String[] args) {
        List<String> fileLines = InputReader.getAllFileLines("day04.txt");
        System.out.println(part1(fileLines));
        System.out.println(part2(fileLines));
    }

    private static int part1(List<String> fileLines) {
        int points = 0;
        for (var fileLine : fileLines) {
            List<Integer> winningNumbers = getWinningNumbers(fileLine);
            points = points + (int) Math.pow(2, winningNumbers.size() - 1);
        }
        return points;
    }

    private static int part2(List<String> fileLines) {
        Map<Integer, Integer> cardsCopiesMap = new HashMap<>();
        for (int card = 0; card < fileLines.size(); card++) {
            List<Integer> winningNumbers = getWinningNumbers(fileLines.get(card));
            int numberOfCopies = winningNumbers.size();
            cardsCopiesMap.merge(card, 1, Integer::sum);
            createCopies(cardsCopiesMap, card, numberOfCopies);
        }
        return cardsCopiesMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void createCopies(Map<Integer, Integer> cardsMap, int currentCard, int numberOfCopies) {
        for (int i = 0; i < cardsMap.get(currentCard); i++) {
            IntStream.range(currentCard + 1, numberOfCopies + (currentCard + 1))
                    .forEach(card -> cardsMap.merge(card, 1, Integer::sum));
        }
    }

    private static List<Integer> getWinningNumbers(String line) {
        List<String> cards = Arrays.stream(line.replaceAll("Card\\s+\\d+:\\s+", "").split("\\s+\\|\\s+")).toList();
        var winingNumbers = Arrays.stream(cards.get(0).split("\\s+")).map(Integer::valueOf).collect(Collectors.toList());
        var myNumbers = Arrays.stream(cards.get(1).split("\\s+")).map(Integer::valueOf).collect(Collectors.toList());
        winingNumbers.retainAll(myNumbers);
        return winingNumbers;
    }
}