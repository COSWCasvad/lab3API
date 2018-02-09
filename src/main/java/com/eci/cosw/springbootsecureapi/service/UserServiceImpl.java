package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@Service
public class UserServiceImpl
    implements UserService
{

    private List<User> users = new ArrayList<>();


    @Autowired
    public UserServiceImpl()
    {
    }

    @PostConstruct
    private void populateSampleData()
    {
        users.add( new User( "xyz","test@mail.com", "password", "Andres", "Perez", "https://ams.educause.edu/eweb/upload/60283746.jpg" ) );
    }


    @Override
    public List<User> getUsers()
    {
        return users;
    }

    @Override
    public User getUser( String username )
    {
        User ans = null;
        for (int i =0;i<users.size() ;i++){
            if(users.get(i).getUsername().equals(username)){
                ans=users.get(i);
            }
        }
        return ans;
    }

    @Override
    public User createUser( User user ) throws ServletException
    {
        if(yaEstaRegistrado(user.getUsername())){
            throw new ServletException("El username ya se encuentra registrado en la base de datos");
        }
        users.add(user);
        return user;
    }

    private boolean yaEstaRegistrado(String username) {
        boolean ans = false;
        for (int i =0;i<users.size() && !ans ;i++){
            ans=users.get(i).getUsername().equals(username);
        }
        return ans;
    }

    @Override
    public User findUserByEmail( String email )
    {
        User ans=null;
        for (int i =0;i<users.size();i++){
            if(users.get(i).getEmail().equals(email)) ans=users.get(i);
        }
        return ans;
    }

    @Override
    public User findUserByEmailAndPassword( String email, String password )
    {
        return users.get( 0 );
    }

}
