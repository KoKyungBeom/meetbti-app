package com.springboot.exception;

import lombok.Getter;

public enum ExceptionCode {
    PASSWORD_MISMATCH(409,"Password Not Changed"),
    CONFIRM_PASSWORD_MISMATCH(400,"Passwords Do Not Match"),
    ACCESS_DENIED(403,"Access Denied"),
    MEMBER_NOT_FOUND(404,"Member Not Found"),
    POST_NOT_FOUND(404,"Post Not Found"),
    COMMENT_NOT_FOUND(404,"Comment Not Found"),
    TEST_RESULT_NOT_FOUND(404,"TestResult Not Found"),
    GAME_NOT_FOUND(404,"Game Not Found"),
    CONTENT_NOT_FOUND(404,"Content Not Found"),
    EMAIL_ALREADY_EXIST(409, "Email Already Exist"),
    NICKNAME_ALREADY_EXIST(409, "Nickname Already Exist"),
    INVALID_AUTHENTICATION_CODE(400, "Invalid Authentication Code");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode (int status, String message) {
        this.status = status;
        this.message = message;
    }
}
