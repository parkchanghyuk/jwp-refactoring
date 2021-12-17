package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuRequest {

    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductRequest> menuProductRequests;

    public MenuRequest() {
    }

    public MenuRequest(String name, BigDecimal price, Long menuGroupId, List<MenuProductRequest> menuProductRequests) {
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProductRequests = menuProductRequests;
    }

    public static MenuRequest of(String name, BigDecimal price, Long menuGroupId) {
        return new MenuRequest(name, price, menuGroupId, new ArrayList<>());
    }

    public static MenuRequest of(String name, BigDecimal price, Long menuGroupId, List<MenuProductRequest> menuProductIds) {
        return new MenuRequest(name, price, menuGroupId, menuProductIds);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductRequest> getMenuProductRequests() {
        return menuProductRequests;
    }
}