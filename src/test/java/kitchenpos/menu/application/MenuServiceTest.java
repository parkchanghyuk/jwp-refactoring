package kitchenpos.menu.application;

import static kitchenpos.menu.application.MenuServiceTestHelper.*;
import static kitchenpos.menugroup.application.MenuGroupServiceTestHelper.*;
import static kitchenpos.product.application.ProductServiceTestHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.menu.domain.MenuDao;
import kitchenpos.menugroup.domain.MenuGroupDao;
import kitchenpos.menugroup.domain.MenuProductDao;
import kitchenpos.product.domain.ProductDao;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.domain.Product;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @Mock
    private MenuDao menuDao;
    @Mock
    private MenuGroupDao menuGroupDao;
    @Mock
    private MenuProductDao menuProductDao;
    @Mock
    private ProductDao productDao;

    @InjectMocks
    private MenuService menuService;

    private Product 제육볶음;
    private MenuProduct 제육볶음_상품;
    private MenuGroup 분식;

    @BeforeEach
    void setUp() {
        // given
        제육볶음 = 상품_생성(1L, "제육볶음", 8_900);
        제육볶음_상품 = 메뉴_상품_생성(제육볶음.getId(), 1);
        분식 = 메뉴_그룹_생성(1L, "분식");
    }

    @DisplayName("메뉴 등록")
    @Test
    void createMenu() {
        Menu 제육볶음_메뉴 = 메뉴_생성("제육볶음정식", 6000, 분식.getId(), 제육볶음_상품);
        given(menuGroupDao.existsById(any(Long.class))).willReturn(true);
        given(productDao.findById(any(Long.class))).willReturn(Optional.of(제육볶음));

        when(menuDao.save(any(Menu.class))).thenReturn(제육볶음_메뉴);
        when(menuProductDao.save(any(MenuProduct.class))).thenReturn(제육볶음_상품);

        제육볶음_메뉴 = menuService.create(제육볶음_메뉴);

        assertThat(제육볶음_메뉴).isNotNull();
    }

}
