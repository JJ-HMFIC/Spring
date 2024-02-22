package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // 자기 자신을 내부에 하나로 가짐, static이기 때문에 하나만 존재

    public static SingletonService getInstance() {
        return instance;
    }
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
