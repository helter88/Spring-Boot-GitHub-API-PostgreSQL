package com.helter.restapiforgithub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class GithubController {

    private final GithubService githubService;


    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    
    @GetMapping("/{userName}")
    public ResponseEntity<String> getAllNotForkRepositoriesByUsername(@PathVariable String userName) {
        githubService.getAllRepoByUserName(userName);

        return ResponseEntity.ok("Found repository for username: " + userName);
    }
}
