package cardiff.domain.entity;

import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@EntityScan
@Getter
public class Admin {

    @Id @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    @Column(length = 16, unique = true)
    private String name;

    @Column(length = 20)
    private String password;


    protected Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}