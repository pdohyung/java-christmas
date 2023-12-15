package christmas.controller;

import christmas.domain.TotalDiscount;
import christmas.domain.enums.Discount;
import christmas.domain.enums.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.util.Constants.*;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printEventStart();
        int visitDate = inputView.inputVisitDate();
        Map<Menu, Integer> orderMenus = inputView.inputOrderMenus();
        outputView.printStartEventPreview(visitDate);
        showOrderMenu(orderMenus);
        int totalPrice = showTotalPrice(orderMenus);
        showGift(totalPrice);
        Map<Discount, Integer> totalDiscount = new TotalDiscount(visitDate, orderMenus, totalPrice).getTotalDiscount();
        showTotalDiscount(totalDiscount);
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
        for (Discount discount : totalDiscount.keySet()) {
            outputView.printDiscount(discount.getName(), totalDiscount.get(discount));
        }
    }
}
