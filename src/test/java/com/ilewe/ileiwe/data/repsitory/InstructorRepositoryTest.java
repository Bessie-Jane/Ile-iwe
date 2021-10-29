package com.ilewe.ileiwe.data.repsitory;

import com.ilewe.ileiwe.data.model.Authority;
import com.ilewe.ileiwe.data.model.Instructor;
import com.ilewe.ileiwe.data.model.LearningParty;
import com.ilewe.ileiwe.data.model.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql("/ileiwe_db/insert.sql")
class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveInstructorAsLearningPartyTest(){
        //create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass",
                new Authority(Role.ROLE_INSTRUCTOR));
        //create and map an instructor with learning party
        Instructor instructor = Instructor.builder()
                    .firstname("John")
                    .lastname("Alao")
                    .learningParty(user).build();

        //save instructor
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);
    }

    @Test
    void InstructorFieldsCannotBeNullTest(){
        LearningParty learningParty = new LearningParty("JideCole@gmail.com", "jide123", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname(null)
                .lastname(null)
                .learningParty(learningParty).build();

        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void InstructorCannotHaveEmptyValues(){
        LearningParty learningParty = new LearningParty("", "", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("Jide")
                .lastname("Cole")
                .learningParty(learningParty).build();

        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void InstructorCannotRegisterWithTheSameEmail(){
        LearningParty learningParty = new LearningParty("mayorkunlove@gmail.com", "mayo0f1ag",
                 new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("Mckenzie")
                .lastname("Bezos")
                .learningParty(learningParty).build();

        instructorRepository.save(instructor);

        learningParty = new LearningParty("mayorkunlove@gmail.com", "mayo0f1ag",
                new Authority(Role.ROLE_INSTRUCTOR));

       Instructor instructor2 = Instructor.builder()
                .firstname("Mckenzie")
                .lastname("Bezos")
                .learningParty(learningParty).build();

        assertThrows(DataIntegrityViolationException.class, () -> instructorRepository.save(instructor2));
    }

    @Test
    void InstructorTableCanBeUpdated(){
        LearningParty learningParty = new LearningParty("lovieOkum@gmail.com", "lovie123",
                new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor3 = Instructor.builder()
                .firstname("Lovie")
                .lastname("Okum")
                .learningParty(learningParty).build();

        instructorRepository.save(instructor3);

        instructor3.setBio("Passionate about imparting knowledge");
        instructorRepository.save(instructor3);

        Optional<Instructor> foundInstructor = instructorRepository.findById(instructor3.getId());

        //Instructor instructor3 = instructorRepository.findById(instructor3.getId().describeConstable().orElse(null));
        assertThat(foundInstructor.get().getBio()).isEqualTo(instructor3.getBio());
    }



    @AfterEach
    void tearDown() {
    }
}