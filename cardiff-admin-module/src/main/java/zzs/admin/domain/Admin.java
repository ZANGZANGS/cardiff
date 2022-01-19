package zzs.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Admin {

    @Id
    private String id;
    private String password;
    private String name;


}
