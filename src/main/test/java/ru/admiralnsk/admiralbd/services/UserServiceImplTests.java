package ru.admiralnsk.admiralbd.services;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.admiralnsk.admiralbd.models.Role;
import ru.admiralnsk.admiralbd.models.Status;
import ru.admiralnsk.admiralbd.models.User;
import ru.admiralnsk.admiralbd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTests {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findAllTest()
    {
        List<User> listReturnedFromRepository = new ArrayList<>();
        User user1 = new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        User user2 = new User((long)2, "Alex", "Alex","12345", Role.USER, Status.ACTIVE);
        User user3 = new User((long)3, "Dima", "Dima","12345", Role.USER, Status.ACTIVE);

        listReturnedFromRepository.add(user1);
        listReturnedFromRepository.add(user2);
        listReturnedFromRepository.add(user3);

        when(userRepository.findAll()).thenReturn(listReturnedFromRepository); // repository.findAll() терперь возвращает list

        //test
        List<User> listReturnedFromService = userService.findAll();

        Assertions.assertEquals(listReturnedFromRepository , listReturnedFromService);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findUserByIdTest()
    {
        User userReturnedFromRepository= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        when(userRepository.findById((long)1)).thenReturn(java.util.Optional.of(userReturnedFromRepository));

        User userReturnedFromService = userService.findUserById((long)1);

        Assertions.assertEquals(userReturnedFromRepository,userReturnedFromService);
        verify(userRepository, times(1)).findById((long)1);
    }

    @Test
    public void initUserTest(){
        User userDispatchedToInit= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        User newUser = new User();
        User userReturnedFromInit = userService.initUser(newUser,userDispatchedToInit);
        Assertions.assertEquals("John", userReturnedFromInit.getName());
        Assertions.assertEquals("John", userReturnedFromInit.getLogin());
        Assertions.assertEquals(passwordEncoder.encode("12345"), userReturnedFromInit.getPassword());
        Assertions.assertEquals(Role.USER, userReturnedFromInit.getRole());
        Assertions.assertEquals(Status.ACTIVE, userReturnedFromInit.getStatus());
    }

    @Test
    public void updateUserTest()
    {
        User user= new User((long)1, "John", "John","12345", Role.USER, Status.ACTIVE);
        Throwable thrown = assertThrows(UsernameNotFoundException.class, () -> userService.updateUser(user));
        Assertions.assertEquals("No user with such id", thrown.getMessage());
    }

    @Test
    public void deleteUserTest()
    {
        userService.deleteUserById((long)1);
        verify(userRepository, times(1)).deleteById((long)1);
    }


}