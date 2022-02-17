package com.spring.SpringbootAllArchetype.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MessageDto {

    boolean isOk = true;
    String message = "SUCCESS";

    public MessageDto(boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }
}
