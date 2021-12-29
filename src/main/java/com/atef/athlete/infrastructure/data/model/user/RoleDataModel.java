package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.user.role.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Data
public class RoleDataModel {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", updatable = false, nullable = false)
    private String type;

    @Column(name = "PAYLOAD")
    private String payload;

    public RoleDataModel() {
    }

    private RoleDataModel(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public static RoleDataModel from(Role role) {
        return new RoleDataModel(role.getClass().getName(), null);
    }
}
