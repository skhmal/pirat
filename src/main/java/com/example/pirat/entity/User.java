package com.example.pirat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String steamId;
    private String email;
    private String avatar;
    private String country;
    private int status;

    @JsonProperty("steam_username")
    private String steamUsername;

    @JsonProperty("steam_token")
    private String steamToken;

    @JsonProperty("steam_update")
    private int steamUpdate;

    @JsonProperty("member_since")
    private Date memberSince;

    @JsonProperty("plan_id")
    private int planId;

    @JsonProperty("kyc_level")
    private int kycLevel;

    @JsonProperty("kyc_required")
    private int kycRequired;

    private int bumps;

    @JsonProperty("o_settings")
    private int oSettings;

    @JsonProperty("o_notify")
    private int oNotify;

    @JsonProperty("store_alias")
    private String storeAlias;

    private String currency;
    private String language;

    @JsonProperty("created_at")
    private Date createdAt;

    private int balance;
    private int wbalance;
    private int lbalance;
    private int abalance;

    @JsonProperty("store_hash")
    private String storeHash;

    @JsonProperty("has_password")
    private int hasPassword;

    @JsonProperty("has_twofa")
    private int hasTwofa;

    @JsonProperty("has_apikey")
    private int hasApikey;

    // Add getters and setters here
}
