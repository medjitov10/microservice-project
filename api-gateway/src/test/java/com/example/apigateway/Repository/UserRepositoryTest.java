package com.example.apigateway.Repository;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import static org.mockito.Mockito.verify;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private ResultSet rs;
    @Captor
    private ArgumentCaptor<RowMapper<UserBean>> rowMapperCaptor;
    @InjectMocks
    UserRepository userRepository;
    @InjectMocks
    private UserBean user;

    @Before
    public void init() throws SQLException {
        user = new UserBean(1L, "batman@gmail.com", "batman", "batman");
        when(rs.getLong(any())).thenReturn(user.getId());
        when(rs.getString(any())).thenReturn(user.getPassword());
    }

    @Test
    public void getUserByUsername_UserRepository_Success() throws SQLException {
        userRepository.getUserByUsername("batman");
        verify(jdbcTemplate).queryForObject(anyString(), any(), rowMapperCaptor.capture());
        RowMapper<UserBean> rm = rowMapperCaptor.getValue();
        UserBean test = rm.mapRow(rs, 1);
        assertEquals(test.getUsername(), user.getUsername());
        assertEquals(test.getPassword(), user.getPassword());
        assertEquals(test.getId(), user.getId());
    }
}
