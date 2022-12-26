package gestion.recommandation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import gestion.recommandation.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
