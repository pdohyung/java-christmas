package christmas.domain.enums;

import java.util.List;

public enum Days {
    SUNDAY(1, List.of(3, 10, 17, 24, 31)),
    MONDAY(1, List.of(4, 11, 18, 25)),
    TUESDAY(1, List.of(5, 12, 19, 26)),
    WEDNESDAY(1, List.of(6, 13, 20, 27)),
    THURSDAY(1, List.of(7, 14, 21, 28)),
    FRIDAY(2, List.of(1, 8, 15, 22, 29)),
    SATURDAY(2, List.of(2, 9, 16, 23, 30));

    private final int type;
    private final List<Integer> dates;

    Days(int type, List<Integer> dates) {
        this.type = type;
        this.dates = dates;
    }

    public int getType() {
        return type;
    }

    public List<Integer> getDates() {
        return dates;
    }
}

