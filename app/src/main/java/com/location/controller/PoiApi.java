/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech Do not edit the class manually.
 */
package com.location.controller;

import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
public interface PoiApi {

  /**
   * POST /api/v1/poi Insert poi
   *
   * @param X_ACCOUNT_ID (required)
   * @param poiPostRequestModel Poi to be added (required)
   * @return Created (status code 201) or Bad request! (status code 400) or Conflict! (status code
   *     409)
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/v1/poi",
      produces = {"application/json"},
      consumes = {"application/json"})
  ResponseEntity<PoiPostReturnModel> addPoi(
      @NotNull @RequestHeader(value = "X-ACCOUNT-ID", required = true) UUID X_ACCOUNT_ID,
      @Valid @RequestBody PoiPostRequestModel poiPostRequestModel)
      throws Exception;
}
