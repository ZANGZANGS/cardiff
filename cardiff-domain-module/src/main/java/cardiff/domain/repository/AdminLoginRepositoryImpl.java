package cardiff.domain.repository;


import cardiff.domain.entity.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdminLoginRepositoryImpl implements AdminLoginRepository {


    private final EntityManager em;

    @Override
    public Long save(Admin admin) {
        em.persist(admin);
        return admin.getId();
    }

    @Override
    public Optional<Admin> findOne(String name, String password) {

       try{
           Admin findAdmin = em.createQuery("select m from Admin m where m.name = :name and m.password = :password", Admin.class)
                   .setParameter("name", name)
                   .setParameter("password", password)
                   .getSingleResult();
           return Optional.of(findAdmin);

       }catch (NoResultException e){
           log.error("회원 정보 불일치 : {}", e.getMessage());
           return  Optional.empty();
       }

    }

}
