package com.springsecurity.sn.repository;



import com.springsecurity.sn.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the RoleRepository.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void saveAndRetrieveRoles() {
        // Arrange: ADMIN, EDITOR, USER
        Role admin = new Role("ADMIN");
        Role editor = new Role("EDITOR");
        Role user = new Role("USER");

        // Act - Save roles to the database
        List<Role> savedRoles = roleRepository.saveAll(List.of(admin, editor, user));
        // Assert - Verify that roles were saved successfully
        assertNotNull(savedRoles);
        assertEquals(3, savedRoles.size());
    }
}