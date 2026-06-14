package com.example.controller.controller;


import com.example.controller.controller.domain.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// restful(REST API) 레스트로 차있다.
@RestController     // 근데 매번 리스폰스바디 붙이기 귀찮고, 만약 이 컨트롤러가 모두 레스트방식이라면 이거를 쓰자.
//@Controller
@RequestMapping("/api/v1/ex/**")
public class ExampleRestController {


//    @ResponseBody   // 레스트방식을 사용한다. 이거를 안붙이면 에러나지. 왜냐면 템플리츠에 한동석.html이 없으니까.
    @GetMapping("ex01")
    public String ex01() {
        return "한동석";
    }



//    레스트방식으로 객체를 리턴해보자
//    제이슨으로 나오네. 제이슨 = 중괄호 있고, 키값에 밸류있고, 큰따옴표있고.
//    근데 비밀번호는 나오면 안되니까. 이그노어어노테이션
//    transient 를 붙이면, 서버단에서 제거를 해버림. 이거할거면 이그노어 어노테이션 주석하자

    @GetMapping("ex02")
    public MemberDTO ex02(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(1L);
        memberDTO.setMemberEmail("test@gmail.com");
        memberDTO.setMemberPassword("1234");
        return memberDTO;
    }


//    리스트를 리턴해보자
@GetMapping("ex03")
public List<MemberDTO> ex03(){
    List<MemberDTO> members = new ArrayList<>();
    MemberDTO memberDTO1 = new MemberDTO();
    MemberDTO memberDTO2 = new MemberDTO();

    memberDTO1.setId(1L);
    memberDTO1.setMemberEmail("test@gmail.com");
    memberDTO1.setMemberPassword("1234");

    memberDTO2.setId(2L);
    memberDTO2.setMemberEmail("test2@gmail.com");
    memberDTO2.setMemberPassword("12345");

    members.add(memberDTO1);
    members.add(memberDTO2);

    return members;
}
}
