package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class UserController {

//   @RequestMapping("/")
//   public String getHomePage() {
//     return "Page for Controller";
//   }
// }

@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String getHomePage() {
    return this.userService.handleHello();
  }
}
