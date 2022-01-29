package cardiff.domain.repository;

import cardiff.domain.entity.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AdminLoginRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    AdminLoginRepository adminLoginRepository;

    @Test
    @DisplayName("로그인 성공 테스트")
    @Transactional
    @Commit
    void 로그인_테스트() throws Exception{
        // given
        Admin admin = new Admin("zzs", "1234");
        em.persist(admin);
        em.flush();
        em.clear();

        // when
        Optional<Admin> zzs = adminLoginRepository.findOne("zzs", "1234");

        // then
        assertThat(admin.getId()).isEqualTo(zzs.get().getId());
        assertThat(admin.getName()).isEqualTo(zzs.get().getName());

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    @Transactional
    void 로그인_테스트2() throws Exception{
        // given
        String name = "fake_admin";
        String password = "39572934098";

        // when
        assertThrows(EmptyResultDataAccessException.class,
                () ->adminLoginRepository.findOne(name, password));

        // then
    }

}