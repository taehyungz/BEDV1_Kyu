package org.prgrms.kyu.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.JoinRequest;
import org.prgrms.kyu.service.SecurityService;
import org.prgrms.kyu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/user/signup")
    public String signUp(Model model) {
        if (securityService.isAuthenticated()) return "redirect:/";
        model.addAttribute("joinForm", new JoinRequest());
        return "/user/signUpForm";
    }

    @PostMapping("/user/signup")
    public String signUp(@ModelAttribute("joinForm") JoinRequest joinRequest) {
        userService.join(joinRequest);
        userService.autoLogin(joinRequest);
        return "redirect:/";
    }

}
