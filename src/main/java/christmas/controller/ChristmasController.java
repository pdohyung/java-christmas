package christmas.controller;

import christmas.domain.TotalDiscount;
import christmas.domain.enums.Discount;
import christmas.domain.enums.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.domain.enums.Discount.GIFT;
import static christmas.util.Constants.*;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int visitDate = getVisitDate();
        outputView.printStartEventPreview(visitDate);
        Map<Menu, Integer> orderMenus = getOrderMenus();
        int totalPrice = showTotalPrice(orderMenus);
        Map<Discount, Integer> totalDiscount = new TotalDiscount(visitDate, orderMenus, totalPrice).getTotalDiscount();
        showTotalDiscount(totalDiscount);
        int totalDiscountPrice = getTotalDiscountPrice(totalDiscount);
        outputView.printTotalDiscountPrice(totalDiscountPrice);
        int finalPrice = getFinalPrice(totalPrice, totalDiscountPrice);
        outputView.printFinalPrice(finalPrice);
    }

    public int getVisitDate() {
        outputView.printEventStart();
        return inputView.inputVisitDate();
    }

    public Map<Menu, Integer> getOrderMenus() {
        Map<Menu, Integer> orderMenus = inputView.inputOrderMenus();
        showOrderMenu(orderMenus);
        return orderMenus;
    }

    public void showOrderMenu(Map<Menu, Integer> orderMenus) {
        outputView.printStartOrderMenus();
        for (Menu menu : orderMenus.keySet()) {
            outputView.printOrderMenus(menu.getName(), orderMenus.get(menu));
        }
    }

    public int showTotalPrice(Map<Menu, Integer> orderMenus) {
        int totalPrice = 0;
        for (Menu menu : orderMenus.keySet()) {
            totalPrice += menu.getPrice() * orderMenus.get(menu);
        }
        outputView.printTotalPrice(totalPrice);
        showGift(totalPrice);
        return totalPrice;
    }

    public void showGift(int totalPrice) {
        if (totalPrice < GIFT_STANDARD) {
            outputView.printGift(false);
            return;
        }
        outputView.printGift(true);
    }

    public void showTotalDiscount(Map<Discount, Integer> totalDiscount) {
        outputView.printStartDiscount();
        if (totalDiscount.isEmpty()) {
            outputView.printNone();
            return;
        }
        for (Discount discount : totalDiscount.keySet()) {
            outputView.printDiscount(discount.getName(), totalDiscount.get(discount));
        }
    }

    public int getTotalDiscountPrice(Map<Discount, Integer> totalDiscount) {
        return totalDiscount.keySet().stream()
                .mapToInt(totalDiscount::get)
                .sum();
    }

    public int getFinalPrice(int totalPrice, int totalDiscountPrice) {
        if (totalDiscountPrice == ZERO) {
            return totalPrice;
        }
        if (totalPrice >= GIFT_STANDARD) {
            return totalPrice - totalDiscountPrice + GIFT.getPrice();
        }
        return totalPrice - totalDiscountPrice;
    }
}
