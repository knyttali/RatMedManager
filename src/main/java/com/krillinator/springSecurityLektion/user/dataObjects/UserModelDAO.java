/* package com.krillinator.springSecurityLektion.user.dataObjects;

import com.krillinator.springSecurityLektion.rats.Rat;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component // Causes ERROR otherwise!!!
public class UserModelDAO implements IUserModelDAO {

    private final UserModelRepository userModelRepository;

    public UserModelDAO(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserModel findUser(String username) {
        return userModelRepository.findByUsername(username);
    }

   /*  @Override
    public void save(UserModel userModel) {
        userModelRepository.save(userModel); // Uses the save() method from above
    } */

  /*   @Override
    public void delete(UserModel userModel) {
        userModelRepository.delete(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userModelRepository.findAll();
    }
    public void saveUserWithRat(UserModel userModel, Rat rat) {
        userModel.addRat(rat);
        userModelRepository.save(userModel);
    }
}
 */ 