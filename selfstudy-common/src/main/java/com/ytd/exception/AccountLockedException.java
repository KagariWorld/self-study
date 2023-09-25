package com.ytd.exception;

public class AccountLockedException extends BaseException{
    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }
}
