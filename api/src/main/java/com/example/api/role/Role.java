package com.example.api.role;

/**
 * @author Max Borowski
 */
public enum Role {
    ROLE_ADMIN("admin"), ROLE_POSTS("rolePosts"), ROLE_USERS("roleUsers"), ROLE_ALBUMS("roleAlbums");
    private String string;
    Role(String name){string = name;}

    @Override
    public String toString(){
        return string;
    }
}
