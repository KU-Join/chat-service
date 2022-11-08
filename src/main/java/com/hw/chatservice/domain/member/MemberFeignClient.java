package com.hw.chatservice.domain.member;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MEMBER-SERVICE", url = "52.79.246.49:8000")
public interface MemberFeignClient {

    @GetMapping("/member-service/validate")
    ResponseEntity validateUser(@RequestParam("email") String email);

}
