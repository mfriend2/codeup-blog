package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    int add(int a, int b) {
        return a + b;
    }

    int subtract(int a, int b) {
        return a - b;
    }

    int multiply(int a, int b) {
        return a * b;
    }

    int divide(int a, int b) {
        return a / b;
    }

    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public String addNumbers(@PathVariable int a, @PathVariable int b) {
        return String.format("%d + %d = %d", a, b, add(a, b));
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public String subtractNumbers(@PathVariable int a, @PathVariable int b) {
        return String.format("%d - %d = %d", a, b, subtract(a, b));
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String multiplyNumbers(@PathVariable int a, @PathVariable int b) {
        return String.format("%d * %d = %d", a, b, multiply(a, b));
    }

    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public String divideNumbers(@PathVariable int a, @PathVariable int b) {
        return String.format("%d / %d = %d", a, b, divide(a, b));
    }
}
