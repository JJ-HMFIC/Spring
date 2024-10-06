package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifecycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 샏성자 호출 ①
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

/*
생성자 호출, url = null -> 샏성자 호출 ①
connect: http://hello-spring.dev -> DI로 초기값 세팅
call: http://hello-spring.devmessage = 초기화 연결 메세지
close: http://hello-spring.dev
* */