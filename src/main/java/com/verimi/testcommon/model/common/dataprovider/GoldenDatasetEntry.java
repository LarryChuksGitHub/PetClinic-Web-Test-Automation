package com.verimi.testcommon.model.common.dataprovider;

import com.verimi.testcommon.endpointactions.api.IntentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Accessors(chain = true)
@Data
public class GoldenDatasetEntry {
    private String input;
    private IntentType expectedIntent;
    private double expectedConfidence;
    private String language;
    private boolean fallbackExpected;

}
