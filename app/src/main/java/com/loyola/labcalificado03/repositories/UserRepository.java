package com.loyola.labcalificado03.repositories;

import com.loyola.labcalificado03.models.User;
import com.orm.SugarRecord;

import java.util.List;

public class UserRepository {

    public static List<User> list(){
        List<User> users= SugarRecord.listAll(User.class);
        return users;
    }

    public static User read(Long id){
        User user = SugarRecord.findById(User.class,id);
        return user;
    }

    public static void create(String fullname, String username, String email, String password){
        User user = new User(fullname,username,email,password);
        SugarRecord.save(user);
    }

    public static void update(Long id, String fullname, String username, String email, String password){
        User user = SugarRecord.findById(User.class,id);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class,id);
        SugarRecord.delete(user);
    }

    public static User Login(String username, String password){
        List <User> user= SugarRecord.find(User.class,"username=? and password=?",username,password);

        if(!user.isEmpty()){
            return user.get(0);
        }
        return null;
    }

    public static User Load(Long id){
        User user = SugarRecord.findById(User.class,id);
        return user;
    }

}
