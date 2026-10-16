package org.example.librarymanagement.user;

import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest( @NotNull String role) {
}
