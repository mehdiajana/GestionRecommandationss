package gestion.recommandation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import gestion.recommandation.model.Profile;
import gestion.recommandation.model.Recommandation;
import gestion.recommandation.model.User;
import gestion.recommandation.repository.ProfileRepository;
import gestion.recommandation.repository.RecommandationRepository;
import gestion.recommandation.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private ProfileRepository pr;
    @Autowired
    private RecommandationRepository rr;

    //***********************************************************************************************************************
    // Les Profiles
    //***********************************************************************************************************************
    //Liste de tous les Profiles
    @GetMapping("/profiles")
    public List<Profile> getProfiles()
    {
        List<Profile> profiless = new ArrayList<>();
        List<Profile> prof = pr.findAll();

        for(Profile p : prof)
        {
            String x = p.getEtat();
                if(Objects.equals(x, "Accepter"))
                {
                    profiless.add(p);
                    System.out.println("etat is : {}"+ p.getEtat());
                }

        }
        return profiless;
    }
    //Liste de tous les mes Profiles
    @GetMapping("/mesprofiles")
    public List<Profile> getMyProfiles(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());

        List<Profile> profiles = pr.findAll();
        List<Profile> mesprofiles= new ArrayList<>();;
        for(Profile p : profiles)
        {
            if(p.getUser().getId()==u.getId())
            {
                mesprofiles.add(p);
            }
        }
        return mesprofiles;
    }
    // Afficher le details d'un profile
    @GetMapping("/profile/{id}")
    public Profile getProfile(@PathVariable Long id){
        return pr.findById(id).get();
    }
    //Ajouter un profile
    @PostMapping("/addProfile")
    public Profile addProfile(@RequestBody Profile profile){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        profile.setEtat("En attente");
        profile.setUser(u);
        return pr.save(profile);
    }
    //supprimer un profile
    @PostMapping ("/deleteProfile/{id}")
    public void deleteProfile(@PathVariable Long id) {
        pr.deleteById(id);
    }
    //***********************************************************************************************************************
    // Les Recommandations
    //***********************************************************************************************************************

    //Liste de tous les Recommandations
    @GetMapping("/recommandations")
    public List<Recommandation> getRecommandations(){
        return rr.findAll();
    }
    // Afficher le details d'une recommandation
    @GetMapping("/recommandation/{id}")
    public Recommandation getRecommandation(@PathVariable Long id){
        return rr.findById(id).get();
    }
    //Ajouter un profile
    @PostMapping("/addRecommandation")
    public void AddRecommandation(@RequestBody Recommandation recommandation){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        Long idu= u.getId();
        Long idr = recommandation.getId();
        rr.save(recommandation);
        u.getRecommandations().add(recommandation);
        ur.save(u);

    }
    //Liste de tous mes recommandations ajouter
    @GetMapping("/mesRecommandationsGiven")
    public List<Recommandation> getMyRecommandationsDonnee(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        Long idd= u.getId();
        return rr.mesRecommandationDonnee(idd);
    }
    //Liste de tous mes recommandations Recu
    @GetMapping("/mesRecommandationsReceived")
    public List<Recommandation> getMyRecommandationsRecu(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        Long idd= u.getId();
        return rr.mesRecommandationRecu(idd);
    }
    //supprimer une Recommandation
    @PostMapping("/deleteRecommandation/{id}")
    public void deleteRecommandation(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        Long idd= u.getId();
        rr.SupprimerRec(id,idd);
        rr.deleteById(id);
    }




}
