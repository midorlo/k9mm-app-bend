package com.midorlo.wolkenbruch.repository.system;

import com.midorlo.wolkenbruch.domain.system.SystemSetting;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface SystemSettingRepository extends ApplicationRepository<SystemSetting> {

    Optional<SystemSetting> findByNameEqualsIgnoreCase(String name);
}
