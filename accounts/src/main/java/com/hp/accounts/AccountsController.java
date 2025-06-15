package com.hp.accounts;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("SayHello")
    private String sayHello(){
        return  "Say Hello World!";
    }
}
