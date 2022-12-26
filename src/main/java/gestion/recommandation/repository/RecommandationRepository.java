package gestion.recommandation.repository;


import gestion.recommandation.model.Recommandation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecommandationRepository extends JpaRepository<Recommandation, Long> {

    @Query(value = "SELECT r.* "
            + " FROM recommandation r,profile p "
            + " WHERE r.profile_id=p.id and"
            + " p.user_id= ?1"
            ,nativeQuery = true)
    List<Recommandation> mesRecommandationRecu(Long id);

    @Query(value = "SELECT r.* "
            + " FROM recommandation r,user_recommandations ur "
            + " WHERE r.id=ur.recommandations_id and"
            + " ur.user_id= ?1"
            ,nativeQuery = true)
    List<Recommandation> mesRecommandationDonnee(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete  "
            + " FROM user_recommandations "
            + " WHERE recommandations_id = ?1 and"
            + " user_id= ?2"
            ,nativeQuery = true)
    void SupprimerRec(Long idc,Long idu);




}
