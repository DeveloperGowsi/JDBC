package com.jdbc;

import java.util.List;

public interface UserDAo {
   int insertUser(User user);
   List<User> getAllUsers();
   User getUserById(int id);
   int deleteUserById(int id);
   int updateUserById(int id,String address);
   
}
