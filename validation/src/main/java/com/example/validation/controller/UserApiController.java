package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterReq;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @PostMapping("")
    public Api<UserRegisterReq> register(
            @Valid
/*{
    "name" : "",
    "age" : 20,
    "email" : "",
    "pNum" : "",
    "register_at" : "2023-12-05T16:17:00"
}*/

//에러체크 예시
/*[Field error in object 'userRegisterReq' on field 'passWord': rejected value [null];
codes [NotBlank.userRegisterReq.passWord,NotBlank.passWord,NotBlank.java.lang.String,NotBlank];
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRegisterReq.passWord,passWord];
arguments []; default message [passWord]]; default message [공백일 수 없습니다]]
*/
            @RequestBody Api<UserRegisterReq> userRegisterReq
    ){
        log.info("init : {}", userRegisterReq);


        var body = userRegisterReq.getData();
        Api<UserRegisterReq> res = Api.<UserRegisterReq>builder()
                .rstCode(String.valueOf(HttpStatus.OK.value()))
                .rstMsg(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();
        return res;
    }
}
