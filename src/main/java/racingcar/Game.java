package racingcar;

import java.util.*;

import camp.nextstep.edu.missionutils.Randoms;

public class Game {
    private final Map<String, Integer> playerStatus = new LinkedHashMap<>();

    public Game(List<String> playerNames) {
        for (String player: playerNames) {
            validateName(player);
            playerStatus.put(player, 0);
        }
    }

    private void validateName(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5글자 이하만 가능합니다: " + name);
        }
        if (playerStatus.containsKey(name)) {
            throw new IllegalArgumentException("이미 등록된 자동차 이름입니다: " + name);
        }
    }

    public void playRound() {
        for (String player: playerStatus.keySet()) {
            if (canGoNext()) {
                int nextLevel = playerStatus.get(player) + 1;
                playerStatus.put(player, nextLevel);
            }
        }
    }

    public Map<String, Integer> getPlayerStatus() {
        return playerStatus;
    }

    public List<String> findWinners() {
        List<String> winners = new ArrayList<>();
        int maxLevel = 0;
        for (String player: playerStatus.keySet()) {
            int level = playerStatus.get(player);
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

    private boolean canGoNext() {
        int value = Randoms.pickNumberInRange(0, 9);
        return 3 < value;
    }
}
