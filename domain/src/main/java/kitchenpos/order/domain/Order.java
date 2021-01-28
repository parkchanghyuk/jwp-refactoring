package kitchenpos.order.domain;

import kitchenpos.common.domain.BaseEntity;
import kitchenpos.ordertable.domain.OrderTable;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(OrderTable orderTable, List<OrderMenu> orderMenus) {
        validate(orderTable, orderMenus);
        this.orderTable = orderTable;
        this.orderStatus = OrderStatus.COOKING;
    }

    protected Order() {
    }

    public boolean isSame(Order order) {
        return order == this;
    }

    public void changeStatus(OrderStatus orderStatus) {
        if (isCompleted()) {
            throw new IllegalArgumentException("종료된 주문의 상태는 변경할 수 없습니다.");
        }
        this.orderStatus = orderStatus;
    }

    public boolean isCompleted() {
        return orderStatus == OrderStatus.COMPLETION;
    }

    public boolean isNotCompleted() {
        return orderStatus != OrderStatus.COMPLETION;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private static void validate(OrderTable orderTable, List<OrderMenu> orderMenus) {
        if (orderTable.isEmpty()) {
            throw new IllegalArgumentException("비어있는 주문 테이블입니다.");
        }
        if (CollectionUtils.isEmpty(orderMenus)) {
            throw new IllegalArgumentException("생성할 주문의 항목이 없습니다.");
        }
    }
}