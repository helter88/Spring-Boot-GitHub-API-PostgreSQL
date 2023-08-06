package com.helter.restapiforgithub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helter.restapiforgithub.client.BranchDetails;
import com.helter.restapiforgithub.client.GithubClient;
import com.helter.restapiforgithub.controller.RequiredResponseDto;
import com.helter.restapiforgithub.error.NotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GithubService {

    private final GithubClient githubClient;

    
    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    
    public List<RequiredResponseDto> getAllRepoByUserName(String userName){
        try{
           List<ResponseRepo> allRepositoriesByUsername = githubClient.getAllRepos(userName);
           List<RequiredResponseDto> requiredData = allRepositoriesByUsername.stream()
            .filter(respRepo -> {
                return respRepo.fork() == false; 
            })
            .map(notForkRepo -> {
                String name = notForkRepo.owner().login();
                String repo = notForkRepo.name();
                List<BranchDetails> branchList = githubClient.getAllBranchesByRepo(name, repo );
                RequiredResponseDto preparedData = new RequiredResponseDto(repo, name, branchList);
                return preparedData;
            }).toList();
        log.info("Founded repositories: ");
        log.info(requiredData);
        return requiredData;
        } catch (RuntimeException exception){
            throw new NotFoundException("Couldn't find user: " + userName);
        }
    }
}
