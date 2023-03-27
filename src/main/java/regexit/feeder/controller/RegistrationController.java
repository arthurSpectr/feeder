package regexit.feeder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import regexit.feeder.domain.User;
import regexit.feeder.domain.UserDto;
import regexit.feeder.service.UserService;

import java.awt.*;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("usr", new UserDto());
        return "registration";
    }

    // Use DTO because somehow thymeleaf can't parse model, throw exception with id
    @PostMapping(value = "/registration")
    public String addUser(@ModelAttribute("usr") UserDto usr, Model model) {
        User newUser = new User();
        newUser.setUsername(usr.getUsername());
        newUser.setPassword(usr.getPassword());
        newUser.setEmail(usr.getEmail());

        if (!userService.addUser(newUser)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}