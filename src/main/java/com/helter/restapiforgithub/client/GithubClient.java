package com.helter.restapiforgithub.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helter.restapiforgithub.service.ResponseRepo;

@FeignClient(value ="github", url="https://api.github.com")
public interface GithubClient {
    
    @GetMapping("users/{userName}/repos")
    List<ResponseRepo> getAllRepos(@PathVariable String userName);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDetails> getAllBranchesByRepo(@PathVariable String owner, @PathVariable String repo);
}
