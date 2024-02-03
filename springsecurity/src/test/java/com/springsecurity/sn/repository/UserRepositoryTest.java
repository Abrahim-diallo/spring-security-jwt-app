package com.springsecurity.sn.repository;

import com.springsecurity.sn.entity.Role;
import com.springsecurity.sn.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Set<Role> roles;
    private List<User> users;

    @BeforeEach
    public void setUp() {
        // Initialisation des rôles et des utilisateurs
        roles = Set.of(new Role("ADMIN"), new Role("EDITOR"), new Role("USER"));
        users = List.of(
                new User("Admin", "Admin", "ad@gmail.com", "password", true, true, roles),
                new User("Editor", "Editor", "editor@gmail.com", "password", true, true, roles),
                new User("User", "User", "user@gmail.com", "password", true, true, roles));
    }

    @Test
    public void testSaveAllUsers() {
        // Enregistrement des utilisateurs
        List<User> savedUsers = userRepository.saveAll(users);
        // Vérification que la liste des utilisateurs enregistrés n'est pas null
        assertNotNull(savedUsers, "La liste des utilisateurs enregistrés ne doit pas être null.");
        // Vérification du nombre d'utilisateurs enregistrés
        assertEquals(3, savedUsers.size(), "Vérifie que trois utilisateurs sont enregistrés.");
    }

    @Test
    public void testFindAllUsers() {
        // Récupération de tous les utilisateurs
        List<User> allUsers = userRepository.findAll();
        // Vérification que la liste des utilisateurs récupérés n'est pas null
        assertNotNull(allUsers, "La liste des utilisateurs récupérés ne doit pas être null.");
        // Vérification que la liste des utilisateurs n'est pas vide
        assertFalse(allUsers.isEmpty(), "La liste des utilisateurs ne doit pas être vide.");
    }

    @Test
    public void testFindUserById() {
        // Recherche de l'utilisateur avec l'ID 1
        Optional<User> user = userRepository.findById(1);
        // Vérification que l'Optional contenant l'utilisateur n'est pas null
        assertNotNull(user, "L'Optional contenant l'utilisateur ne doit pas être null.");
        // Vérification de la présence de l'utilisateur dans l'Optional
        assertTrue(user.isPresent(), "L'utilisateur avec l'ID 1 doit être présent.");
        // Récupération et vérification de l'ID de l'utilisateur
        User actualUser = user.get();
        assertEquals(1, actualUser.getId(), "L'ID de l'utilisateur doit être 1.");
    }

    @Test
    public void testUpdateUserEmail() {
        // Recherche de l'utilisateur avec l'ID 1
        User user = userRepository.findById(1).orElseThrow(() -> new AssertionError("Utilisateur non trouvé."));
        // Modification de l'email de l'utilisateur
        user.setEmail("updated@example.com");
        // Mise à jour de l'utilisateur en base de données
        User updatedUser = userRepository.save(user);
        // Vérification que l'utilisateur mis à jour n'est pas null
        assertNotNull(updatedUser, "L'utilisateur mis à jour ne doit pas être null.");
        // Vérification de l'email de l'utilisateur mis à jour
        assertEquals("updated@example.com", updatedUser.getEmail(), "L'email de l'utilisateur doit être mis à jour.");
    }

    @Test
    public void testDeleteUser() {
        // ID de l'utilisateur à supprimer
        Integer userId = 3;
        // Suppression de l'utilisateur
        userRepository.deleteById(userId);
        // Recherche de l'utilisateur supprimé
        Optional<User> deletedUser = userRepository.findById(userId);
        // Vérification que l'utilisateur supprimé est introuvable
        assertNull(deletedUser, "L'utilisateur supprimé ne doit pas être trouvé.");
    }
}