package com.phoenix.wdis.api.listener;

import java.util.Map;

public interface CTApiListener {
    public void onResponseReceived(Map<String, Object> response);
    public void onFailedToGetResponse(Map<String, Object> errorResponse);
    
}
