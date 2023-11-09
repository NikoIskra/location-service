package com.location.service;

import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import java.util.UUID;

public interface PoiService {
  PoiPostReturnModel save(UUID X_ACCOUNT_ID, PoiPostRequestModel poiPostRequestModel);
}
