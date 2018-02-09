package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.Todo;
import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@RestController
@RequestMapping( "user" )
public class UserController
{

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public ResponseEntity<?> login( @RequestBody User login )

    {
        String jwtToken = "";

        if ( login.getUsername() == null || login.getPassword() == null )
        {
            //throw new ServletException( "Please fill in username and password" );
            return new ResponseEntity<>("Please fill in username and password",HttpStatus.FORBIDDEN);
        }

        String username = login.getUsername();
        String password = login.getPassword();

        User user = userService.getUser( login.getUsername() );

        if ( user == null )
        {
            //throw new ServletException( "User username not found." );
            return new ResponseEntity<>("User username not found." ,HttpStatus.FORBIDDEN);
        }

        String pwd = user.getPassword();

        if ( !password.equals( pwd ) )
        {
            //throw new ServletException( "Invalid login. Please check your name and password." );
            return new ResponseEntity<>("Invalid login. Please check your name and password." ,HttpStatus.FORBIDDEN);
        }

        jwtToken = Jwts.builder().setSubject( username ).claim( "roles", "user" ).setIssuedAt( new Date() ).signWith(
            SignatureAlgorithm.HS256, "secretkey" ).compact();

        return new ResponseEntity<>(new Token( jwtToken ),HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping( value = "/items", method = RequestMethod.GET )
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @CrossOrigin
    @RequestMapping( value = "/items", method = RequestMethod.POST )
    public ResponseEntity<?>  usuarioNuevo(@RequestBody User user) {
            try{
                return new ResponseEntity<>(userService.createUser(user),HttpStatus.ACCEPTED);
            }
            catch (ServletException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
            }

    }

    @CrossOrigin
    @RequestMapping( value = "/byEmail/{email}.{dominio}", method = RequestMethod.GET )
    public ResponseEntity<?>  userByEmail(@PathVariable  String email,@PathVariable  String dominio){

        try{
            return new ResponseEntity<>(userService.findUserByEmail(email+"."+dominio),HttpStatus.ACCEPTED);
        }catch(ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }



    public class Token
    {

        String access_token;


        public Token( String access_token )
        {
            this.access_token = access_token;
        }


        public String getAccessToken()
        {
            return access_token;
        }

        public void setAccessToken( String access_token )
        {
            this.access_token = access_token;
        }
    }

}
