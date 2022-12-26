package gestion.recommandation.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import gestion.recommandation.model.Role;
import gestion.recommandation.model.User;
import gestion.recommandation.repository.RoleRepository;
import gestion.recommandation.repository.UserRepository;
import gestion.recommandation.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rr;

    @GetMapping("/login")
    public void login() {

    }
    @GetMapping("/profile")
    public String profile(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "profile";
    }
    @GetMapping("/inscription")
    public String inscription(Model m) {
        m.addAttribute("user", new User());
        return "inscription";
    }
    @GetMapping("/error")
    public String error(Model m) {
        m.addAttribute("user", new User());
        return "error";
    }
    @PostMapping("/inscription")
    public void inscription(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors())
        {

        }else
        {
            List<Role> roles = new ArrayList<>();
            roles.add(rr.findByName("USER"));
            user.setRoles(roles);
            userService.saveUser(user);
        }
    }

    @GetMapping
    public String home(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "header";
    }

}
