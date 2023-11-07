package com.location.controller.impl;

import com.location.controller.PoiApi;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.service.PoiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoiController implements PoiApi {

  @Autowired PoiService poiService;

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
}
