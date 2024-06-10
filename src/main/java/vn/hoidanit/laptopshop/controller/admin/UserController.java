package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.services.UploadFileService;
import vn.hoidanit.laptopshop.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private final UserService userService;
  private final UploadFileService uploadFileService;
  private final PasswordEncoder passwordEncoder;

  public UserController(UserService userService, UploadFileService uploadFileService,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.uploadFileService = uploadFileService;
    this.passwordEncoder = passwordEncoder;
  }

  // @GetMapping("/")
  // public String getHomePage(Model model) {
  // String text = this.userService.handleHello();
  // return text;
  // }

  @GetMapping("/admin/user/create")
  public String getCreatePage(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/create";
  }

  @GetMapping("/admin/user")
  public String getTableUsersPage(Model model) {
    List<User> listUsers = this.userService.getAllUser();
    model.addAttribute("listUser", listUsers);
    return "admin/user/show";
  }

  @PostMapping("/admin/user/create")
  public String createUserPage(@ModelAttribute("newUser") @Valid User user, BindingResult newUserBindingResult,
      @RequestParam("avatarFile") MultipartFile file) {

    List<FieldError> errors = newUserBindingResult.getFieldErrors();
    for (FieldError error : errors) {
      System.out.println(error.getField() + " - " + error.getDefaultMessage());
    }

    // validate
    if (newUserBindingResult.hasErrors()) {
      return "/admin/user/create";
    }
    //

    String avatar = this.uploadFileService.handleUploadFile(file, "avatar");
    String hashPassword = this.passwordEncoder.encode(user.getPassword());
    user.setPassword(hashPassword);
    user.setAvatar(avatar);
    user.setRole(this.userService.getRoleByName(user.getRole().getName()));
    this.userService.handleSaveUser(user);
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/{id}")
  public String getUserDetailPage(Model model, @PathVariable Long id) {
    User user = this.userService.findUserById(id);
    model.addAttribute("user", user);
    return "admin/user/detail";
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
