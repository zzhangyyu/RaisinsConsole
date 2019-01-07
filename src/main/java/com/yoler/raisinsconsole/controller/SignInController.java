package com.yoler.raisinsconsole.controller;

import com.yoler.raisinsconsole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 管理端用户controller
 */
@Controller
@RequestMapping(value = "/console/")
public class SignInController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "signInPage", method = RequestMethod.GET)
    public String signInPage() {
        return "signIn";
    }

    @RequestMapping(value = "welcomePage", method = RequestMethod.POST)
    public String welcome(@RequestParam String userName, @RequestParam String password, Model model, HttpServletRequest request) {
        Map<String, Object> signInResult = userService.signIn(userName, password);
        String status = (String) signInResult.get("status");
        String msg = (String) signInResult.get("msg");
        if ("200".equals(status)) {
            request.getSession().setAttribute("userName", userName);
            request.getSession().setAttribute("password", password);
            return "welcome";
        } else if ("500".equals(status)) {
            model.addAttribute("errorMsg", msg);
            return "signIn";
        } else {
            model.addAttribute("errorMsg", "服务器错误");
            return "signIn";
        }
    }


}
