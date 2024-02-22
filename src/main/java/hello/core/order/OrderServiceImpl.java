            package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;// = new MemoryMemberRepository();
    // AppConfig에 따른 수정, 추상화에만 집중

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 고정 할인 정책
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 비율 할인 정책
    private final DiscountPolicy discountPolicy; // 인터페이스만 의존
    //누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다

    /*
     * 할인 정책을 변경하려면 클라이언트인 OrderSerivceImpl 코드를 고쳐야함
     * DIP : 주문서비스 클라이언트( OrderServiceImpl )는 DiscountPolicy 인터페이스에 의존하면서 DIP를
        지킨 것 같은데?
     * 클래스 의존관계를 분석해 보자. 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
        추상(인터페이스) 의존: DiscountPolicy
        구체(구현) 클래스: FixDiscountPolicy , RateDiscountPolicy
        => DiscountPolicy 자료형이지만 new로 RateDiscountPolicy/FixDiscountPolicy 만들기 때문

        OrderServiceImpl 이 DiscountPolicy 인터페이스 뿐만 아니라
        FixDiscountPolicy 인 구체 클래스도 함께 의존하고 있다. 실제 코드를 보면 의존하고 있다! DIP 위반

     * OCP: 변경하지 않고 확장할 수 있다고 했는데!
        지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다.

        FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 의 소스
        코드도 함께 변경해야 한다! OCP 위반
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // Order(주문)에 관해서만 담당
        // discount(할인)에 대해서는 discountPolicy에 넘겨 => 단일 체계 원칙을 수행
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}