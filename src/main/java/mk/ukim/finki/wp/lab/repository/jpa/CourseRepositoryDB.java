package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryDB extends JpaRepository<Course, String> {
    List<Course> findAllByFaculty(Faculty faculty);
    List<Course> findAllByOrderByNameAsc();
    List<Course> findAllByFacultyOrderByNameAsc(Faculty faculty);
    List<Course> findAllByFacultyOrNameOrderByNameAsc(Faculty faculty, String name);
    Course findByCode(String code);
}
