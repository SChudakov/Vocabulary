package com.sschudakov.web.registration.repository;

import com.sschudakov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public interface UserRepository
        extends JpaRepository<User, Integer>, IUserRepository {

    User findByUserName(String userName);

    User findByUserEmail(String email);
}



