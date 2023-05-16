package com.krillinator.springSecurityLektion.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.krillinator.springSecurityLektion.rats.Rat;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

    // Lägg till en råtta till användarens lista med råttor
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.rats = :rats WHERE u.id = :userId")
    void addRatToList(@Param("userId") Long userId, @Param("rats") List<Rat> rats);

    // Hämta alla råttor för en användare
    @Query("SELECT u.rats FROM UserModel u WHERE u.id = :userId")
    List<Rat> getRatsForUser(@Param("userId") Long userId);

    default void addRatToUser(Rat rat, UserModel user) {
        user.getRats().add(rat); // Add the new Rat to the user's list of rats
        rat.setOwner(user); // Set the user of the new Rat
        save(user); // Save the updated user
    }
    default void saveUserWithRat(UserModel userModel, Rat rat) {
        userModel.addRat(rat);
        save(userModel);
    }

}
