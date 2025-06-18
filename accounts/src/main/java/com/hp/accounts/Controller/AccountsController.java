package com.hp.accounts.Controller;


import com.hp.accounts.Constants.AccountsConstant;
import com.hp.accounts.Dto.CustomerDto;
import com.hp.accounts.Dto.ResponseDto;
import com.hp.accounts.Services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {


    public IAccountService  accountService;

    @GetMapping("SayHello")
    private String sayHello(){
        return  "Say Hello World!";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201,AccountsConstant.MESSAGE_201));
    }


}
