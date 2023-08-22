package com.app.library.repository;

import com.app.library.exception.object.ResourceNotFoundException;
import com.app.library.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE %:keyword% OR " +
            "LOWER(u.lastName) LIKE %:keyword% OR " +
            "LOWER(u.email) LIKE %:keyword% OR " +
            "LOWER(u.username) LIKE %:keyword%")
    Page<User> searchUsers(@Param("keyword") String keyword, Pageable pageable);



    default User getUser(User currentUser) {
        return getUserByName(currentUser.getUsername());
    }

    default User getUserByName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }


}
