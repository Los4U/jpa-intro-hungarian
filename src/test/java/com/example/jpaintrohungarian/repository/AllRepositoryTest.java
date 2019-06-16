package com.example.jpaintrohungarian.repository;

import com.example.jpaintrohungarian.entity.Address;
import com.example.jpaintrohungarian.entity.Location;
import com.example.jpaintrohungarian.entity.School;
import com.example.jpaintrohungarian.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    private  StudentRepository studentRepository;

    @Autowired
    private  AddressRepozitory addressRepozitory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void saveOneSimple(){
        Student john = Student.builder()
                .email("emai")
                .name("John")
                .build();

        studentRepository.save(john);

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(1);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueFieldTwice(){
        Student student = Student.builder()
                .email("jon@codec.pl")
                .name("John")
                .build();

        studentRepository.save(student);

        Student student2 = Student.builder()
                .email("jon@codec.pl")
                .name("Peter")
                .build();

        studentRepository.saveAndFlush(student2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void emailShoudlBeNotNull(){
        Student student2 = Student.builder()
                .name("Peter")
                .build();

        studentRepository.saveAndFlush(student2);
    }

    @Test
    public void transientIsNotSaved(){
        Student student2 = Student.builder()
                .birthDay(LocalDate.of(1987, 2, 12))
                .name("Peter")
                .email("en")
                .build();

        student2.calculateAge();
        assertTrue(student2.getAge() >= 31);
        studentRepository.save(student2);
        entityManager.clear();

        List<Student> students = studentRepository.findAll();
    }

    @Test
    public void addressIsPersistedWithStudent() {
        Address address = Address.builder()
                .city("Poland")
                .city("Budapest")
                .address("Kolejowa 2")
                .zipCode(43432)
                .build();

        Student student = Student.builder()
                .email("en")
                .address(address)
                .build();

        studentRepository.save(student);
        List<Address> addresses = addressRepozitory.findAll();


        assertThat(addresses)
                .hasSize(1)
                .allMatch(address1 -> address1.getId() > 0L);
    }

    @Test
    public void studentsArePersistedAndDeletedWithNewSchool(){

        Set<Student> students = IntStream.range(1, 10)
                .boxed()
                .map(integer -> Student.builder().email("student" + integer + "@codecool.ok").build())
                .collect(Collectors.toSet());

        School school = School.builder()
                .students(students)
                .location(Location.BUDAPEST)
                .build();

        schoolRepository.save(school);
        assertThat(studentRepository.findAll())
                .hasSize(9)
                .anyMatch(student -> student.getEmail().equals("student9@codecool.ok"));


        schoolRepository.deleteAll();
        assertThat(studentRepository.findAll())
                .hasSize(0);
    }











}