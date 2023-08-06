package com.helter.restapiforgithub.service;

import com.helter.restapiforgithub.client.Owner;

public record ResponseRepo(String name, Owner owner, Boolean fork) {
    
}
