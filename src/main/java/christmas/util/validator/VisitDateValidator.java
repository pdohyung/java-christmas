package christmas.util.validator;

import static christmas.util.ErrorMessage.*;

public class VisitDateValidator {
    public static int validateInputVisitDate(String input) {
        return convertNumberOfMenuToNumber(input);
    }

    private static int convertNumberOfMenuToNumber(String input) {
        try {
            int result = Integer.parseInt(input);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE.getErrorMessage());
        }
    }
}
