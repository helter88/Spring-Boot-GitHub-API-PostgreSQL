package com.helter.restapiforgithub;

import java.util.List;

public record RequiredResponseDto(String repositoryName, String ownerLogin, List<BranchDetails> branches ) {
    
}
