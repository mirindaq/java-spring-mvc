package vn.hoidanit.laptopshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String handleHello() {
    return "hello from service";
  }

  public User handleSaveUser(User user) {
    return this.userRepository.save(user);
  }

  public List<User> getAllUser() {
    return this.userRepository.findAll();
  }

  public List<User> getAllUsersByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public User findUserById(long id) {
    return this.userRepository.findById(id);
  }

}
