package com.minji.vanillashop.global.exceptions;

public class MemberEmailDuplicateException extends RuntimeException {
    public MemberEmailDuplicateException(String message) {
        super(message);
    }
}
