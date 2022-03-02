package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Faculty;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.service.impl.CourseService;
import mk.ukim.finki.wp.lab.service.impl.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final ReviewService reviewService;

    public CourseController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    @GetMapping(path = {"/list"})
        public String listAllCourses(@RequestParam(required = false) Faculty faculty,
                                     @RequestParam(required = false) String query,
                                     Model model) {
        List<Course> courses;
        if (faculty == null && query == null) {
            courses = courseService.findAll();
        } else {
            courses = courseService.findAllByFacultyOrderByNameAsc(faculty);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("faculties", Faculty.values());
        return "listCourses";
    }

    @GetMapping(path = {"/add"})
    public String addNewCourse(Model model) {
        model.addAttribute("faculties", Faculty.values());
        return "addCourse";
    }

    @PostMapping(path = {"/add_course"})
    public String addCourse(@RequestParam String name,
                            @RequestParam String code,
                            @RequestParam String url,
                            @RequestParam String teachers,
                            @RequestParam Faculty faculty) {
        courseService.save(new Course(
                code, faculty, name, teachers, url
        ));
        return "redirect:/courses/list";
    }

    @GetMapping(path = {"/info/{code}"})
    public String getCourseInfo(@PathVariable String code, Model model) {
        model.addAttribute("course", courseService.findByCode(code));
        model.addAttribute("reviews", courseService.findByCode(code).getReviews());
        model.addAttribute("courses", courseService.findAllByFacultyOrderByNameAsc(
                courseService.findByCode(code).getFaculty()
        ));
        return "infoCourse";
    }

    @PostMapping(path = {"/review"})
    public String addReview(@RequestParam String course, @RequestParam String review) {
        Review r = reviewService.save(new Review(review, LocalDateTime.now()));
        Course c = courseService.findByCode(course);
        c.getReviews().add(r);
        courseService.save(c);
        return "redirect:info/" + course;
    }

    @PostMapping(path = {"/prerequisite"})
    public String addPrerequisite(@RequestParam String prerequisite, @RequestParam String currentCourse) {
        Course c = courseService.findByCode(currentCourse), p = courseService.findByCode(prerequisite);
        if (!c.getCompetencies().contains(p))
            c.getCompetencies().add(p);
        courseService.save(c);
        return "redirect:info/" + currentCourse;
    }

    @GetMapping(path = {"/graph/{fax}"})
    public String getGraph(@PathVariable String fax, Model model) {
        model.addAttribute("fax", fax);
        return "graph-test";
    }

    @PostMapping(path = {"/graph"})
    public String getGraph(@RequestParam List<String> subjectCodes, Model model){
        model.addAttribute("fax", String.join(":", subjectCodes));
        return "graph-test";
    }
}
