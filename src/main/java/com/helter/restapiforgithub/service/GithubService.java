package com.helter.restapiforgithub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.helter.restapiforgithub.client.GithubClient;
import com.helter.restapiforgithub.controller.RequestRepoDto;
import com.helter.restapiforgithub.controller.RequiredResponseDto;
import com.helter.restapiforgithub.error.NotFoundException;
import com.helter.restapiforgithub.model.Repo;
import com.helter.restapiforgithub.repository.RepoRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GithubService {

    private final GithubClient githubClient;
    private final RepoRepository repoRepository;

    
    public GithubService(GithubClient githubClient, RepoRepository repoRepository ) {
        this.githubClient = githubClient;
        this.repoRepository = repoRepository;
    }

    
    public List<RequiredResponseDto> getAllRepoByUserName(String userName){
        try{
           List<ResponseRepo> allRepositoriesByUsername = githubClient.getAllRepos(userName);
           allRepositoriesByUsername.stream()
            .filter(respRepo -> {
                return respRepo.fork() == false; 
            })
            .forEach(notForkRepo -> {
                String owner = notForkRepo.owner().login();
                String name = notForkRepo.name();
                List<Repo> existingRepo = repoRepository.findByOwnerAndName(owner, name);
                if (existingRepo.isEmpty()){
                    Repo repo = new Repo(owner, name);
                    repoRepository.save(repo); 
                } 
            });

        List<Repo> founded = repoRepository.findByOwner(userName);
        log.info("Founded repositories: ");
        log.info(founded);
        List<RequiredResponseDto> responseList = new ArrayList<>();
        founded.forEach(repo -> {
            responseList.add(new RequiredResponseDto(repo.getId(), repo.getOwner(), repo.getName()));
        });
        return responseList ;
        } catch (RuntimeException exception){
            throw new NotFoundException("Couldn't find user: " + userName);
        }
    }

    public List<RequiredResponseDto> getAll(Pageable pageable) {
        List<Repo> allRepo = repoRepository.findAll(pageable);
        List<RequiredResponseDto> dto = new ArrayList<>();
        allRepo.forEach(data -> {
            dto.add(new RequiredResponseDto(data.getId(), data.getOwner(), data.getName()));
        });

        return dto;
    }

    public void post(RequestRepoDto dto){
        Repo newRepo = new Repo(dto.owner(), dto.name());
        repoRepository.save(newRepo);
    }

    public RequiredResponseDto delete(Long id) {
        Repo repoToDelete = repoRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find id: " + id));
        repoRepository.deleteById(id);
        return new RequiredResponseDto(repoToDelete.getId(), repoToDelete.getOwner(), repoToDelete.getName());
    }
}
