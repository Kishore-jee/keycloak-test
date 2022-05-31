package com.example.controller;

import com.example.demo.Constants;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.kafka.dto.ActivityDto;
import com.example.kafka.dto.Message;
import com.example.kafka.service.KafKaPublisherService;
import com.example.repository.UserRepository;
import com.example.util.Response;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.VERSION)
public class UsersController {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserRepository userRepository;
	@Autowired
	private KafKaPublisherService kafKaPublisherService;

	@GetMapping({"/users"})
	public ResponseEntity<List<User>> getUsers() {
		List<User> usersList = null;

		try {
			this.LOGGER.info("In getUsers()");
			usersList = this.userRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(usersList);
		} catch (Exception var3) {
			this.LOGGER.error(Constants.EXCEPTION, var3);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(usersList);
		}
	}

	@PostMapping({"/users"})
	public Response<UserDto> saveUsers(@RequestBody UserDto user) {
		try {
			this.LOGGER.info("In saveUsers()");
			User userEntity = new User();
			BeanUtils.copyProperties(user, userEntity);
			userEntity = (User) this.userRepository.save(userEntity);
			BeanUtils.copyProperties(userEntity, user);
			userEntity = null;
			this.sendActivity(user);
			return new Response<>(Constants.SUCCESS, HttpStatus.OK.toString(), user);
		} catch (Exception var3) {
			this.LOGGER.error(Constants.EXCEPTION, var3);
			return new Response<>(StringUtils.joinWith(" ", Constants.EXCEPTION, var3.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	private void sendActivity(UserDto dto) {
		ActivityDto activityDto = null;
		activityDto = new ActivityDto(dto.getId().toString(), "save", LocalDateTime.now(), "keycloak-test",
				"saving user in keycloak test user");
		this.kafKaPublisherService.activityPublisher(new Message<>(activityDto));
	}
}