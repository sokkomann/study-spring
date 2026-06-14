package com.app.app.proxy;

import com.app.app.aop.aspect.handler.SimpleHandler;
import com.app.app.aop.aspect.util.MethodMatcher;
import com.app.app.aop.aspect.util.NameMatchMethodMatcher;
import com.app.app.service.HelloService;
import com.app.app.service.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

@SpringBootTest
@Slf4j
public class ProxyTests {

    @Test
    public void testSay(){
//        연예인(Target)
        HelloService target = new HelloServiceImpl();

//        광고만 진행하겠다고 함(PointCut)
        MethodMatcher matcher = new NameMatchMethodMatcher("sayHello");

//        매니저(Proxy)
        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader(),
                new Class[] {HelloService.class},
//                매니저가 계약서 작성(Advice)
                new SimpleHandler(target, matcher));

//        광고 진행
        String result = proxy.sayGoodbye("ted");
        log.info("result: {}", result);
    }
}















