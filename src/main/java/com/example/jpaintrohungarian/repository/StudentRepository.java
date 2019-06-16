package com.example.jpaintrohungarian.repository;

import com.example.jpaintrohungarian.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameStartingWithOrBirthDayBetween(String name, LocalDate from, LocalDate to);

    @Query("SELECT distinct s.address.country from Student s")
    List<String> findAllCountry();
}
