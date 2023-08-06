package com.helter.restapiforgithub.controller;

import java.util.List;

import com.helter.restapiforgithub.client.BranchDetails;

public record RequiredResponseDto(String repositoryName, String ownerLogin, List<BranchDetails> branches ) {
    
}
