package com.example.jpaintrohungarian.repository;

import com.example.jpaintrohungarian.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepozitory extends JpaRepository<Address, Long> {
}
