package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Faculty;
import mk.ukim.finki.wp.lab.model.exception.NotFoundInRepositoryException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements mk.ukim.finki.wp.lab.service.CourseService {
    private final CourseRepositoryDB courseRepository;

    public CourseService(CourseRepositoryDB courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllByFaculty(Faculty faculty) {
        return courseRepository.findAllByFaculty(faculty);
    }

    @Override
    public List<Course> findAllByOrderByNameAsc() {
        return courseRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Course> findAllByFacultyOrNameOrderByNameAsc(Faculty faculty, String name) {
        return courseRepository.findAllByFacultyOrNameOrderByNameAsc(faculty, name);
    }

    @Override
    public List<Course> findAllByFacultyOrderByNameAsc(Faculty faculty) {
        return courseRepository.findAllByFacultyOrderByNameAsc(faculty);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course findByCode(String code) {
        return courseRepository.findByCode(code);
    }


}
