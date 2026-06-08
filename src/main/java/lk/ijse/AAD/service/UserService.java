package lk.ijse.AAD.service;


import lk.ijse.AAD.dto.UsersDTO;

import java.util.List;

public interface UserService {
    UsersDTO saveUser(UsersDTO usersDTO);

    List<UsersDTO> getUsers();

    UsersDTO getUserDetails(long id);

    UsersDTO updateUser(UsersDTO usersDTO);

    void updateUserStatus(UsersDTO usersDTO);

    void deleteUser(long id);
}
