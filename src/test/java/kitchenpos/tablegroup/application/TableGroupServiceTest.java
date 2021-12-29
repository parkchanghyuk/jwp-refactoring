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

import kitchenpos.table.domain.OrderTableDao;
import kitchenpos.tablegroup.domain.TableGroupDao;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.tablegroup.domain.TableGroup;

@ExtendWith(MockitoExtension.class)
public class TableGroupServiceTest {
    @Mock
    OrderTableDao orderTableDao;
    @Mock
    TableGroupDao tableGroupDao;
    @Mock
    OrderDao orderDao;

    @InjectMocks
    TableGroupService tableGroupService;

    @DisplayName("단체 좌석을 생성한다.")
    @Test
    void createTableGroup() {
        // given
        OrderTable orderTable = 좌석_정보(1L, 0, true, null);
        OrderTable orderTable2 = 좌석_정보(2L, 0, true, null);
        TableGroup tableGroup = 단체_좌석_정보(1L, orderTable, orderTable2);
        given(orderTableDao.findAllByIdIn(anyList())).willReturn(Arrays.asList(orderTable, orderTable2));

        // when
        when(tableGroupDao.save(any())).thenReturn(tableGroup);
        when(orderTableDao.save(any())).thenReturn(orderTable);
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
        given(orderTableDao.findAllByTableGroupId(any(Long.class))).willReturn(Arrays.asList(orderTable, orderTable2));
        given(orderDao.existsByOrderTableIdInAndOrderStatusIn(anyList(), anyList())).willReturn(false);

        // when
        when(orderTableDao.save(any())).thenReturn(orderTable, orderTable2);
        tableGroupService.ungroup(tableGroup.getId());

        // then
        assertThat(orderTable.getTableGroupId()).isNull();
    }

}
