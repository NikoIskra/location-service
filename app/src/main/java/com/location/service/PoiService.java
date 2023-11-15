package com.location.service;

import com.location.model.PoiGetReturnModel;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.SearchNearestReturnModel;
import java.util.UUID;

public interface PoiService {
  PoiPostReturnModel save(UUID X_ACCOUNT_ID, PoiPostRequestModel poiPostRequestModel);

  PoiGetReturnModel get(UUID X_ACCOUNT_ID, Long poiID);

  SearchNearestReturnModel searchNearest(
      UUID X_ACCOUNT_ID,
      Integer meters,
      Float latitude,
      Float longitude,
      Integer page,
      Integer pageSize);
}
