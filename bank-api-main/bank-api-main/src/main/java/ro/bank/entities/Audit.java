package ro.bank.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class Audit {

    @Column(nullable = false)
    @CreatedBy
    public String createdBy;

    @Column(nullable = false)
    @CreatedDate
    public LocalDateTime createdAt;

    @LastModifiedBy
    public String updatedBy;
    @LastModifiedDate
    public LocalDateTime updatedAt;

}
