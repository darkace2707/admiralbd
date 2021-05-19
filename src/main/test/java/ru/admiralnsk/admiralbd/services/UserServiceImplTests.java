package ru.admiralnsk.admiralbd.services;

import org.junit.Before;
import org.junit.Test;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.admiralnsk.admiralbd.models.Status.ACTIVE;

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
        User user1 = new User((long)1, "John", "John","12345", Role.USER, ACTIVE);
        User user2 = new User((long)2, "Alex", "Alex","12345", Role.USER, ACTIVE);
        User user3 = new User((long)3, "Dima", "Dima","12345", Role.USER, ACTIVE);

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(repository.findAll()).thenReturn(list);

        //test
        List<User> list2 = service.findAll();

        assertEquals(3, list2.size());
        verify(repository, times(1)).findAll();
    }

    /*@Test
    public void getEmployeeByIdTest()
    {
        when(repository.findById((long)1)).thenReturn(new User((long)1, "John", "John","12345", Role.USER, ACTIVE);

        User user = service.findUserById((long)1);

        assertEquals("John", user.getName());
        assertEquals("John", user.getLogin());
        assertEquals("12345", user.getPassword());
    }*/


}