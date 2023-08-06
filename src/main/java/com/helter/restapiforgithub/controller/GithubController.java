package com.helter.restapiforgithub.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helter.restapiforgithub.error.InvalidFormatException;
import com.helter.restapiforgithub.service.GithubService;


@RestController
@RequestMapping("/api")
public class GithubController {

    private final GithubService githubService;


    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    
    @GetMapping("/{userName}")
    public ResponseEntity<List<RequiredResponseDto>> getAllNotForkRepositoriesByUsername(@PathVariable String userName, @RequestHeader(HttpHeaders.ACCEPT) String accept) {
        if(accept == "application/xml"){
            throw new InvalidFormatException("Not Acceptable: Only JSON format is supported");
        }
        List<RequiredResponseDto> allRepoByUserName = githubService.getAllRepoByUserName(userName);

        return ResponseEntity.ok(allRepoByUserName);
    }
}
