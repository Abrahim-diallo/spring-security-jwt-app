package com.springsecurity.sn.repository;

import com.springsecurity.sn.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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
    //---method to listAllRoles---
    @Test
    void listAllRoles() {
        // Act - Retrieve all roles from the database
        List<Role> allRoles = roleRepository.findAll();

        // Assert - Verify that all roles were retrieved successfully
        assertNotNull(allRoles);
        assertEquals(3, allRoles.size());

        // Print role details
        allRoles.forEach(role -> System.err.println(role));
    }
    //---method to  get one role from the database===
    @Test
    void getOneRole() {
        // Act - Retrieve one role from the database
        Role oneRole = roleRepository.getOne(1);

        // Assert - Verify that one role was retrieved successfully
        assertNotNull(oneRole);
        assertEquals(1, oneRole.getId());
        //---system.err
        System.err.println("Role " + oneRole);
    }
    //---method to update one role from the database===
    @Test
    void updateOneRole() {
        // Act - Retrieve one role from the database
        Role oneRole = roleRepository.getOne(1);

        // Assert - Verify that one role was retrieved successfully
        assertNotNull(oneRole);
        assertEquals(1, oneRole.getId());
        //---system.err
        System.err.println("Role " + oneRole);

        // Update one role
        oneRole.setName("ADMINISTRATORR");

        // Act - Update one role in the database
        Role updatedRole = roleRepository.save(oneRole);

        // Assert - Verify that one role was updated successfully
        assertNotNull(updatedRole);
        assertEquals(1, updatedRole.getId());
        //---system.err
        System.err.println("Role " + updatedRole);
    }

}