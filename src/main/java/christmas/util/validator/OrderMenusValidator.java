package christmas.util.validator;

import christmas.domain.enums.Menu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static christmas.util.Constants.*;
import static christmas.util.ErrorMessage.*;

public class OrderMenusValidator {
    public static Map<Menu, Integer> validateInputOrderMenus(String input) {
        List<String> inputOrderMenus = splitInputOrderMenusByComma(input);
        return splitInputOrderMenusByBar(inputOrderMenus);
    }

    private static List<String> splitInputOrderMenusByComma(String input) {
        if (input.startsWith(COMMA) || input.endsWith(COMMA)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_FORMAT_MESSAGE.getErrorMessage());
        }
        return Arrays.stream(input.split(COMMA))
                .toList();
    }

    private static Map<Menu, Integer> splitInputOrderMenusByBar(List<String> inputOrderMenus) {
        Map<Menu, Integer> orderMenus = new LinkedHashMap<>();
        for (String inputOrderMenu : inputOrderMenus) {
            String[] nameAndQuantity = inputOrderMenu.split(BAR);
            Menu menu = isValidMenu(nameAndQuantity[0]);
            int quantity = isValidQuantity(nameAndQuantity[1]);
            if (orderMenus.containsKey(menu)) {
                throw new IllegalArgumentException(INVALID_MENU_ORDER_FORMAT_MESSAGE.getErrorMessage());
            }
            orderMenus.put(menu, quantity);
        }
        return orderMenus;
    }

    private static Menu isValidMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MENU_ORDER_FORMAT_MESSAGE.getErrorMessage()));
    }

    private static int isValidQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_FORMAT_MESSAGE.getErrorMessage());
        }
    }
}
