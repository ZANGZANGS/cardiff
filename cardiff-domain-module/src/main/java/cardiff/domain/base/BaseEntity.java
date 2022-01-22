package cardiff.domain.base;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String createUser;
    private LocalDateTime createDatetime;
    private String lastModifiedUser;
    private LocalDateTime lastModifiedDatetime;

}
