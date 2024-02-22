package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //psvm
/*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
*/
        //MemberService memberService = new MemberServiceImpl(); Appconfig에 의한 수정

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /*
        ApplicationContext : 스프링컨테이너, 인터페이스
         */
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        //getBean(메서드명, 리턴 타입)
        Member member = new Member(1L, "memberA", Grade.VIP);
        // ctrl+alt+v
        memberService.join(member);


        Member findMember = memberService.findMember(1l);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
        //soutv

    }
}
