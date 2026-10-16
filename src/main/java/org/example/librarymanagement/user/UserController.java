package org.example.librarymanagement.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Validated @RequestBody CreateUserRequest request) {
        UserResponse register = userService.register(request);
        return ResponseEntity.ok(register);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PatchMapping("{id}/role")
    public ResponseEntity<UserResponse> changeRole(@PathVariable String id, @Validated @RequestBody ChangeRoleRequest request) {
        return ResponseEntity.ok().body(userService.changeRole(Long.parseLong(id),request));
    }

}
