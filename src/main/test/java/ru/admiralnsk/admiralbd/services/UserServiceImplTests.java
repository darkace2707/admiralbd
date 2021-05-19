package ru.admiralnsk.admiralbd.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.admiralnsk.admiralbd.models.Role;
import ru.admiralnsk.admiralbd.models.Status;
import ru.admiralnsk.admiralbd.models.User;
import ru.admiralnsk.admiralbd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTests {
    @InjectMocks
    UserServiceImpl service;

    @Mock
    UserRepository repository;

    @Mock
    PasswordEncoder encoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findAllTest()
    {
        List<User> list = new ArrayList<>();
        User user1 = new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        User user2 = new User((long)2, "Alex", "Alex","12345", Role.USER, Status.ACTIVE);
        User user3 = new User((long)3, "Dima", "Dima","12345", Role.USER, Status.ACTIVE);

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(repository.findAll()).thenReturn(list); // repository.findAll() терперь возвращает list

        //test
        List<User> list2 = service.findAll();

        assertEquals(list , list2);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void findUserByIdTest()
    {
        User user1= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        when(repository.findById((long)1)).thenReturn(java.util.Optional.of(user1));

        User user2 = service.findUserById((long)1);

        assertEquals("John", user2.getName());
        assertEquals("John", user2.getLogin());
        assertNotEquals("1234", user2.getPassword());
        assertEquals(user1,user2);
        verify(repository, times(1)).findById((long)1);
    }

    @Test
    public void putUserTest()
    {
        User user1= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        service.putUser(user1);
        verify(repository, times(1)).save(user1);
    }

    @Test
    public void updateUserTest()
    {
        User user1= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        /*when(repository.save(user1)).thenReturn(user1);
        User updateUser1= new User((long)1, "AAA", "AAA","12345", Role.USER, Status.ACTIVE);
        when(repository.save(updateUser1)).thenReturn(updateUser1);
        User user3, user4;
        service.putUser(user1);
        user3 = service.findUserById((long)1);
        service.updateUser(updateUser1);
        user4 = service.findUserById((long)1);
        assertEquals(user3, user4);*/
        service.putUser(user1);
        //service.updateUser(user1);
        verify(repository, times(1)).save(user1);
    }

    @Test
    public void deleteUserTest()
    {
        service.deleteUserById((long)1);
        verify(repository, times(1)).deleteById((long)1);
    }


}