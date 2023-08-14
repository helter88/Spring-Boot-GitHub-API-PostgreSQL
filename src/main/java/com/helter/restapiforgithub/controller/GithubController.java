package com.helter.restapiforgithub.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helter.restapiforgithub.service.GithubService;


@RestController
@RequestMapping("/api")
public class GithubController {
    private final GithubService githubService;
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }
    @GetMapping("/{userName}")
    public ResponseEntity<List<RequiredResponseDto>> getAllNotForkRepositoriesByUsername(@PathVariable String userName) {
      
        List<RequiredResponseDto> allRepoByUserName = githubService.getAllRepoByUserName(userName);

        return ResponseEntity.ok(allRepoByUserName);
    }

    @GetMapping
    public ResponseEntity<List<RequiredResponseDto>> getAll(Pageable pageable) {
        List<RequiredResponseDto> allRepo = githubService.getAll(pageable);

        return ResponseEntity.ok(allRepo);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody RequestRepoDto requestRepoDto ) {
        githubService.post(requestRepoDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RequiredResponseDto> delete(@PathVariable Long id) {
        RequiredResponseDto repoToDelete = githubService.delete(id);

        return ResponseEntity.ok(repoToDelete);
    }
}