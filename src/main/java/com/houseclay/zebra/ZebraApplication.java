package com.houseclay.zebra;

import com.houseclay.zebra.model.CommandCustomRunner;
import com.houseclay.zebra.model.Role;
import com.houseclay.zebra.model.User;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.repository.CommandCustomRunnerRepository;
import com.houseclay.zebra.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties(Credential.class)
public class ZebraApplication implements CommandLineRunner {

	private Logger logger= LoggerFactory.getLogger(ZebraApplication.class);

	@Autowired
	private UserService userService;


	public ZebraApplication(Credential credential) {
		this.credential = credential;
	}

	private static Credential credential;


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	CommandCustomRunnerRepository commandCustomRunnerRepository;
	public static void main(String[] args) {
		System.setProperty("spring.datasource.username",credential.getUsername());
		System.setProperty("spring.datasource.password",credential.getPassword());
		SpringApplication.run(ZebraApplication.class, args);
	}


	//Command Line Runner
	@Override
	public void run(String... args) throws Exception {
		//creating role

		logger.info("------------properties---------");
//		System.setProperty("spring.datasource.username",credential.getUsername());
//		System.setProperty("spring.datasource.password",credential.getPassword());
		logger.info("Username : "+credential.getUsername());
		logger.info("Password : "+credential.getPassword());

		//check if the cmd line has run before or not
		ArrayList<CommandCustomRunner> commandCustomRunner = (ArrayList<CommandCustomRunner>) commandCustomRunnerRepository.findAll();
		if(commandCustomRunner.size() == 0 || commandCustomRunner == null || !commandCustomRunner.get(0).getIsRunOnce()) {
			userService.saveRole(new Role(null, "ROLE_USER", "Secondary", BaseTimeStamp.builder().created_by("SYSTEM").created_on(new Date()).build())); // 3rd Party User - Non HouseClay People
			userService.saveRole(new Role(null, "ROLE_MANAGER", "Secondary", BaseTimeStamp.builder().created_by("SYSTEM").created_on(new Date()).build())); //HouseClay Working People
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN", "Primary", BaseTimeStamp.builder().created_by("SYSTEM").created_on(new Date()).build())); // Ankit and Arpit Only

			userService.registerUser(new User(null, "ankit.biswas9@gmail.com", "password", new ArrayList<>(), "Ankit", "Biswas", "7892014327", true, true, true, "No Noted mentioned", BaseTimeStamp.builder().build()), "SYSTEM");

			userService.addRoleToUser("ankit.biswas9@gmail.com", "ROLE_SUPER_ADMIN");

			CommandCustomRunner cmd = CommandCustomRunner.builder().isRunOnce(true).build();
			commandCustomRunnerRepository.save(cmd);
		}

	}

}
