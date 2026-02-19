package com.example.demo.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FilesEntity;
import com.example.demo.entity.UserRegistration;
import com.example.demo.model.LoginReqDto;
import com.example.demo.model.UserRegisterDto;
import com.example.demo.model.UserRegistrationDto;
import com.example.demo.repository.FilesRepo;
import com.example.demo.repository.UserRegistrationRepo;
import com.example.demo.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationRepo userRegistrationRepo;

	@Autowired
	FilesRepo filesRepo;

	@Override
	public UserRegistration insertUserRegister(UserRegistrationDto userRegistrationDto) {

		UserRegistration user = new UserRegistration();
		try {
			user.setFirstName(userRegistrationDto.getFirstName());
			user.setLastName(userRegistrationDto.getLastName());
			user.setEmail(userRegistrationDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()));
			user.setPhoneNumber(userRegistrationDto.getPhoneNumber());
			userRegistrationRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRegistration checkLoginCredentials(LoginReqDto loginReqDto) {

		UserRegistration byEmail = userRegistrationRepo.findByEmail(loginReqDto.getEmail());

		if (byEmail != null) {

			String decode = new String(Base64.getDecoder().decode(byEmail.getPassword()));

			if (decode.equals(loginReqDto.getPassword())) {

				return byEmail;
			}
		}
		return null;
	}

	@Override
	public UserRegistration insertUserRegisterwithMultipartfiles(UserRegistrationDto userRegistrationDto, MultipartFile[] filess) {
		UserRegistration user = new UserRegistration();
		try {
			user.setFirstName(userRegistrationDto.getFirstName());
			user.setLastName(userRegistrationDto.getLastName());
			user.setEmail(userRegistrationDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()));
			user.setPhoneNumber(userRegistrationDto.getPhoneNumber());
			userRegistrationRepo.save(user);

			if (filess != null && filess.length > 0) {
				for (MultipartFile multipartFile : filess) {
					FilesEntity files = new FilesEntity();
					files.setFileName(multipartFile.getOriginalFilename());
					files.setFileType(multipartFile.getContentType());
					files.setData(multipartFile.getBytes());
					filesRepo.save(files);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRegisterDto getByUserId(Long id) {
	
		Optional<UserRegistration> byId = userRegistrationRepo.findById(id);
		
		if(byId.isPresent()) {
			
			return new UserRegisterDto(byId.get().getFirstName(), byId.get().getLastName(), byId.get().getPhoneNumber(),byId.get().getEmail());
		}
		
		return null;
	}

}
