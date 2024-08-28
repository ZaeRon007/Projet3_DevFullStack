package com.chatop.services;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chatop.model.DBUser;
import com.chatop.model.dto.UserDto;
import com.chatop.model.dto.UserLoginDto;
import com.chatop.model.dto.UserRegisterDto;
import com.chatop.model.responses.simpleToken;
import com.chatop.repository.DBUserRepository;

@Service
public class DBUserService {
    
    @Autowired
    private DBUserRepository DBUserRepository;

    @Autowired
    PasswordEncoder PasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public DBUserService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    public Iterable<DBUser> getUsers(){
        return DBUserRepository.findAll();
    }

    public DBUser createUser(UserRegisterDto userRegisterDto){
        DBUser userToAdd = new DBUser(  userRegisterDto.getName(),
                                        userRegisterDto.getEmail(),
                                        new TimeService().getTime(),
                                        new TimeService().getTime());
                                        
        userToAdd.setPassword(PasswordEncoder.encode(userRegisterDto.getPassword()));
        return userToAdd;
    }

    public void addUser(DBUser DBUser){
        DBUserRepository.save(DBUser);
    }

    public void removeUser(DBUser DBuser){
        DBUserRepository.delete(DBuser);
    }

    public ResponseEntity<?> register(UserRegisterDto userRegisterDto) {
        if(DBUserRepository.existsByEmail(userRegisterDto.getEmail())){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        DBUser userToAdd = createUser(userRegisterDto);
        DBUserRepository.save(userToAdd);
        
        return ResponseEntity.ok().body(null);
    }

    public ResponseEntity<?> login(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

            DBUser userToAdd = DBUserRepository.findByEmail(userLoginDto.getEmail());
            userToAdd.setUpdatedAt(new TimeService().getTime());
            DBUserRepository.save(userToAdd);
            
            return ResponseEntity.ok(new simpleToken(jwtService.generateToken(userToAdd)));
        } catch (Exception e) {
            System.out.printf("Exception: %s\n",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    public ResponseEntity<UserDto> getUser() throws ParseException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body((DBUserRepository.findByEmail(username)).ToUserDto());
    }

    public ResponseEntity<?> getUserDtoById(String id) throws NumberFormatException, ParseException {
        return ResponseEntity.ok().body((DBUserRepository.findById(Integer.parseInt(id)).ToUserDto()));
    }
}
