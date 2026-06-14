package com.example.controller.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // controller 의 역할을 한다고 보고 보고의방식 두번쨰
@RequestMapping(value = "product/**")
@Slf4j
public class ProductDetailController {
    @GetMapping("product-detail")
    public String productDetail() {
        log.info("product-detail~~~~~~~~~~~~~~~~~");
        return "product/product-detail";
    }
}
