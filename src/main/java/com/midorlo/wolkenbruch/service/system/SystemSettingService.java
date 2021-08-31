package com.midorlo.wolkenbruch.service.system;

import com.midorlo.wolkenbruch.domain.system.SystemSetting;
import com.midorlo.wolkenbruch.repository.system.SystemSettingRepository;
import com.midorlo.wolkenbruch.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class SystemSettingService extends ApplicationService<SystemSetting, SystemSettingRepository> {

   public SystemSettingService(SystemSettingRepository repository) {
      super(repository);
   }

   public boolean isInstallationComplete() {
      AtomicBoolean isComplete = new AtomicBoolean(false);
      Optional<SystemSetting> installation = repository.findByNameEqualsIgnoreCase("installation");
      installation.ifPresent(systemSetting -> isComplete.set(true));
      return isComplete.get();
   }
}
