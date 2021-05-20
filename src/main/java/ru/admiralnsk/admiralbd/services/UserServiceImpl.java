package ru.admiralnsk.admiralbd.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.admiralnsk.admiralbd.models.Role;
import ru.admiralnsk.admiralbd.models.User;
import ru.admiralnsk.admiralbd.repository.UserRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No user with such id"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void putUser(User user) {
        User newUser = new User();
        userRepository.save(this.initUser(newUser,user));
    }

    @Override
    public void updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            User userToUpdate = userRepository.getOne(user.getId());
            userRepository.save(this.initUser(userToUpdate,user));
        } else {
            throw new UsernameNotFoundException("No user with such id");
        }
    }

    public User initUser(User newUser,User user) {
        newUser.setLogin(user.getLogin());
        if (!user.getPassword().isEmpty()) {
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        newUser.setName(user.getName());
        newUser.setRole(user.getRole());
        newUser.setStatus(user.getStatus());
        return newUser;
    }
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
