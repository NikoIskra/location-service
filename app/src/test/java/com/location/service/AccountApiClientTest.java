package com.location.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.location.exception.BadRequestException;
import com.location.model.AccountRoleIDReturnModel;
import com.location.model.AccountRoleIDReturnModelResult;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class AccountApiClientTest {
  @Mock RestTemplate restTemplate;

  @InjectMocks AccountApiClient accountApiClient;

  @Value("${base.url}")
  private String baseUrl;

  private static final UUID accountIdUuid = UUID.fromString("ec73eca8-1e43-4c0d-b5a7-588b3c0e3c9c");

  private static AccountRoleIDReturnModel createAccountRoleIDReturnModel() {
    AccountRoleIDReturnModelResult accountRoleIDReturnModelResult =
        new AccountRoleIDReturnModelResult().roleId(accountIdUuid);
    AccountRoleIDReturnModel accountRoleIDReturnModel =
        new AccountRoleIDReturnModel().ok(true).result(accountRoleIDReturnModelResult);
    return accountRoleIDReturnModel;
  }

  @Test
  void testVerifyAccountID() {
    AccountRoleIDReturnModel accountRoleIDReturnModel = createAccountRoleIDReturnModel();
    when(restTemplate.getForObject(
            baseUrl + "/api/v1/account/{account_id}/role/PROVIDER",
            AccountRoleIDReturnModel.class,
            accountIdUuid.toString()))
        .thenReturn(accountRoleIDReturnModel);
    assertDoesNotThrow(() -> accountApiClient.verifyAccountID(accountIdUuid));
  }

  @Test
  void testVerifyAccountID_exceptionThrown() {
    when(restTemplate.getForObject(
            "http://localhost:3000/api/v1/account/{account_id}/role/PROVIDER",
            AccountRoleIDReturnModel.class,
            accountIdUuid.toString()))
        .thenThrow(RestClientException.class);
    assertThrows(BadRequestException.class, () -> accountApiClient.verifyAccountID(accountIdUuid));
  }
}
