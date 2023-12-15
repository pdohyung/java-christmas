package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.validator.VisitDateValidator;

public class InputView {
    private static final String INPUT_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int inputVisitDate() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE);
        return VisitDateValidator.validateInputVisitDate(Console.readLine());
    }
}
