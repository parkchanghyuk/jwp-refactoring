# 키친포스

---
## 2단계 서비스 리팩터링
### 리팩토링
* 기능 별 패키지 분리
* *JDBC* → *JPA* 변경
  * `Entity`
  * `Repository`
* *DTO* 설정 
  * `Request`
  * `Response`
* *Entity* 설정
  * 원시값 포장 
  * 연관관계 설정
* *Service* 로직 *Domain* 으로 이동
* *Test* 로직 확인

---

## 1단계 테스트를 통한 코드 보호 
## 요구 사항
### 상품 `Product`
* 상품을 등록한다.
  * 가격은 *필수* 이다.
  * 가격은 *음수* 가 될 수 없다.
* 상품을 조회한다.
* 상품 목록을 조회한다

### 메뉴 그룹 `MenuGroup`
* 메뉴 그룹을 등록한다.
* 메뉴 목록을 조회한다.

### 메뉴 `Menu`
* 메뉴를 등록한다.
  * 가격은 *필수* 이다.
  * 가격은 *음수* 가 될 수 없다.
  * 메뉴 그룹이 등록되어 있어야 한다.
  * 상품은 모두 등록되어 있어야 한다.
  * 삼품 가격의 합은 *0* 보다 크다.
* 메뉴 목록을 조회한다.

### 좌석 `Table`
* 좌석을 등록한다.
* 좌석 목록을 조회한다.
* 좌석을 비운다.
  * 좌석이 등록되어 있어야 한다.
  * 단체 좌석에 포함되어 있을 경우 좌석을 비울 수 없다.
  * 조리 중 또는 식사 중인 좌석은 비울 수 없다.
* 손님 수를 변경한다.
  * 손님 수는 *음수* 일 수 없다.
  * 좌석이 등록되어 있어야 한다.
  * 좌석이 비어있을 경우 변경 할 수 없다.

### 단체 좌석 `TableGroup`
* 단체 좌석을 등록한다.
  * 좌석은 2좌석 이상이어야 한다.
  * 좌석들은 모두 등록되어 있어야 한다.
  * 좌석들은 손님이 없는 상태여야 한다.
  * 좌석들은 다른 단체좌석으로 등록되어 있으면 안된다.
* 단체 좌석을 해제한다.
  * 단체 좌석 중 요리중이거나 식사중인 좌석이 있으면 안된다.

### 주문 `Order`
* 주문을 등록한다.
  * 메뉴가 없으면 안된다.
  * 등록된 메뉴만 주문 가능하다.
  * 좌석이 등록되어 있어야 한다.
  * 좌석에 손님이 없으면 안된다.
* 주문 목록을 조회한다.
* 주문 상태를 변경한다.
  * 주문이 등록되어 있어야 한다.
  * 주문의 상태가 *완료* 인 경우 변경 할 수 없다.

---

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
