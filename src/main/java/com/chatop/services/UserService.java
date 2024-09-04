package com.chatop.services;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chatop.model.UserEntity;
import com.chatop.model.dto.UserDto;
import com.chatop.model.dto.UserLoginDto;
import com.chatop.model.dto.UserRegisterDto;
import com.chatop.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository DBUserRepository;

    @Autowired
    PasswordEncoder PasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public UserService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    /**
     * get all users from database
     * @return a list of UserEntity
     */
    public Iterable<UserEntity> getUsers(){
        return DBUserRepository.findAll();
    }

    /**
     * create a new user from database
     * @param userRegisterDto a user to register
     * @return UserEntity the created user
     */
    public UserEntity createUser(UserRegisterDto userRegisterDto){
        UserEntity userToAdd = new UserEntity(  userRegisterDto.getName(),
                                        userRegisterDto.getEmail(),
                                        new TimeService().getTime(),
                                        new TimeService().getTime());
                                        
        userToAdd.setPassword(PasswordEncoder.encode(userRegisterDto.getPassword()));
        return userToAdd;
    }

    /**
     * add a user to database
     * @param userEntity user to add
     */
    public void addUser(UserEntity userEntity){
        DBUserRepository.save(userEntity);
    }

    /**
     * remove a user from database
     * @param DBuser user to remove
     */
    public void removeUser(UserEntity userEntity){
        DBUserRepository.delete(userEntity);
    }

    /**
     * register a user to database
     * @param userRegisterDto user to register
     * @return the generated token for user
     */
    public String register(UserRegisterDto userRegisterDto) {
        if(DBUserRepository.existsByEmail(userRegisterDto.getEmail())){
            return "";
        }
        UserEntity userToAdd = createUser(userRegisterDto);
        DBUserRepository.save(userToAdd);
        
        return jwtService.generateToken(userToAdd);
    }

    /**
     * authenticate a user
     * @param userLoginDto user to authenticate
     * @return a new token for user
     */
    public String login(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

            UserEntity userToAdd = DBUserRepository.findByEmail(userLoginDto.getEmail());
            userToAdd.setUpdatedAt(new TimeService().getTime());
            DBUserRepository.save(userToAdd);
            
            return jwtService.generateToken(userToAdd);
        } catch (Exception e) {
            System.out.printf("Exception: %s\n",e);
            return "";
        }
    }

    /**
     * get the authenticated user from database
     * @return the authenticated user
     * @throws ParseException
     */
    public UserDto getUser() throws ParseException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return DBUserRepository.findByEmail(username).ToUserDto();
    }

    /**
     * get a user by its id from database
     * @param id the user id
     * @return a user
     * @throws NumberFormatException
     * @throws ParseException
     */
    public UserDto getUserDtoById(String id) throws NumberFormatException, ParseException {
        return DBUserRepository.findById(Integer.parseInt(id)).ToUserDto();
    }
}
