package hello.core.lifecycle;

import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("===================================");
        System.out.println("* 생성");
        System.out.println("===================================");
        System.out.println("URL     : " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("Connect : " + url);
    }

    public void call(String message) {
        System.out.println("Call    : " + url);
        System.out.println("Message : " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("Close   : " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("===================================");
        System.out.println("* 초기화");
        System.out.println("===================================");
        connect();
        call("초기화 연결");
        System.out.println("===================================");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("===================================");
        System.out.println("* 소멸");
        System.out.println("===================================");
        disconnect();
        System.out.println("===================================");
    }
}
