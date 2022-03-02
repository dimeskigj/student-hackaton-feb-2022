package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Faculty;

import java.util.List;
import java.util.Optional;

public interface CourseService{
    List<Course> findAll();
    List<Course> findAllByFaculty(Faculty faculty);
    List<Course> findAllByOrderByNameAsc();
    List<Course> findAllByFacultyOrNameOrderByNameAsc(Faculty faculty, String name);
    List<Course> findAllByFacultyOrderByNameAsc(Faculty faculty);
    void save(Course course);
    Course findByCode(String code);
}
