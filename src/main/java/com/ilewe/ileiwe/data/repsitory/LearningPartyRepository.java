package com.ilewe.ileiwe.data.repsitory;

import com.ilewe.ileiwe.data.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

    LearningParty findByEmail(String email);

//    @Query
//            ("Select '*' from LearningParty " + "as L where L.email =:email")
//    LearningParty findUserByEmail(String email);


}
