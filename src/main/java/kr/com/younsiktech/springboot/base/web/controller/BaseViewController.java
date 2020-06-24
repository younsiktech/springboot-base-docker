package kr.com.younsiktech.springboot.base.web.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseViewController {

    protected String hashKey;

    public BaseViewController() {
        this.hashKey = String.valueOf(System.currentTimeMillis());
    }

    @ModelAttribute("hashKey")
    public String getHashKey() {
        return hashKey;
    }

}
