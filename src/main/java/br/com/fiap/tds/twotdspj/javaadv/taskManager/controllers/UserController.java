package br.com.fiap.tds.twotdspj.javaadv.taskManager.controllers;

import br.com.fiap.tds.twotdspj.javaadv.taskManager.domainmodel.Task;
import br.com.fiap.tds.twotdspj.javaadv.taskManager.domainmodel.TaskPriority;
import br.com.fiap.tds.twotdspj.javaadv.taskManager.domainmodel.TaskStatus;
import br.com.fiap.tds.twotdspj.javaadv.taskManager.domainmodel.User;
import br.com.fiap.tds.twotdspj.javaadv.taskManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("user", new User());
        return  "users/form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "users/form";
        }
        this.userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable("id") UUID id){
        this.userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/view/{id}")
    public String viewTask(@PathVariable("id") UUID id, Model model){
        User user = this.userService.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Task not found " + id.toString()
                )
        );
        String passwordHidden = "*".repeat(user.getPassword().length());
        model.addAttribute("user", user);
        model.addAttribute("lengthPassword", passwordHidden);
        return "users/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model){
        User newUser = this.userService.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Task not found " + id.toString()
                )
        );
        model.addAttribute("user", newUser);
        return "users/form";
    }
}
