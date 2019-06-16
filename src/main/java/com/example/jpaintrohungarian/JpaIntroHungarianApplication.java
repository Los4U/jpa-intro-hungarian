package com.example.jpaintrohungarian;

import com.example.jpaintrohungarian.entity.Address;
import com.example.jpaintrohungarian.entity.Student;
import com.example.jpaintrohungarian.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class JpaIntroHungarianApplication {

    @Autowired
    public StudentRepository studentRepository;

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

            Student mike =  Student.builder()
                    .email("email")
                    .name("Mike")
                    .address(Address.builder().city("Misko").country("Poland").address("Adv").zipCode(432).build())
                    .birthDay(LocalDate.of(444, 1, 2))
                    .phoneNumber("4343242")
                    .phoneNumber("543")
                    .phoneNumber("7658")
                    .build();

            studentRepository.save(mike);

            //studentRepository.save(john);
        };
    }


}
