package com.bankmandiri.assesment.services.users;
import com.bankmandiri.assesment.exceptions.ResourceNotFoundException;
import com.google.api.client.util.ArrayMap;
import com.bankmandiri.assesment.dto.UserDto;
import com.bankmandiri.assesment.entities.Users;
import com.bankmandiri.assesment.repositories.UsersRepository;
import com.bankmandiri.assesment.requests.UserRequest;
import com.bankmandiri.assesment.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Timestamp getSysDate()
    {
    	return usersRepository.getSysDate();
    }

	@Override
	public List<Object> findAllUsers() {
		List<Users> users = usersRepository.findAll();

		List<Object> arr = new ArrayList<>();
		users.forEach(data -> {
			Map<String, Object> user = new ArrayMap<>();
			user.put("id", data.getId());
			user.put("userName", data.getUserName());
			user.put("firstName", data.getFirstName());
			user.put("lastName", data.getLastName());
			user.put("email", data.getEmail());
			user.put("birthDate", data.getBirthDate());
			user.put("basicSalary", data.getBasicSalary());
			user.put("status", data.getStatus());
			user.put("groupName", data.getGroupName());
			user.put("description", data.getDescription());
			arr.add(user);

		});
		return arr;
	}

	@Override
	public Response save(UserRequest userRequest) {
		Response response = null;
		Users users = new Users();
		Timestamp sysdate = usersRepository.getSysDate();
		users.setUserName(userRequest.getUserName());
		users.setFirstName(userRequest.getFirstName());
		users.setLastName(userRequest.getLastName());
		users.setEmail(userRequest.getEmail());
		users.setBirthDate(userRequest.getBirthDay());
		users.setBasicSalary(userRequest.getBasicSalary());
		users.setStatus(userRequest.getStatus());
		users.setGroupName(userRequest.getGroupName());
		users.setDescription(userRequest.getDescription());
		users = usersRepository.save(users);
		UserDto userDto = new UserDto(
				users.getId(),
				users.getUserName(),
				userRequest.getFirstName(),
				userRequest.getLastName(),
				userRequest.getEmail(),
				userRequest.getBirthDay(),
				userRequest.getBasicSalary(),
				userRequest.getStatus(),
				userRequest.getGroupName(),
				userRequest.getDescription());
		response = users != null ? new Response("Success", userDto) : new Response(null);
		return response;
	}



	@Override
	public Users findById(Long id) {
		return usersRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User not found!")
		);
	}

	@Override
	public Response updateUser(Long id, UserRequest userRequest) {
		Users users = this.findById(id);
		Timestamp sysdate = usersRepository.getSysDate();
		Response response = null;
		users.setUserName(userRequest.getUserName());
		users.setFirstName(userRequest.getFirstName());
		users.setLastName(userRequest.getLastName());
		users.setEmail(userRequest.getEmail());
		users.setBirthDate(userRequest.getBirthDay());
		users.setBasicSalary(userRequest.getBasicSalary());
		users.setStatus(userRequest.getStatus());
		users.setGroupName(userRequest.getGroupName());
		users.setDescription(userRequest.getDescription());
		users = usersRepository.save(users);
		UserDto userDto = new UserDto(
				users.getId(),
				users.getUserName(),
				userRequest.getFirstName(),
				userRequest.getLastName(),
				userRequest.getEmail(),
				userRequest.getBirthDay(),
				userRequest.getBasicSalary(),
				userRequest.getStatus(),
				userRequest.getGroupName(),
				userRequest.getDescription());
		response = users != null ? new Response("Success", userDto) : new Response(null);
		return response;
	}

	@Override
	public Response deleteUser(Long id) {
    	Response response = null;
		Users users = this.findById(id);
		usersRepository.deleteById(users.getId());
		UserDto userDto = new UserDto(
				users.getId(),
				users.getUserName(),
				users.getFirstName(),
				users.getLastName(),
				users.getEmail(),
				users.getBirthDate(),
				users.getBasicSalary(),
				users.getStatus(),
				users.getGroupName(),
				users.getDescription());
		response = users != null ? new Response("Success", userDto) : new Response(null);
		return response;
	}


	@Override
	public Map<String, Object> findAllUsersPage(Integer pageNo, Integer pageSize, String sortBy, String sortDirection, String userName, String firstName) {
		Map<String, Object> result = new HashMap<>();

		Sort.Direction sort;
		if (sortBy.equalsIgnoreCase("desc"))
			sort = Sort.Direction.DESC;
		else
			sort = Sort.Direction.ASC;

		Page<Users> list = null;

		Pageable paging = PageRequest.of(pageNo, pageSize, sort, sortBy);

		if (firstName.equalsIgnoreCase(""))
			userName = null;
		else
			userName = "%" + userName + "%";

		if (firstName.equalsIgnoreCase(""))
			firstName = null;
		else
			firstName = "%" + firstName + "%";

		list = usersRepository.findAllUsers(userName,firstName, paging);

		result.put("data", list.getContent());
		result.put("total_result", list.getTotalElements());
		result.put("total_page", list.getTotalPages());
		return result;
	}
}


