package com.midorlo.wolkenbruch.service.sceduled;

import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Collection of Scheduled administrative Account events.
 */
@Slf4j
@Service
public class ScheduledAdministrativeAccountEvents {

   private final AccountRepository accountRepository;

   public ScheduledAdministrativeAccountEvents(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
   }
//
//   /**
//    * Not activated accounts should be automatically deleted after 3 days.
//    * <p>
//    * This is scheduled to get fired every day, at 01:00 (am).
//    */
//   @Scheduled(cron = "0 0 1 * * ?")
//   public void handleRemoveNonActivatedAccounts() {
//      log.info("Deleting all non activated Accounts.");
//      accountRepository.deleteAll(accountRepository.findAllByIdNotNullAndActivatedIsFalse());
//   }
}
