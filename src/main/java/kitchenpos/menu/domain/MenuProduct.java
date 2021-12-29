package kitchenpos.menu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seq;
    @Column
    private Long menuId;
    @Column
    private Long productId;
    @Column
    private long quantity;

    protected MenuProduct() {
    }

    public MenuProduct(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
