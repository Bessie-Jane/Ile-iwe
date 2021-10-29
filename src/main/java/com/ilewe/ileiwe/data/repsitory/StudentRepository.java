package com.ilewe.ileiwe.data.repsitory;

import com.ilewe.ileiwe.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
