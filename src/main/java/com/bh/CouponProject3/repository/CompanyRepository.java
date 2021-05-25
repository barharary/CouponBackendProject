package com.bh.CouponProject3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bh.CouponProject3.beans.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsByNameOrEmail(String Name, String Email);

	Optional<Company> findOneByNameOrEmail(String Name, String Email);

	boolean existsByEmailAndPassword(String Email, String Password);

	Optional<Company> findIdByEmailAndPassword(String Email, String Password);

	boolean existsByIdAndName(int Id, String Name);

	boolean existsByEmail(String email);


}
