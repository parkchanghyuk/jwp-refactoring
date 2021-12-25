package kitchenpos.order.domain;

import kitchenpos.table.domain.OrderTable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_table_id", nullable = false)
    private OrderTable orderTable;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "ordered_time", nullable = false)
    private LocalDateTime orderedTime;

    @OneToMany(mappedBy = "order")
    private List<OrderLineItem> orderLineItems;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getOrderTableId() {
        return orderTable.getId();
    }

    public void setOrderTableId(final Long orderTableId) {
//        this.orderTableId = orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus.name();
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = OrderStatus.valueOf(orderStatus);
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(final LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(final List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public void completion() {
        this.orderStatus = OrderStatus.COMPLETION;
    }

    public void cooking() {
        this.orderStatus = OrderStatus.COOKING;
    }

    public void meal() {
        this.orderStatus = OrderStatus.MEAL;
    }

    public boolean isCooking() {
        return this.orderStatus == OrderStatus.COOKING;
    }

    public boolean isMeal() {
        return this.orderStatus == OrderStatus.MEAL;
    }

    public boolean isCompletion() {
        return this.orderStatus == OrderStatus.COMPLETION;
    }

    public boolean notIsCompletion() {
        return !isCompletion();
    }
}
