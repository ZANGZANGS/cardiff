package cardiff.admin.controller.api;

import cardiff.domain.entity.Admin;
import cardiff.domain.repository.AdminLoginRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessionControllerTest {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    AdminLoginRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

    }

    @Test
    @DisplayName("로그인 성공 테스트")
    @Transactional
    void 로그인_성공_테스트() throws Exception{
        // given
        String username = "테스트관리자";
        String password = "12345";

        Admin admin = new Admin(username, password);
        admin.passwordEncoding(passwordEncoder);
        repository.save(admin);

        // when // then
        HttpSession session = mvc.perform(formLogin()
                        .user(username)
                        .password(password)
                )
                .andExpect(status().is3xxRedirection())
                .andReturn().getRequest().getSession();

        Assertions.assertThat(session.getAttribute(session.getAttributeNames().nextElement()).toString())
                .contains("테스트관리자");

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    @Transactional
    void 로그인_실패_테스트() throws Exception{
        // given
        String username = "가짜관리자";
        String password = "12345";

        // when // then
        HttpSession session = mvc.perform(formLogin()
                        .user(username)
                        .password(password)
                )

                .andExpect(status().is3xxRedirection())
                .andReturn().getRequest().getSession();

        Assertions.assertThat(session.getAttribute(session.getAttributeNames().nextElement()).toString())
                .contains("BadCredentialsException");

    }

    @Test
    @DisplayName("로그아웃 테스트")
    @WithMockUser(roles="ADMIN")
    void 로그아웃_테스트() throws Exception{

        mvc.perform(logout())
                .andExpect(status().is3xxRedirection())
                .andReturn().getRequest().getSession();

    }
}