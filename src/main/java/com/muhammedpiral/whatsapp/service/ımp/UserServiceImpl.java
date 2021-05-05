package com.muhammedpiral.whatsapp.service.ımp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.model.SearchCriteria;
import com.muhammedpiral.whatsapp.repository.UserRepository;
import com.muhammedpiral.whatsapp.repository.specification.UserSpecification;
import com.muhammedpiral.whatsapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public List<User> findByCriteria(List<SearchCriteria> criteriaList) {
		UserSpecification spec = new UserSpecification(criteriaList);
		return this.userRepository.findAll(spec);
	}

	@Override
	public User userSave(User user) {

		return this.userRepository.save(user);

	}

	@Override
	public User findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUuid(uuid);
	}

	@Override
	public Page<User> getList(UserSpecification spec, Pageable pageable) {

		return userRepository.findAll(spec, pageable);

	}

	@Override
	public User findByPhoneNumber(int phoneNumber) {

		return this.userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				this.getAuthority(user));
	}

	public Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		user.getRoles().forEach(r -> {
			r.getOperations().forEach(operation -> {
				authorities.add(new SimpleGrantedAuthority(operation.getCode()));
			});
		});
		return authorities;
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void update(User user) {

		this.userRepository.save(user);

	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public boolean existsByUsername(String username) {

		return this.userRepository.existsByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		this.userRepository.delete(user);

	}

	@Override
	public void delete(Long updateBy, String uuid) {

		this.userRepository.delete(updateBy, uuid);

	}

//	@Override
//	public Page<User> getList(UserSpecification spec, Pageable pageable) {
//		
//		return this.userRepository.findAll(spec, pageable);
//	}

}
