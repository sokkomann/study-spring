package com.app.app.service;

//    Target(연예인)
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name){
        return "Hello " + name;
    }

    public String sayGoodbye(String name){
        return "Goodbye " + name;
    }
}
