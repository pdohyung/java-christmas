package christmas.util;

public enum ErrorMessage {
    INVALID_DATE_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_ORDER_FORMAT_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
