package kitchenpos.order.ui;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.order.application.OrderService;
import kitchenpos.order.domain.Order;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderResponse;
import kitchenpos.order.dto.OrderStatusRequest;

@RequestMapping("/api/orders")
@RestController
public class OrderRestController {
	private final OrderService orderService;

	public OrderRestController(final OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponse> create(@RequestBody final OrderRequest order) {
		final Order created = orderService.create(order);
		final URI uri = URI.create("/api/orders/" + created.getId());
		return ResponseEntity.created(uri)
			.body(created.toResDto());
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> list() {
		return ResponseEntity.ok()
			.body(OrderResponse.ofList(orderService.list()));
	}

	@PutMapping("/{orderId}/order-status")
	public ResponseEntity<OrderResponse> changeOrderStatus(
		@PathVariable final Long orderId,
		@RequestBody final OrderStatusRequest orderStatusRequest
	) {
		return ResponseEntity.ok(orderService.changeOrderStatus(orderId, orderStatusRequest).toResDto());
	}
}
