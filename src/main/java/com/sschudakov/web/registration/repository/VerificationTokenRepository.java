package com.sschudakov.web.registration.repository;

import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}



