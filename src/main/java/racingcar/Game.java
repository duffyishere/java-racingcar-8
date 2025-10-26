package racingcar;

import java.util.HashMap;
import java.util.Map;

import camp.nextstep.edu.missionutils.Console;

public class Game {
    private Map<String, Integer> players = new HashMap<>();
    private int maxRound = 0;
    private int currentRound = 0;

    public void start() {
        setup();
    }

    private void setup() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉽표(,) 기준으로 구분)");
        String playersInput = Console.readLine();
        for (String player: playersInput.split(",")) {
            if (players.containsKey(player)) {
                throw new IllegalArgumentException("이미 등록된 자동차 이름입니다.");
            }
            players.put(player, 0);
        }

        System.out.println("시도할 횟수는 몇 회인가요?");
        String maxRoundInput = Console.readLine().trim();
        try {
            this.maxRound = Integer.parseInt(maxRoundInput);
            if (maxRound < 1) throw new IllegalArgumentException("진행할 라운드 수는 0보다 커야합니다.");
        } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("진행할 라운드 수는 숫자만 입력 가능합니다.");
        }
    }
}
