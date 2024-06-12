package vn.hoidanit.laptopshop.domain.dto;

import vn.hoidanit.laptopshop.service.validator.RegisterChecked;
import vn.hoidanit.laptopshop.service.validator.StrongPassword;

@RegisterChecked
public class RegisterDTO {
  private String firstName;
  private String lastName;
  private String email;

  @StrongPassword(message = "Password phải tối thiểu có 8 ký tự, bao gồm chữ hoa, chữ thường và kí tự đặc biệt")
  private String password;
  private String confirmPassword;

  @Override
  public String toString() {
    return "RegisterDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
        + password + ", confirmPassword=" + confirmPassword + "]";
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

}
