package com.location.service;

import com.location.exception.BadRequestException;
import com.location.model.AccountRoleIDReturnModel;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountApiClient {

  private String baseUrl;

  private final RestTemplate restTemplate;

  public AccountApiClient(@Value("${base.url}") String baseUrl, RestTemplate restTemplate) {
    this.baseUrl = baseUrl;
    this.restTemplate = restTemplate;
  }

  public void verifyAccountID(UUID accountID) {
    try {
      AccountRoleIDReturnModel accountRoleIDReturnModel =
          restTemplate.getForObject(
              baseUrl + "/api/v1/account/{account_id}/role/PROVIDER",
              AccountRoleIDReturnModel.class,
              accountID.toString());
    } catch (Exception e) {
      throw new BadRequestException("No provider role for this account found");
    }
  }
}
