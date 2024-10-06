package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private final int discountFixAmount = 1000;// 1000원 할인
    @Override
    public int disount(Member member, int price) { //vip면 1000원 할인 , 아니면 0 반환
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
