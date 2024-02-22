package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        
        for (String beanDefinitionName : beanDefinitionNames) {
            //iter
            // beanDefinitionNames 배열을 순회하면서 각 빈의 이름을 beanDefinitionName 변수에 할당
            Object bean = ac.getBean(beanDefinitionName);
            //ac.getBean(beanDefinitionName)을 호출하여 해당 이름의 빈을 가져옴.
            // 가져온 빈을 bean 변수에 저장
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
        //ApplicationContext에 등록된 모든 빈의 이름과 각 빈 객체를 출력하는 메소드
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            //iter
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //ac.getBeanDefinition(beanDefinitionName)을 호출하여
            //해당 이름의 빈에 대한 BeanDefinition 객체를 가져온다.
            //이를 beanDefinition 변수에 저장합니다.
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                // beanDefinition.getRole()을 호출하여 해당 빈의 역할(role)을 확인함.
                // 여기서 BeanDefinition.ROLE_APPLICATION은 애플리케이션 역할을 가진 빈을 나타내는 상수
                Object bean = ac.getBean(beanDefinitionName);
                // ac.getBean(beanDefinitionName)을 호출하여 해당 이름의 빈을 가져옴
                // 이를 bean 변수에 저장
               System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }



        }
        //ApplicationContext에 등록된 빈 중에서 애플리케이션 역할을 가진 빈의 이름과 객체를 출력하는 메소드
    }
}
