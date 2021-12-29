package kitchenpos.tablegroup.application;

import static kitchenpos.table.application.TableServiceTestHelper.*;
import static kitchenpos.tablegroup.application.TableGroupServiceTestHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTableRepository;
import kitchenpos.tablegroup.domain.TableGroup;
import kitchenpos.tablegroup.domain.TableGroupRepository;

@ExtendWith(MockitoExtension.class)
public class TableGroupServiceTest {
    @Mock
    OrderTableRepository orderTableRepository;
    @Mock
    TableGroupRepository tableGroupRepository;
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    TableGroupService tableGroupService;

    @DisplayName("단체 좌석을 생성한다.")
    @Test
    void createTableGroup() {
        // given
        OrderTable orderTable = 좌석_정보(1L, 0, true, null);
        OrderTable orderTable2 = 좌석_정보(2L, 0, true, null);
        TableGroup tableGroup = 단체_좌석_정보(1L, orderTable, orderTable2);
        given(orderTableRepository.findAllByIdIn(anyList())).willReturn(Arrays.asList(orderTable, orderTable2));

        // when
        when(tableGroupRepository.save(any())).thenReturn(tableGroup);
        when(orderTableRepository.save(any())).thenReturn(orderTable);
        tableGroup = tableGroupService.create(tableGroup);

        // then
        assertThat(tableGroup).isNotNull();
        assertThat(tableGroup.getOrderTables().size()).isEqualTo(2);
    }

    @DisplayName("단체 좌석을 해제한다.")
    @Test
    void ungroup() {
        // given
        OrderTable orderTable = 좌석_정보(1L, 0, false, 1L);
        OrderTable orderTable2 = 좌석_정보(2L, 0, false, 1L);
        TableGroup tableGroup = 단체_좌석_정보(1L, orderTable, orderTable2);
        given(orderTableRepository.findAllByTableGroupId(any(Long.class))).willReturn(Arrays.asList(orderTable, orderTable2));
        given(orderRepository.existsByOrderTableIdInAndOrderStatusIn(anyList(), anyList())).willReturn(false);

        // when
        when(orderTableRepository.save(any())).thenReturn(orderTable, orderTable2);
        tableGroupService.ungroup(tableGroup.getId());

        // then
        assertThat(orderTable.getTableGroupId()).isNull();
    }

}
