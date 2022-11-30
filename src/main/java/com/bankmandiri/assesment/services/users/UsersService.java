package com.bankmandiri.assesment.services.users;

import com.bankmandiri.assesment.entities.Users;
import com.bankmandiri.assesment.requests.UserRequest;
import com.bankmandiri.assesment.responses.Response;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public interface UsersService {

	Timestamp getSysDate();

	List<Object> findAllUsers();

	Response save(UserRequest userRequest);

	Users findById (Long id);

	Response updateUser(Long id, UserRequest userRequest);

	Response deleteUser(Long id);

	Map<String, Object> findAllUsersPage(Integer pageNo, Integer pageSize, String sortBy, String sort, String config, String config2);

}
