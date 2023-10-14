package com.jobs.springsecurity.api.login;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
public class LoginViewController {
    private LoginService loginService;

    @RequestMapping(value = "/")
    public String dashboard(Model model){
        return "index";
    }

    @RequestMapping(value = "/access-denied")
    public String accessDenied(Model model){
        return "access-denied";
    }

    @RequestMapping(value = "/user/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("principal", principal.getName());
        return "dashboard";
    }

    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard(Model model, Principal principal){
        model.addAttribute("principal", principal.getName());
        return "admin-dashboard";
    }
}
