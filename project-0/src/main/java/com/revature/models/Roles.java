package com.revature.models;

import javax.management.relation.Role;

public enum Roles {

    ADMIN("admin"),MANAGER("manager"),BASIC_USER("basic_user");

    private String roleName;

    Roles(String name){
        this.roleName= name;
    }

    public static Roles getByName(String name){
        for(Roles role: Roles.values())
            if(role.roleName.equals(name)){
                return role;
            }
        return BASIC_USER;
    }


}
