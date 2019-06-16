package com.example.jpaintrohungarian;

import com.example.jpaintrohungarian.entity.Address;
import com.example.jpaintrohungarian.entity.Location;
import com.example.jpaintrohungarian.entity.School;
import com.example.jpaintrohungarian.entity.Student;
import com.example.jpaintrohungarian.repository.SchoolRepository;
import com.example.jpaintrohungarian.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class JpaIntroHungarianApplication {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public SchoolRepository schoolRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaIntroHungarianApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init(){
        return args -> {
//            Student john =  Student.builder()
//                    .email("email")
//                    .name("John")
//                    .birthDay(LocalDate.of(2000, 2, 2))
//                    .build();

//            Student mike =  Student.builder()
//                    .email("email")
//                    .name("Mike")
//                    .address(Address.builder().city("Misko").country("Poland").address("Adv").zipCode(432).build())
//                    .birthDay(LocalDate.of(444, 1, 2))
//                    .phoneNumber("4343242")
//                    .phoneNumber("543")
//                    .phoneNumber("7658")
//                    .build();

//            studentRepository.save(mike);

            //studentRepository.save(john);

            Address address1 =  Address.builder()
                    .address("Street 32")
                    .city("Budapest")
                    .country("Hungary")
                    .build();

            Address address2 =  Address.builder()
                    .address("Kopernic")
                    .city("Miskolc")
                    .country("Hungary")
                    .build();

            Address address3 =  Address.builder()
                    .address("Wolska")
                    .city("Wawer")
                    .country("Hungary")
                    .build();

            Student john =  Student.builder()
                    .name("John")
                    .email("joh@codecool.ok")
                    .birthDay(LocalDate.of(1984, 2, 2))
                    .address(address1)
                    .phoneNumbers(Arrays.asList("423","53443"))
                    .build();

            Student mike =  Student.builder()
                    .name("Mike")
                    .email("mike@codecool.ok")
                    .birthDay(LocalDate.of(2001, 2, 2))
                    .address(address2)
                    .phoneNumbers(Arrays.asList("1111","1111443"))
                    .build();

            Student pawel =  Student.builder()
                    .name("Pawel")
                    .email("pawel@codecool.ok")
                    .birthDay(LocalDate.of(1999, 12, 12))
                    .address(address2)
                    .phoneNumbers(Arrays.asList("2222","2"))
                    .build();

            School school = School.builder()
                    .location(Location.BUDAPEST)
                    .name("Codecool Buda")
                    .student(john)
                    .student(mike)
                    .student(pawel)
                    .build();

//            john.setSchool(school);
//            mike.setSchool(school);
//            pawel.setSchool(school);


            schoolRepository.save(school);

        };
    }


}
