package org.prgrms.kyu.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.StoreCreateRequest;
import org.prgrms.kyu.dto.UserInfo;
import org.prgrms.kyu.service.SecurityService;
import org.prgrms.kyu.service.StoreService;
import org.prgrms.kyu.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
@RequiredArgsConstructor
public class StoreController {
  private final StoreService storeService;
  private final SecurityService securityService;
  private final UserService userService;

  @PostMapping("/store")
  public String signUp(@ModelAttribute("storeCreateForm") StoreCreateRequest storeCreateRequest,
                       Authentication authentication)
      throws AuthenticationException {
    if (!securityService.isAuthenticated()) throw new AuthenticationException();
    final UserInfo userInfo = userService.getUser(authentication.getName());
    storeService.save(storeCreateRequest, userInfo.getId());
    return "redirect:/";
  }
}
