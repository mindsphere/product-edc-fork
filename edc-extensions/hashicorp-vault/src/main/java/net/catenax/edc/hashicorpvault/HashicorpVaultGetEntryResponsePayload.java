/*
 *  Copyright (c) 2022 Mercedes-Benz Tech Innovation GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Mercedes-Benz Tech Innovation GmbH - Initial API and Implementation
 *
 */

package net.catenax.edc.hashicorpvault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class HashicorpVaultGetEntryResponsePayload {

  @JsonProperty("data")
  private GetVaultEntryData data;

  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class GetVaultEntryData {

    @JsonProperty("data")
    private Map<String, String> data;

    @JsonProperty("metadata")
    private HashicorpVaultEntryMetadata metadata;
  }
}
