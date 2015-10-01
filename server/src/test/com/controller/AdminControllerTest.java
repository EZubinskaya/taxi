package com.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.google.common.collect.ImmutableList;
import com.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class AdminControllerTest {
    @Configuration
    @EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
    static class AppConfig extends GlobalMethodSecurityConfiguration {
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("user").password("password").roles("user");
        }
        @Bean
        public AdminController adminController() {
            return new AdminController();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return Mockito.mock(UserDetailsService.class);
        }

        @Bean
        public ManagerService managerService() {
            return Mockito.mock(ManagerService.class);
        }
    }
    @InjectMocks
    private AdminController controller;

    @Mock
    private UserDetailsService userDetailsService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private MockHttpSession getSessionWithRoles(String[] roles) {
        GenericWebApplicationContext gwac = (GenericWebApplicationContext)wac;
        gwac.setServletContext(new MockServletContext());
        mockMvc = webAppContextSetup(wac)
                .alwaysDo(print()).build();
        Authority[] authority = new Authority[roles.length];
        Authority a;
        for(int i=0; i<roles.length;i++) {
            a = new Authority(roles[i]);
            authority[i]=a;
            wac.getServletContext().declareRoles(roles[i]);
        }
        Authentication authToken= new UsernamePasswordAuthenticationToken("admin1@mail","password1", ImmutableList.copyOf(authority));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SecurityContextHolder.getContext().setAuthentication(authToken);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        SecurityContextHolder.getContext().setAuthentication(authToken);

        return session;
    }

    @org.junit.Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void adminPageTest1() throws Exception {
        String[] arr = {"ROLE_ADMIN"};
        MockHttpSession session = getSessionWithRoles(arr);

        mockMvc.perform(get("/admin").session(session)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("adminPage.html"));
    }

    @Test(expected = NestedServletException.class)
    public void adminPageTest2() throws Exception {
        String[] arr = {"ROLE_MANGER"};
        MockHttpSession session = getSessionWithRoles(arr);

        mockMvc.perform(get("/admin").session(session)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("adminPage.html"));
    }

}
