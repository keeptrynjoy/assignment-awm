package com.example.assignment.commons.interceptor;

import com.example.assignment.domain.Users;
import com.example.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public Boolean isAuthorized(String loginId, Auth auth) throws AccessDeniedException {
        Users user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 로그인 정보가 없습니다.."));

        if(!auth.role().equals(user.getRole())){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return true;
    }
}
