package christmas.domain;

import christmas.domain.enums.Category;
import christmas.domain.enums.Days;
import christmas.domain.enums.Discount;
import christmas.domain.enums.Menu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.domain.enums.Discount.*;
import static christmas.util.Constants.*;
import static christmas.util.Constants.WEEKDAY;
import static christmas.util.Constants.WEEKEND;
import static christmas.util.ErrorMessage.*;

public class TotalDiscount {
    private final Map<Discount, Integer> totalDiscount;

    public TotalDiscount(int visitDate, Map<Menu, Integer> orderMenus, int totalPrice) {
        this.totalDiscount = generateTotalDiscount(visitDate, orderMenus, totalPrice);
    }

    public Map<Discount, Integer> getTotalDiscount() {
        return totalDiscount;
    }

    private Map<Discount, Integer> generateTotalDiscount(int visitDate, Map<Menu, Integer> orderMenus, int totalPrice) {
        Map<Discount, Integer> initialTotalDiscount = new LinkedHashMap<>();
        if (totalPrice < MIN_ORDER_PRICE) {
            return initialTotalDiscount;
        }
        calculateChristmasDDay(visitDate, initialTotalDiscount);
        calculateWeek(visitDate, initialTotalDiscount, orderMenus);
        calculateSpecial(visitDate, initialTotalDiscount);
        calculateGift(totalPrice, initialTotalDiscount);
        return initialTotalDiscount;
    }

    private void calculateChristmasDDay(int visitDate, Map<Discount, Integer> totalDiscount) {
        if (visitDate >= CHRISTMAS_DATE) {
            return;
        }
        totalDiscount.put(CHRISTMAS_D_DAY, DEFAULT_DISCOUNT + visitDate * CHRISTMAS_D_DAY.getPrice());
    }

    private void calculateWeek(int visitDate, Map<Discount, Integer> totalDiscount, Map<Menu, Integer> orderMenus) {
        Days findDays = findDays(visitDate);
        if (findDays.getType() == WEEKDAY) {
            totalDiscount.put(Discount.WEEKDAY, weekdayDiscount(orderMenus));
        }
        if (findDays.getType() == WEEKEND) {
            totalDiscount.put(Discount.WEEKDAY, weekendDiscount(orderMenus));
        }
    }

    private void calculateSpecial(int visitDate, Map<Discount, Integer> totalDiscount) {
        Days findDays = findDays(visitDate);
        if ((findDays == Days.SUNDAY) || (visitDate == CHRISTMAS_DATE)) {
            totalDiscount.put(SPECIAL, SPECIAL.getPrice());
        }
    }

    private void calculateGift(int totalPrice, Map<Discount, Integer> totalDiscount) {
        if (totalPrice >= GIFT_STANDARD) {
            totalDiscount.put(GIFT, GIFT.getPrice());
        }
    }

    private int weekdayDiscount(Map<Menu, Integer> orderMenus) {
        return orderMenus.keySet().stream()
                .filter(menu -> menu.getCategory().equals(Category.DESSERT))
                .mapToInt(menu -> Discount.WEEKDAY.getPrice() * orderMenus.get(menu))
                .sum();
    }

    private int weekendDiscount(Map<Menu, Integer> orderMenus) {
        return orderMenus.keySet().stream()
                .filter(menu -> menu.getCategory().equals(Category.MAIN))
                .mapToInt(menu -> Discount.WEEKEND.getPrice() * orderMenus.get(menu))
                .sum();
    }

    private Days findDays(int visitDate) {
        return Arrays.stream(Days.values())
                .filter(days -> days.getDates().contains(visitDate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DATE_MESSAGE.getErrorMessage()));
    }
}
