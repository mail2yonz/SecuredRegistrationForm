package com.example.demo.DataLoader;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println ("Loading data ...." );

        roleRepository.save ( new UserRole ( "USER" ) );

        roleRepository.save(new UserRole ( "ADMIN" ));


        UserRole adminRole= roleRepository.findByRole ( "ADMIN" );

        UserRole userRole= roleRepository.findByRole ( "USER" );


        User user = new User ("yonatan@gmail.com","password","yonatan","Mengesha",true,"user");

        user.setRoles ( Arrays.asList ( userRole) );

        userRepository.save ( user );

        User user2 = new User ("yonatan@gmail.com","password","yonatan","Mengesha",true,"admin");

        user2.setRoles ( Arrays.asList ( adminRole ) );

        userRepository.save ( user2 );

    }
}
