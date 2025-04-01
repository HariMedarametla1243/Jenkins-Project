package com.haribabu.RestAPI.with.Mysql.with.JPA.Demo.Controller;

import com.haribabu.RestAPI.with.Mysql.with.JPA.Demo.Repository.StudentRepository;
import com.haribabu.RestAPI.with.Mysql.with.JPA.Demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);

    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudents(){
       return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        Optional<Student> student= studentRepository.findById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id,@RequestBody Student std){
        Optional<Student> student= studentRepository.findById(id);
        if(student.isPresent()){
            student.get().setStudentAddress(std.getStudentAddress());
            student.get().setStudentEmail(std.getStudentEmail());
            student.get().setStudentName(std.getStudentName());
            return new ResponseEntity<>(studentRepository.save(student.get()),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        Optional<Student> student= studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
