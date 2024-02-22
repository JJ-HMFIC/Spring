package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //cal1 AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    @Bean
    public MemberService memberService() {
        System.out.println("cal1 AppConfig.memberService"); //soutm
        return new MemberServiceImpl(memberRepository());
        //구현체에 MemoryMemberRepository를 넣어서 생성 , 생성자 주입
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    } // ctrl + alt + m : 리팩토링
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");// soutm
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //애플리케이션의 실제 동작에 필요한 구현 객체를 생성, 생성자를 통해서 주입(연결)
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
