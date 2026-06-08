package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.UsersDTO;
import lk.ijse.AAD.entity.Users;
import lk.ijse.AAD.enumaration.UserStatus;
import lk.ijse.AAD.repository.UserRepository;
import lk.ijse.AAD.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersDTO saveUser(UsersDTO usersDTO) {

        try{
            log.info("Attempting to save a new user to the database");
            Users user = new Users();
            user.setFirstName(usersDTO.getFirstName());
            user.setLastName(usersDTO.getLastName());
            user.setDob(new Date());
            user.setStatus(UserStatus.ACTIVE);

            Users savedUser = userRepository.save(user);

            log.info("User saved with ID: {}", savedUser.getId());

            UsersDTO usersDTO1 = new UsersDTO();
            usersDTO1.setId(savedUser.getId());
            usersDTO1.setFirstName(savedUser.getFirstName());
            usersDTO1.setLastName(savedUser.getLastName());
            usersDTO1.setDob(savedUser.getDob());
            usersDTO1.setStatus(savedUser.getStatus());

            return usersDTO1;
        } catch (Exception e) {
            log.error("Error occurred while saving user: {}", e.getMessage());
            throw new RuntimeException(e);
        }





    }

    @Override
    public List<UsersDTO> getUsers() {
        log.info("Fetching all users from the database");
        try{

            List<Users> usersList = userRepository.findAll();
            log.info("Total users fetched: {}", usersList.size());

            return usersList.stream().map(user -> {
                UsersDTO usersDTO = new UsersDTO();
                usersDTO.setId(user.getId());
                usersDTO.setFirstName(user.getFirstName());
                usersDTO.setLastName(user.getLastName());
                usersDTO.setDob(user.getDob());
                usersDTO.setStatus(user.getStatus());
                return usersDTO;
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsersDTO getUserDetails(long id) {
        log.info("Fetching user details for user ID: {}", id);
        try {
            Optional<Users> optionalUser = userRepository.findById(id);
            if(!optionalUser.isPresent()){
               throw new RuntimeException("User not found with ID: " + id);
            }
            log.info("User found: {}", optionalUser);
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(optionalUser.get().getId());
            usersDTO.setFirstName(optionalUser.get().getFirstName());
            usersDTO.setLastName(optionalUser.get().getLastName());
            usersDTO.setDob(optionalUser.get().getDob());
            usersDTO.setStatus(optionalUser.get().getStatus());
            return usersDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsersDTO updateUser(UsersDTO usersDTO) {
        try {
            log.info("Attempting to update user with ID: {}", usersDTO.getId());
            Optional<Users> optionalUser = userRepository.findById(usersDTO.getId());
            if (!optionalUser.isPresent()) {
                throw new RuntimeException("User not found with ID: " + usersDTO.getId());
            }
            Users user = optionalUser.get();
            user.setFirstName(usersDTO.getFirstName());
            user.setLastName(usersDTO.getLastName());
//            user.setDob(usersDTO.getDob());
            user.setStatus(usersDTO.getStatus());

            Users updatedUser = userRepository.save(user);
            log.info("User updated successfully with ID: {}", updatedUser.getId());

            UsersDTO updatedUserDTO = new UsersDTO();
            updatedUserDTO.setId(updatedUser.getId());
            updatedUserDTO.setFirstName(updatedUser.getFirstName());
            updatedUserDTO.setLastName(updatedUser.getLastName());
            updatedUserDTO.setDob(updatedUser.getDob());
            updatedUserDTO.setStatus(updatedUser.getStatus());

            return updatedUserDTO;
        } catch (Exception e) {
            log.error("Error occurred while updating user: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserStatus(UsersDTO usersDTO) {
        try {
            log.info("Attempting to update user status for user ID: {}", usersDTO.getId());
            Optional<Users> optionalUser = userRepository.findById(usersDTO.getId());
            if (!optionalUser.isPresent()) {
                throw new RuntimeException("User not found with ID: " + usersDTO.getId());
            }
            if(usersDTO.getStatus() == null){
                throw new RuntimeException("User status cannot be null for user ID: " + usersDTO.getId());
            }

            Users user = optionalUser.get();
            user.setStatus(usersDTO.getStatus());
            userRepository.save(user);
            log.info("User status updated successfully for user ID: {}", usersDTO.getId());
        } catch (Exception e) {
            log.error("Error occurred while updating user status: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(long id) {
        log.info("Attempting to delete user with ID: {}", id);
        try {
            Optional<Users> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()) {
                throw new RuntimeException("User not found with ID: " + id);
            }
            Users user = optionalUser.get();
            user.setStatus(UserStatus.DELETED);
            userRepository.save(user);
            log.info("User marked as deleted successfully for user ID: {}", id);
        } catch (Exception e) {
            log.error("Error occurred while deleting user: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
