package com.faisco;

import com.faisco.entity.Department;
import com.faisco.entity.Role;
import com.faisco.entity.User;
import com.faisco.repository.DepartmentRepository;
import com.faisco.repository.RoleRepository;
import com.faisco.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@SpringBootTest
public class MysqlApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	RoleRepository roleRepository;

	@Before
	public void initData(){
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();

		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		Assert.notNull(department.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.notNull(role.getId());

		User user = new User();
		user.setName("user");
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setPassword(bpe.encode("user"));
		user.setCreatedate(new Date());
		user.setDepartment(department);
		userRepository.save(user);
		Assert.notNull(user.getId());
	}

	@Test
	public void insertUserRoles(){
		User user = userRepository.findByName("user");
		Assert.notNull(user);

		List<Role> roles = roleRepository.findAll();
		Assert.notNull(roles);
		user.setRoles(roles);
		userRepository.save(user);
	}
}
