package racingcar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Game {
    private final Map<String, Integer> playerLevels = new HashMap<>();
    private int maxRound = 0;
    private int currentRound = 1;

    public void start() {
        setup();
        while(this.currentRound <= this.maxRound) {
            playRound();
            printProgress();
            this.currentRound++;
        }
        printWinner();
    }

    private void setup() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String playerLevelsInput = Console.readLine();
        for (String player: playerLevelsInput.split(",")) {
            player = player.trim();
            if (playerLevels.containsKey(player))
                throw new IllegalArgumentException("이미 등록된 자동차 이름입니다.");
            if (4 < player.length())
                throw new IllegalArgumentException("자동차 이름은 5글자 이하만 가능합니다.");
            playerLevels.put(player, 0);
        }

        System.out.println("시도할 횟수는 몇 회인가요?");
        String maxRoundInput = Console.readLine().trim();
        try {
            this.maxRound = Integer.parseInt(maxRoundInput);
            if (maxRound < 1)
                throw new IllegalArgumentException("진행할 라운드 수는 0보다 커야합니다.");
        } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("진행할 라운드 수는 숫자만 입력 가능합니다.");
        }
    }

    private void playRound() {
        for (String player: playerLevels.keySet()) {
            if (canGoNext()) {
                int nextLevel = playerLevels.get(player) + 1;
                playerLevels.put(player, nextLevel);
            }
        }
    }

    private boolean canGoNext() {
        int value = Randoms.pickNumberInRange(0, 9);
        return 3 < value;
    }

    private void printProgress() {
        StringBuilder sb = new StringBuilder();
        for (String player: playerLevels.keySet()) {
            int level = playerLevels.get(player);
            sb.append(player).append(" : ");
            sb.append("-".repeat(Math.max(0, level)));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private List<String> findWinner() {
        List<String> winners = new ArrayList<>();
        int maxLevel = 0;
        for (String player: playerLevels.keySet()) {
            int level = playerLevels.get(player);
            if (maxLevel < level) {
                maxLevel = level;
                winners.clear();
                winners.add(player);
            } else if (maxLevel == level) {
                winners.add(player);
            }
        }
        return winners;
    }

    private void printWinner() {
        List<String> winners = findWinner();
        StringBuilder sb = new StringBuilder();
        sb.append("최종 우승자 : ");
        winners.forEach(winner -> sb.append(winner).append(", "));
        System.out.println(sb.substring(0, sb.length() - 2));
    }
}
