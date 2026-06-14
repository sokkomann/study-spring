package com.example.controller.controller.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor  //기본생성자
@Getter @Setter @ToString
// 왜 컴포넌트로 스프링한테 보고 안하나요? => 화면으로 받은 객체들은 스프링한테 보고할 필요가없다. (세터메소드로 바인딩 한것)
public class Member {
    private String name;
    private int age;
}
