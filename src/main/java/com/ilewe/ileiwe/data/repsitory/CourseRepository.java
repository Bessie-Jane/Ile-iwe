package com.ilewe.ileiwe.data.repsitory;

import com.ilewe.ileiwe.data.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
