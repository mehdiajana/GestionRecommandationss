package gestion.recommandation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.recommandation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String Username);

}