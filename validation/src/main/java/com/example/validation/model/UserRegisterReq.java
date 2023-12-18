package com.example.validation.model;

import com.example.validation.annotation.phoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterReq {

    /*@NotNull    //!= null
    @NotBlank   //!= null && != ""
    @NotEmpty   //!= null && != "" && != " "*/
    private String name;

    private String nickName;

    @NotBlank
    @Size(min = 1, max = 12)
    private String passWord;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer age;

    @Email
    private String email;

    //@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 양식이 아닙니다")
    @phoneNumber
    private String pNum;

    @FutureOrPresent
    private LocalDateTime registerAt;

    @AssertTrue(message = "name or nickname은 존재해야 합니다")
    public boolean isNameChk(){

        if(Objects.nonNull(name) && !name.isBlank()){
            return true;
        }

        if(Objects.nonNull(nickName) && !nickName.isBlank()){
            return true;
        }
        return false;
    }
}
