package kitchenpos.product.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(final ProductRequest productRequest) {
        Product product = new Product(productRequest.getName(), productRequest.getPrice());
        return ProductResponse.of(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }
}
