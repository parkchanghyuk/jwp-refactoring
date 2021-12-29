package kitchenpos.menu.acceptance;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;

public class MenuAcceptanceTestHelper {

    public static ExtractableResponse 메뉴_등록되어_있음(String name, int price, Long id, MenuProduct... menuProducts) {
        return 메뉴_등록_요청(name, price, id, menuProducts);
    }

    public static ExtractableResponse<Response> 메뉴_목록_조회_요청() {
        return RestAssured
            .given().log().all()
            .when().get("/api/menus")
            .then().log().all().extract();
    }

    public static ExtractableResponse 메뉴_등록_요청(String name, int price, Long id, MenuProduct... menuProducts) {
        Menu menu = new Menu(name, BigDecimal.valueOf(price), id, Arrays.asList(menuProducts));
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(menu)
            .when().post("/api/menus")
            .then().log().all().extract();
    }

    public static MenuProduct 메뉴_상품(Long productId, int quantity) {
        MenuProduct menuProduct = new MenuProduct(productId, quantity);
        return menuProduct;
    }

    public static void 메뉴_등록됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 메뉴_등록_실패됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static void 메뉴_목록_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
