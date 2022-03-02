package mk.ukim.finki.wp.lab.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    private String code;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> competencies;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> reviews;
    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    public Course(String code, Faculty faculty, String name, String professors, String URL) {
        this.code = code;
        this.faculty = faculty;
        this.name = name;
        this.professors = professors;
        this.URL = URL;
    }

    public Course() {
    }

    private String name;
    private String professors;
    private String URL;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Course> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<Course> competencies) {
        this.competencies = competencies;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessors() {
        return professors;
    }

    public void setProfessors(String professors) {
        this.professors = professors;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
