package christmas.domain.enums;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 100),
    WEEKDAY("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SPECIAL("특별 할인", 1_000),
    GIFT("증정 이벤트", 120_000);

    private final String name;
    private final int price;

    Discount(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
