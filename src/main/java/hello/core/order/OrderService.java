package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
    //1. 주문 생성 시 id,상품명, 상품 가격을 파라미터로 넘기고
    // order(주문 결과) 반환

}
