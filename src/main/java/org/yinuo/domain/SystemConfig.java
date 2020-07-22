package org.yinuo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Accessors(fluent = true)
public class SystemConfig {
    public enum KeyEnum{
        userTypes, userStatus, userGroups, deviceStatus, deviceGroups
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String configKey;
    @Column(length = 512)
    private String configValue;
}
