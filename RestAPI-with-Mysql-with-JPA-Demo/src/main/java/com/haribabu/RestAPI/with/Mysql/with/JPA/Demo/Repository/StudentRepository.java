package com.haribabu.RestAPI.with.Mysql.with.JPA.Demo.Repository;

import com.haribabu.RestAPI.with.Mysql.with.JPA.Demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
