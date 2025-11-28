package com.Etech.census.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Etech.census.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findTop3ByOrderByIdDesc();

}
