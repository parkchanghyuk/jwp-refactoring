package kitchenpos.tablegroup.application;

import java.util.Arrays;

import kitchenpos.table.domain.OrderTable;
import kitchenpos.tablegroup.domain.TableGroup;

public class TableGroupServiceTestHelper {
    public static TableGroup 단체_좌석_정보(Long id, OrderTable... orderTables) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setId(id);
        tableGroup.setOrderTables(Arrays.asList(orderTables));
        return tableGroup;
    }
}
