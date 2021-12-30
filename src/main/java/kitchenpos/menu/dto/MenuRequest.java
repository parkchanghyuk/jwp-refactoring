package kitchenpos.menu.dto;

import java.math.BigDecimal;

public class MenuRequest {
    private String name;
    private BigDecimal price;
    private Long menuGroupId;

    public MenuRequest() {
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
}
