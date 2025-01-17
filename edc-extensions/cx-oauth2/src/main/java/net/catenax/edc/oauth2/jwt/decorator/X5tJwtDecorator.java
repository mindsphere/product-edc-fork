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
package net.catenax.edc.oauth2.jwt.decorator;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.jwt.JwtDecorator;

@RequiredArgsConstructor
public class X5tJwtDecorator implements JwtDecorator {
  private static final String SHA_1 = "SHA-1";

  @NonNull private final byte[] encodedCertificate;

  @Override
  public void decorate(
      @NonNull final JWSHeader.Builder header, @NonNull final JWTClaimsSet.Builder claimsSet) {
    header.x509CertThumbprint(new Base64URL(sha1Base64Fingerprint(encodedCertificate)));
  }

  public static String sha1Base64Fingerprint(final byte[] bytes) {
    try {
      final MessageDigest messageDigest = MessageDigest.getInstance(SHA_1);
      messageDigest.update(bytes);
      return Base64.getEncoder().encodeToString(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      throw new EdcException(e);
    }
  }
}
