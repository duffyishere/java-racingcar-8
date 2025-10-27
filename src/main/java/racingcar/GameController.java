package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {
    public void start() {
        List<String> playerNames = readPlayerNames();
        int maxRound = readMaxRound();
        Game game = new Game(playerNames);

        for (int round = 1; round <= maxRound; round++) {
            game.playRound();
            printProgress(game.getPlayerStatus());
        }

        printWinners(game.findWinners());
    }

    public List<String> readPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String playerLevelsInput = Console.readLine();
        for (String player: playerLevelsInput.split(",")) {
            String trimmedPlayerName = player.trim();
            validatePlayerName(playerNames, trimmedPlayerName);
            playerNames.add(trimmedPlayerName);
        }
        return playerNames;
    }

    private void validatePlayerName(List<String> playerLevels, String player) {
        if (playerLevels.contains(player))
            throw new IllegalArgumentException("이미 등록된 자동차 이름입니다.");
        if (4 < player.length())
            throw new IllegalArgumentException("자동차 이름은 5글자 이하만 가능합니다.");
    }

    public int readMaxRound() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String maxRoundInput = Console.readLine().trim();
        try {
            int maxRound = Integer.parseInt(maxRoundInput);
            if (maxRound < 1)
                throw new IllegalArgumentException("진행할 라운드 수는 0보다 커야합니다.");
            return maxRound;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("진행할 라운드 수는 숫자만 입력 가능합니다.");
        }
    }

    private void printProgress(Map<String, Integer> playerStatus) {
        playerStatus.forEach((player, level) -> {
            System.out.println(player + " : " + "-".repeat(level));
        });
        System.out.println();
    }

    private void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
