package christmas.view;

import static java.text.MessageFormat.format;

public class OutputView {
    private static final String PRINT_EVENT_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_START_EVENT_PREVIEW = "12월 {0}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String PRINT_START_ORDER_MENUS = "\n<주문 메뉴>";
    private static final String PRINT_START_GIFT = "\n<증정 메뉴>";
    private static final String PRINT_START_DISCOUNT = "\n<혜택 내역>";
    private static final String PRINT_ORDER_MENU = "{0} {1}개";
    private static final String PRINT_TOTAL_PRICE = "\n<할인 전 총주문 금액>\n{0}원";
    private static final String PRINT_DISCOUNT = "{0}: -{1}원";
    private static final String PRINT_GIFT = "샴페인 1개";
    private static final String NONE = "없음";

    public void printEventStart() {
        System.out.println(PRINT_EVENT_START_MESSAGE);
    }

    public void printStartEventPreview(int visitDate) {
        System.out.println(format(PRINT_START_EVENT_PREVIEW, visitDate));
    }

    public void printStartOrderMenus() {
        System.out.println(PRINT_START_ORDER_MENUS);
    }

    public void printOrderMenus(String menuName, int quantity) {
        System.out.println(format(PRINT_ORDER_MENU, menuName, quantity));
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println(format(PRINT_TOTAL_PRICE, totalPrice));
    }

    public void printGift(boolean hasGift) {
        System.out.println(PRINT_START_GIFT);
        if (hasGift) {
            System.out.println(PRINT_GIFT);
            return;
        }
        printNone();
    }

    public void printStartDiscount() {
        System.out.println(PRINT_START_DISCOUNT);
    }

    public void printDiscount(String discountName, int discountPrice) {
        System.out.println(format(PRINT_DISCOUNT, discountName, discountPrice));
    }

    public void printNone() {
        System.out.println(NONE);
    }
}
