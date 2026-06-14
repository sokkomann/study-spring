package com.example.controller.controller.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private String name;
    private int korean;
    private int english;
    private int math;

    public int getTotal() {
        return korean + english +math;
    }

    public double getAverage() {
        return getTotal() / 3.0;
    }

}
