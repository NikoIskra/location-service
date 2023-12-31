package com.location.service;

import com.location.exception.BadRequestException;
import com.location.exception.ConflictException;
import com.location.exception.NotFoundException;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPutRequestModel;
import com.location.model.TagsEnum;
import com.location.persistence.repository.CustomPoiJPARepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoiValidator {

  private final AccountApiClient accountApiClient;

  private final CustomPoiJPARepository poiRepository;

  public void validatePoiPost(UUID accountID, PoiPostRequestModel poiPostRequestModel) {
    accountApiClient.verifyAccountID(accountID);
    Set<TagsEnum> tagSet = new HashSet<>(poiPostRequestModel.getTags());
    if (tagSet.size() != poiPostRequestModel.getTags().size()) {
      throw new BadRequestException("tags must be unique!");
    }
    if (poiRepository.existsByExternalIDAndName(
        poiPostRequestModel.getExternalId(), poiPostRequestModel.getName())) {
      throw new ConflictException("location with that external ID and name already exists");
    }
  }

  public void validatePoiPut(UUID accountID, Long poiID, PoiPutRequestModel poiPutRequestModel) {
    accountApiClient.verifyAccountID(accountID);
    if (!poiRepository.existsById(poiID)) {
      throw new NotFoundException("no record with that ID exists!");
    }
    Set<TagsEnum> tagSet = new HashSet<>(poiPutRequestModel.getTags());
    if (tagSet.size() != poiPutRequestModel.getTags().size()) {
      throw new BadRequestException("tags must be unique!");
    }
  }
}
