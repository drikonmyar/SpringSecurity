package com.easter.SpringSecurity.config;

public class PreAuthorizeAuthority {

    private PreAuthorizeAuthority(){

    }

    public static final String READ_ADMIN = "hasAnyAuthority('ADMIN_VIEW_ONLY')";

}
