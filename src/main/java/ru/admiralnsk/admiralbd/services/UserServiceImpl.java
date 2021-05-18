package ru.admiralnsk.admiralbd.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.admiralnsk.admiralbd.models.Role;
import ru.admiralnsk.admiralbd.models.User;
import ru.admiralnsk.admiralbd.repository.UserRepository;

import java.util.List;

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
        User userToSave = new User();
        userToSave.setLogin(user.getLogin());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userToSave.setName(user.getName());
        userToSave.setRole(user.getRole());
        userToSave.setStatus(user.getStatus());
        userRepository.save(userToSave);
    }

    @Override
    public void updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            User userToUpdate = userRepository.getOne(user.getId());
            userToUpdate.setLogin(user.getLogin());
            if (!user.getPassword().isEmpty()) {
                userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userToUpdate.setName(user.getName());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setStatus(user.getStatus());
            userRepository.save(userToUpdate);
        } else {
            throw new UsernameNotFoundException("No user with such id");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
