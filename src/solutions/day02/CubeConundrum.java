package solutions.day02;

import solutions.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubeConundrum {

    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";

    private static final Map<String, Integer> COLOR_NUMBER_LIMIT_MAP = Map.of(
            RED, 12,
            GREEN, 13,
            BLUE, 14
    );

    public static void main(String[] args) {
        var inputs = InputReader.getAllFileLines("day02.txt");
        System.out.println(part1SumAllPossibleGameIds(inputs));
        System.out.println(part2PowerOfGameHighestSet(inputs));
    }

    private static int part1SumAllPossibleGameIds(List<String> inputs) {
        int sum = 0;
        for (var input : inputs) {
            var gameSetArray = input.split(":");
            var colorNumberMap = parseSets(gameSetArray[1]);
            if (isGamePossible(colorNumberMap)) {
                String gameId = gameSetArray[0].replaceAll("\\D", "");
                sum += Integer.parseInt(gameId);
            }
        }
        return sum;
    }

    private static int part2PowerOfGameHighestSet(List<String> inputs) {
        int power = 0;
        for (var input : inputs) {
            var gameSetArray = input.split(":");
            var colorNumberMap = parseSets(gameSetArray[1]);
            power = power + (colorNumberMap.get(RED) * colorNumberMap.get(BLUE) * colorNumberMap.get(GREEN));
        }
        return power;
    }

    private static Map<String, Integer> parseSets(String fullGameSet) {
        var colorNumberMap = new HashMap<String, Integer>();

        Arrays.stream(fullGameSet.split(";"))
                .map(set -> set.split(","))
                .flatMap(Arrays::stream)
                .map(String::strip)
                .map(cube -> cube.split(" "))
                .forEach(colorNumberSplit -> {
                    var color = colorNumberSplit[1];
                    var number = Integer.parseInt(colorNumberSplit[0]);
                    colorNumberMap.merge(color, number, Integer::max);
                });
        return colorNumberMap;
    }

    private static boolean isGamePossible(Map<String, Integer> colorNumberMap) {
        return colorNumberMap.get(RED) <= COLOR_NUMBER_LIMIT_MAP.get(RED) &&
                colorNumberMap.get(GREEN) <= COLOR_NUMBER_LIMIT_MAP.get(GREEN) &&
                colorNumberMap.get(BLUE) <= COLOR_NUMBER_LIMIT_MAP.get(BLUE);
    }
}
