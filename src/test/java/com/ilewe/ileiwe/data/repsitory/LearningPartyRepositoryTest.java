package com.ilewe.ileiwe.data.repsitory;

import com.ilewe.ileiwe.data.model.Authority;
import com.ilewe.ileiwe.data.model.LearningParty;
import com.ilewe.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql("/ileiwe_db/insert.sql")
class LearningPartyRepositoryTest {
    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp(){

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void createLearningPartyWithStudentRoleTest(){
        LearningParty learningUser = new LearningParty("bessie@gmail.com", "123Pass321",
                                                          new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);
        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("bessie@gmail.com");
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("After saving -> {}", learningUser);
    }

    @Test
    void createLearningPartyUniqueEmailsTest(){
        //create a learning party
        LearningParty user1 = new LearningParty("yomi@gmail.com", "234Pass432",
                new Authority(Role.ROLE_STUDENT));
        //save to db
        learningPartyRepository.save(user1);
        assertThat(user1.getEmail()).isEqualTo("yomi@gmail.com");
        assertThat(user1.getId()).isNotNull();

        //create another learning party with same email
        LearningParty user2 = new LearningParty("yomi@gmail.com", "234Pass432",
                new Authority(Role.ROLE_STUDENT));

        // save and catch exception
        assertThrows(DataIntegrityViolationException.class, () -> learningPartyRepository.save(user2));
        //assert that exception was thrown
    }

    @Test
    void LearningPartyCannotBeNullTest(){
        LearningParty learningUser1 = new LearningParty(null, null, new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(learningUser1));
    }

    @Test
    void learningPartyWithEmptyStringValuesTest(){
        LearningParty learningUser2 = new LearningParty("", "", new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(learningUser2));
    }

    @Test
    @Transactional
    void findByUserNameTest(){
    LearningParty learningParty = learningPartyRepository.findByEmail("tomi@gmail.com");
        assertThat(learningParty).isNotNull();
        assertThat(learningParty.getEmail()).isEqualTo("tomi@gmail.com");
        log.info("Learning party object -> {}", learningParty);
    }

    @AfterEach
    void tearDown(){

    }

}