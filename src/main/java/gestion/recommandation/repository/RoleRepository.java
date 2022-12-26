package gestion.recommandation.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import gestion.recommandation.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String Name);
}