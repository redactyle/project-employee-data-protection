package com.example.voltage.clientimtation;

import java.util.AbstractMap;

public class Constants {

    public static final String urlProtect = "https://voltage-pp-0000.bescl.local/vibesimple/rest/v1/protect";
    public static final String urlAccess = "https://voltage-pp-0000.bescl.local/vibesimple/rest/v1/access";
    public static final AbstractMap.SimpleImmutableEntry<String, String> contentTypePair = new AbstractMap.SimpleImmutableEntry<>("Content-Type", "application/json");
    public static final AbstractMap.SimpleImmutableEntry<String, String> authorizationPair = new AbstractMap.SimpleImmutableEntry<>("Authorization",
            "VSAuth vsauth_method=\"sharedSecret\", vsauth_data=\"dm9sdGFnZTEyMw==\", vsauth_identity_ascii=\"adnan@honeywell.com\", vsauth_version=\"200\"");
}
