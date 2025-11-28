package com.Etech.census.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Etech.census.Respository.MemberRepository;
import com.Etech.census.entity.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Total members
    public long countAll() {
        return memberRepository.count();
    }

    // Recent members (limit 3 for dashboard)
    public List<Member> findRecent(int limit) {
        return memberRepository.findTop3ByOrderByIdDesc();
    }
}