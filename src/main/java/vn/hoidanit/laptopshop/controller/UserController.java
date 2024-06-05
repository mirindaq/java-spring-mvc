package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String getHomePage(Model model) {
    String text = this.userService.handleHello();
    return "hello";
  }

  @GetMapping("/admin/user/create")
  public String getCreatePage(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/create";
  }

  @GetMapping("/admin/user")
  public String getTableUsersPage(Model model) {
    List<User> listUsers = this.userService.getAllUser();
    model.addAttribute("listUser", listUsers);
    return "admin/user/list";
  }

  @PostMapping("/admin/user/create")
  public String createUserPage(@ModelAttribute("newUser") User user) {
    this.userService.handleSaveUser(user);
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/{id}")
  public String getUserDetailPage(Model model, @PathVariable Long id) {
    User user = this.userService.findUserById(id);
    model.addAttribute("user", user);
    return "admin/user/show";
  }

  @GetMapping("/admin/user/update/{id}")
  public String getUserUpdatePage(Model model, @PathVariable Long id) {
    User user = this.userService.findUserById(id);
    model.addAttribute("updateUser", user);
    return "admin/user/update";
  }

  @PostMapping("/admin/user/update/{id}")
  public String updateUserPage(@ModelAttribute("updateUser") User newUser) {
    User currentUser = this.userService.findUserById(newUser.getId());
    if (currentUser != null) {
      currentUser.setAddress(newUser.getAddress());
      currentUser.setEmail(newUser.getEmail());
      currentUser.setFullName(newUser.getFullName());
      currentUser.setPhone(newUser.getPhone());

      this.userService.handleSaveUser(currentUser);
    }
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/delete/{id}")
  public String getDeleteUserPage(Model model, @PathVariable Long id) {
    model.addAttribute("id", id);
    model.addAttribute("user", new User());
    return "admin/user/delete";
  }

  @PostMapping("/admin/user/delete")
  public String deleteUserPage(Model model, @ModelAttribute("user") User user) {
    this.userService.deleteById(user.getId());
    return "redirect:/admin/user";
  }

}
