package com.location.controller.impl;

import com.location.controller.PoiApi;
import com.location.model.PoiGetReturnModel;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPutRequestModel;
import com.location.model.SearchNearestReturnModel;
import com.location.service.PoiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PoiController implements PoiApi {

  private final PoiService poiService;

  @Override
  public ResponseEntity<PoiPostReturnModel> addPoi(
      @NotNull UUID X_ACCOUNT_ID, @Valid PoiPostRequestModel poiPostRequestModel) throws Exception {
    PoiPostReturnModel poiPostReturnModel = poiService.save(X_ACCOUNT_ID, poiPostRequestModel);
    HttpHeaders returnHeaders = new HttpHeaders();
    String headerString = "/api/v1/poi/" + poiPostReturnModel.getResult().getId();
    returnHeaders.set("Location", headerString);
    return ResponseEntity.status(HttpStatus.CREATED)
        .headers(returnHeaders)
        .body(poiPostReturnModel);
  }

  @Override
  public ResponseEntity<PoiGetReturnModel> getPoi(@NotNull UUID X_ACCOUNT_ID, Long poiId)
      throws Exception {
    PoiGetReturnModel poiGetReturnModel = poiService.get(X_ACCOUNT_ID, poiId);
    return ResponseEntity.status(HttpStatus.OK).body(poiGetReturnModel);
  }

  @Override
  public ResponseEntity<SearchNearestReturnModel> getNearestPois(
      @NotNull UUID X_ACCOUNT_ID,
      Integer meters,
      @NotNull @Valid Float latitude,
      @NotNull @Valid Float longitude,
      @NotNull @Valid Integer page,
      @Min(20) @Max(100) @Valid Integer pageSize)
      throws Exception {
    SearchNearestReturnModel searchNearestReturnModel =
        poiService.searchNearest(X_ACCOUNT_ID, meters, latitude, longitude, page, pageSize);
    return ResponseEntity.status(HttpStatus.OK).body(searchNearestReturnModel);
  }

  @Override
  public ResponseEntity<PoiPostReturnModel> updatePoi(
      @NotNull UUID X_ACCOUNT_ID, Long poiId, @Valid PoiPutRequestModel poiPutRequestModel)
      throws Exception {
    PoiPostReturnModel poiPostReturnModel =
        poiService.update(X_ACCOUNT_ID, poiId, poiPutRequestModel);
    return ResponseEntity.status(HttpStatus.OK).body(poiPostReturnModel);
  }
}
