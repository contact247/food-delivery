package com.example.swiggato.repositories;

import com.example.swiggato.enums.Gender;
import com.example.swiggato.models.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        public Customer findByMobileNo(String mobileNo);
        public List<Customer> findByGender(Gender gender);
}
