package org.example.librarymanagement.user;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.common.BusinessException;
import org.example.librarymanagement.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AppUserRepository appUserRepository;

    @Transactional
    public UserResponse register(CreateUserRequest createUserRequest) {
        if (appUserRepository.existsByUsername(createUserRequest.username())) {
            throw new BusinessException("Username already exists");
        }
        AppUser appUser = AppUser.builder()
                .username(createUserRequest.username())
                .fullName(createUserRequest.fullName())
                .role(Role.MEMBER)
                .build();

        AppUser savedAppUser = appUserRepository.save(appUser);

        return UserResponse.from(savedAppUser);
    }

    @Transactional
    public UserResponse changeRole(Long id,ChangeRoleRequest changeRoleRequest) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));

        appUser.changeRole(Role.fromString(changeRoleRequest.role()));

//        appUserRepository.save(appUser);

        return UserResponse.from(appUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return appUserRepository.findAll().stream().map(UserResponse::from).toList();
    }
}
