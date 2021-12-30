package kitchenpos.product.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductPrice {
    @Column
    private BigDecimal price;

    public ProductPrice() {

    }

    public ProductPrice(BigDecimal price) {
        checkProductPrice(price);
        this.price = price;
    }

    private void checkProductPrice(BigDecimal price) {
        if (isNegative(price)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNegative(BigDecimal price) {
        return Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
