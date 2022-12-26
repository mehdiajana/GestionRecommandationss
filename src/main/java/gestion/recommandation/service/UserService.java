package gestion.recommandation.service;



import java.util.List;

import gestion.recommandation.model.Role;
import gestion.recommandation.model.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}