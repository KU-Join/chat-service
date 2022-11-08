package com.hw.chatservice.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFeignService {

    private final MemberFeignClient memberFeignClient;

    public ResponseEntity validateUser(String email) {
        return memberFeignClient.validateUser(email);
    }

}
