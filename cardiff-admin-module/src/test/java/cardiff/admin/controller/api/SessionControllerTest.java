package cardiff.admin.controller.api;

import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.service.AdminLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AdminLoginService adminLoginService;

    final AdminLoginDto.Req req = AdminLoginDto.Req.builder()
            .name("테스트관리자")
            .password("1234")
            .build();

    @Test
    @DisplayName("로그인 성공 테스트")
    void 로그인_성공_테스트() throws Exception{
        // given

        given(adminLoginService.doLogin(any(), any())).willReturn(true);

        // when // then
        mvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void 로그인_실패_테스트() throws Exception{
        // given
        given(adminLoginService.doLogin(any(), any())).willReturn(false);

        // when // then
        mvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(content().string(Matchers.containsString("errorMessage")));

    }

    @Test
    @DisplayName("로그아웃 테스트")
    void 로그아웃_테스트() throws Exception{

        mvc.perform(delete("/api/v1/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .session(new MockHttpSession())
                )
                .andExpect(status().isOk());

    }
}