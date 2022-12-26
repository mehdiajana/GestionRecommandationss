package gestion.recommandation.controller;


import gestion.recommandation.repository.ProfileRepository;
import gestion.recommandation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gestion.recommandation.model.Categorie;
import gestion.recommandation.model.Profile;
import gestion.recommandation.model.User;
import gestion.recommandation.repository.CategorieRepository;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private ProfileRepository pr;
    @Autowired
    private CategorieRepository cr;
    //***********************************************************************************************************************
    // Les Categories
    //***********************************************************************************************************************
    //supprimer une Categorie
    @GetMapping("/deleteCategorie/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        cr.deleteById(id);
    }
    //Ajouter une Categorie
    @PostMapping("/addCategorie")
    public Categorie AddCategorie(@RequestBody Categorie categorie)
    {
        return cr.save(categorie);
    }
    //Liste de tous les categories
    @GetMapping("/categories")
    public List<Categorie> getCategories(){
        return cr.findAll();
    }
    //Modifier une Categorie
    @PutMapping ("/ModifyCategorie/{id}")
    public Categorie AddCategorie(@PathVariable Long id,@RequestBody Categorie categorie){
        Categorie c= cr.findById(id).get();
        c.setNom(categorie.getNom());
        return cr.save(c);
    }
    //***********************************************************************************************************************
    // Les profiles
    //***********************************************************************************************************************
    //Liste de tous les Profiles
    @GetMapping("/profiles")
    public List<Profile> getProfiles(){
        return pr.findAll();
    }

    //accepter un profile
    @GetMapping("/acceptProfile/{id}")
    public void accepter(@PathVariable Long id) {
        Profile p = pr.findById(id).get();
        p.setEtat("Accepter");
        pr.save(p);
    }


    //refuser un profile
    @GetMapping("/rejectProfile/{id}")
    public void refuser(@PathVariable Long id) {
        Profile p = pr.findById(id).get();
        p.setEtat("Refuser");
        pr.save(p);
    }





    //***********************************************************************************************************************
    // Les Utilisateurs
    //***********************************************************************************************************************
    //Liste de tous les Utilisateurs
    @GetMapping("/users")
    public List<User> getUseeers(){
        return ur.findAll();
    }





}
