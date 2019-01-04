package com.yoler.raisinsconsole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理端用户controller
 */
@Controller
@RequestMapping(value = "/console/")
public class SignInController {
    /**
     * 用户登录页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "signInPage", method = RequestMethod.GET)
    public String signInPage(Model model) {
        return "signInPage";
    }
}
