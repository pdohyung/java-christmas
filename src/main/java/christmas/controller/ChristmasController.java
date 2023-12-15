package christmas.controller;

import christmas.domain.enums.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

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
    }

    public void showOrderMenu(Map<Menu, Integer> orderMenus) {
        outputView.printStartOrderMenus();
        for (Menu menu : orderMenus.keySet()) {
            outputView.printOrderMenus(menu.getName(), orderMenus.get(menu));
        }
    }
}
