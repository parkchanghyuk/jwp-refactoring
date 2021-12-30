package kitchenpos.product.application;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.product.domain.Product;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTestHelper {
    public static Product 상품_생성(Long id, String name, int price) {
        return new Product(id, name, BigDecimal.valueOf(price));
    }
}
