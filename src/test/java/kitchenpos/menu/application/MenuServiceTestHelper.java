package kitchenpos.menu.application;

import java.math.BigDecimal;
import java.util.Arrays;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;

public class MenuServiceTestHelper {
    public static Menu 메뉴_생성(String name, int price, Long menuGroupId, MenuProduct... menuProducts) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setPrice(BigDecimal.valueOf(price));
        menu.setMenuGroupId(menuGroupId);
        menu.setMenuProducts(Arrays.asList(menuProducts));
        return menu;
    }

    public static MenuProduct 메뉴_상품_생성(Long productId, int quantity) {
        MenuProduct menuProduct = new MenuProduct(productId, quantity);
        return menuProduct;
    }
}
