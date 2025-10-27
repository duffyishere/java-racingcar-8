package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class GameTest {

    @Nested
    @DisplayName("사용자 이름 테스트")
    class NameValidation {
        @Test
        @DisplayName("글자 수 예외 테스트")
        void 글자수_예외_테스트() {
            List<String> names = Arrays.asList("pobi", "woni", "kwak_duffy");

            assertThatThrownBy(() -> new Game(names))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름은 5글자 이하만 가능합니다");
        }

        @Test
        @DisplayName("중복 예외 테스트")
        void 중복_예외_테스트() {
            List<String> names = Arrays.asList("pobi", "pobi", "kwak_duffy");

            assertThatThrownBy(() -> new Game(names))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이미 등록된 자동차 이름입니다");
        }
    }

    @Nested
    @DisplayName("기능 테스트")
    class Winners {

        @Test
        @DisplayName("단독 우승자 테스트")
        void 단독_우승자_테스트() {
            Game game = new Game(Arrays.asList("pobi", "woni", "jun"));

            Map<String, Integer> status = game.getPlayerStatus();
            status.put("pobi", 4);
            status.put("woni", 2);
            status.put("jun", 3);

            List<String> winners = game.findWinners();
            assertThat(winners).containsExactly("pobi");
        }

        @Test
        @DisplayName("공동 우승자 테스트")
        void 공동_우승자_테스트() {
            Game game = new Game(Arrays.asList("pobi", "woni", "jun"));

            Map<String, Integer> status = game.getPlayerStatus();
            status.put("pobi", 5);
            status.put("woni", 5);
            status.put("jun", 2);

            List<String> winners = game.findWinners();
            assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
        }
    }
}
