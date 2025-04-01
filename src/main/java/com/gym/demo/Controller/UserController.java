package com.gym.demo.Controller;

import com.gym.demo.Model.User;
import com.gym.demo.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "APIs for managing users in the fitness app")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    @Operation(summary = "Create a new user", description = "Register a new user in the system")
    public ResponseEntity<User> createUser(@RequestBody @Parameter(description = "User details") User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // Get All Users
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get Users with Pagination & Sorting
    @GetMapping("/paginated")
    @Operation(summary = "Get paginated users", description = "Retrieve users with pagination and sorting options")
    public ResponseEntity<Page<User>> getUsersPaginated(
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number, default is 0") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Page size, default is 10") int size,
            @RequestParam(defaultValue = "id") @Parameter(description = "Sorting field, default is 'id'") String sortBy,
            @RequestParam(defaultValue = "asc") @Parameter(description = "Sorting order, default is ascending") String sortDirection) {
        
        return ResponseEntity.ok(userService.getUsersPaginatedAndSorted(page, size, sortBy, sortDirection));
    }

    // Get User by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their ID")
    public ResponseEntity<User> getUserById(@PathVariable @Parameter(description = "User ID") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update User
    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Modify an existing user's details")
    public ResponseEntity<User> updateUser(
            @PathVariable @Parameter(description = "User ID") Long id,
            @RequestBody @Parameter(description = "Updated user details") User user) {
        
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setAddress(user.getAddress());
            return ResponseEntity.ok(userService.saveUser(updatedUser));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete User
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Remove a user by their ID")
    public ResponseEntity<Void> deleteUser(@PathVariable @Parameter(description = "User ID") Long id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
