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
        用户类型,用户状态,用户分组,设备状态,设备分组
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String configKey;
    @Column(length = 512)
    private String configValue;
}
