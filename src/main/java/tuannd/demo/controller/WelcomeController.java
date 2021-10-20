package tuannd.demo.controller;

import tuannd.demo.data.model.User;
import tuannd.demo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/403")
    public  String error(){
        return "error";
    }

    @PostMapping("/signup")
    public String registter(@ModelAttribute("user") User user, Model model){
        String warning= "";
        boolean flag = userService.register(user);
        if(flag){
            warning = "Register successfully";
        }else {
            warning = "Register fail";
        }
        model.addAttribute("warning",warning);
        model.addAttribute("flag",flag);
        return "redirect:/login";
    }

}
