package kitchenpos.menu.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kitchenpos.product.domain.Product;

import javax.persistence.*;

@Entity
public class MenuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
//    private Long menuId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;
//    private Long productId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    private long quantity;

    public Long getSeq() {
        return seq;
    }

    public Menu getMenu() {
        return menu;
    }

    public void changeMenu(Menu menu) {
        this.menu = menu;
    }

    public Product getProduct() {
        return product;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void changeQuantity(final long quantity) {
        this.quantity = quantity;
    }
}