package com.service.impl;

import com.models.User;
import com.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by kzub on 10/1/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    private List<User> users;
    private User user;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setPhone("phone");
        user.setName("name");
        user.setCount(5);
        users = new ArrayList<User>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                users.clear();
                return "delete element " ;
            }
        }).when(userRepository).delete(anyLong());
    }

    @Test
    public void testDelete() throws Exception {
        assertEquals(userService.getAll().size(), 1);
        userService.delete(user.getId());
        assertEquals(userService.getAll().size(), 0);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(userService.getAll().get(0).getCount(), users.get(0).getCount());
        assertEquals(userService.getAll().get(0).getName(), users.get(0).getName());
        assertEquals(userService.getAll().get(0).getPhone(), users.get(0).getPhone());
    }

    @Test
    public void testFindByPhone() throws Exception {
        assertEquals(userService.findByPhone("phone").getPhone(), user.getPhone());
    }

    @Test
    public void testIncrementCount() throws Exception {
        int curCount = user.getCount();
        userService.incrementCount(user);
        assertEquals(user.getCount(), curCount+1);
    }

    @Test
    public void testDecrementCount() throws Exception {
        userService.save(user);
        int curCount = user.getCount();
        userService.decrementCount(user);
        assertEquals(userService.findByPhone(user.getPhone()).getCount(), curCount-1);
    }

    @Test
    public void testSave() throws Exception {
        userService.save(user);
        verify(userRepository,times(1)).save(any(User.class));
    }

    @Test
    public void testUpdate() throws Exception {
        userService.update(user);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteByPhone() throws Exception {
        userService.save(user);
        userService.deleteByPhone("phone");
        verify(userRepository,times(1)).delete(any(User.class));
    }
}