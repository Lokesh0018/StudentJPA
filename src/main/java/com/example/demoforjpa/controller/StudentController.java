package com.example.demoforjpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demoforjpa.entity.Student;
import com.example.demoforjpa.repo.StudentRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    
    @PostMapping("/api/student")
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
    }

    @GetMapping("/api/student")    
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        Optional<Student> stu = studentRepo.findById(id);
        if(stu.isPresent())
            return new ResponseEntity<>(stu.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/student/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> stu = studentRepo.findById(id);
        if(stu.isPresent()){
            stu.get().setName(student.getName());
            stu.get().setEmail(student.getEmail());
            stu.get().setAddress(student.getAddress());
            return new ResponseEntity<>(studentRepo.save(stu.get()),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
