package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.graph.Link;
import mk.ukim.finki.wp.lab.model.graph.Node;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = {"/api"})
public class RestController {
    private final CourseService courseService;

    public RestController(CourseService courseService) {
        this.courseService = courseService;
    }

    private void traverseCourse(Set<Course> result, Course course) {
        if (course == null) return;
        if (result.contains(course)) return;
        result.add(course);
        List<Course> competencies = course.getCompetencies();
        if (!competencies.isEmpty()) {
            competencies.forEach(c -> traverseCourse(result, c));
        }
    }

    @GetMapping(path = "/", produces = "application/json")
    public Map<String, List<Object>> getNodes(@RequestParam(required = true) String subjectCodes) {
        List<Object> nodes = new ArrayList<>();
        List<Object> links = new ArrayList<>();

        List<String> codesList = Arrays.stream(subjectCodes.split(":")).collect(Collectors.toList());
        List<Course> wantedCourses = codesList.stream().map(courseService::findByCode).collect(Collectors.toList());

        Set<Course> toBeVisualized = new HashSet<>();
        wantedCourses.forEach(c -> traverseCourse(toBeVisualized, c));

        List<Course> list = new ArrayList<>(toBeVisualized);
        list.forEach(c -> nodes.add(new Node(
                c.getName(), c.getCode()
        )));
        list.forEach(c1 -> {
            if (!c1.getCompetencies().isEmpty()) {
                c1.getCompetencies().forEach(c2 ->
                        links.add(new Link(
                                list.indexOf(c2),
                                list.indexOf(c1),
                                1
                        ))
                );
            }
        });

        HashMap<String, List<Object>> result = new HashMap<>();
        result.put("nodes", nodes);
        result.put("links", links);
        return result;
    }
}
