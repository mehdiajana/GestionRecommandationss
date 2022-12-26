package gestion.recommandation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gestion.recommandation.model.User;
import gestion.recommandation.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User Get(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }
    @PostMapping
    public User Get(@RequestParam("username") String username){
        return userRepository.findUserByUsername(username);
    }



    @DeleteMapping("/{id}")
    public void Delete(@PathVariable("id") Long id){
         userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void Update(@PathVariable("id") Long id , @RequestBody User user){
        User u = new User();
        u=userRepository.findById(id).get();
        if (user.getNom()!=null)
            u.setNom(user.getNom());
        if (user.getPrenom()!=null)
         u.setPrenom(user.getPrenom());
        if (user.getUsername()!=null)
            u.setUsername(user.getUsername());
        userRepository.save(u);



    }




}
