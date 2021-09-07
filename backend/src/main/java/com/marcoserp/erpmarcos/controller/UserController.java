package com.marcoserp.erpmarcos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoserp.erpmarcos.exception.ResourceNotFoundException;
import com.marcoserp.erpmarcos.model.User;
import com.marcoserp.erpmarcos.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) throws ResourceNotFoundException{	
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID de usuário não existe " + id));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
          @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        user.setEmailId(userDetails.getEmailId());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
	
	@DeleteMapping("/users/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteUser(@PathVariable Long id)
         throws ResourceNotFoundException {
        User user = userRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Usuário não existe :: " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
