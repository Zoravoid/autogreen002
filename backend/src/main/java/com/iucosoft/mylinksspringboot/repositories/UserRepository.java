package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.personDetails FROM User u WHERE u.id = :userId")
    Optional<PersonDetails> findPersonDetailsByUserId(final @Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(final @Param ("username") String username);

    @Query("SELECT COUNT(l) > 0 FROM Link l WHERE l.owner.id = :id")
    boolean hasLinks(Long id);

    @Query("SELECT u.userStatus FROM User u WHERE u.id = :userId")
    UserStatus findUserStatusByUserId(@Param("userId") Long userId);
}
