package com.helter.restapiforgithub;

import java.util.List;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@EnableFeignClients
public class GithubService {

    private final GithubClient githubClient;


    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    
    public void getAllRepoByUserName(String username){
        List<ResponseRepo> allRepositoriesByUsername = githubClient.getAllRepos(username);
        List<RequiredResponseDto> requiredData = allRepositoriesByUsername.stream()
            .filter(respRepo -> {
                return respRepo.fork() == false; 
            })
            .map(notForkRepo -> {
                String userName = notForkRepo.owner().login();
                String repo = notForkRepo.name();
                List<BranchDetails> branchList = githubClient.getAllBranchesByRepo(userName, repo );
                RequiredResponseDto preparedData = new RequiredResponseDto(repo, userName, branchList);
                return preparedData;
            }).toList();
        log.info("Founded repositories: ");
        log.info(requiredData);
    }
}
