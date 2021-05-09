package ru.admiralnsk.admiralbd.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.admiralnsk.admiralbd.models.User;
import ru.admiralnsk.admiralbd.services.UserService;

@AllArgsConstructor
@Controller
@PreAuthorize("hasAuthority('departures:write')")
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user-page";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable Long id, Model model) {
        userService.findUserById(id);
        model.addAttribute("user", userService.findUserById(id));
        return "edit-user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/new-user")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.putUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
