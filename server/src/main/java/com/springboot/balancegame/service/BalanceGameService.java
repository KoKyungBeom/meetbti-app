package com.springboot.balancegame.service;

import com.springboot.balancegame.entity.BalanceGame;
import com.springboot.balancegame.repository.BalanceGameRepository;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.gamestatus.GameStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceGameService {
    private final BalanceGameRepository balanceGameRepository;

    public BalanceGameService(BalanceGameRepository repository) {
        this.balanceGameRepository = repository;
    }
    public BalanceGame createGame(BalanceGame game, Authentication authentication) {
        return balanceGameRepository.save(game);
    }
    public BalanceGame findGame(long gameId) {
        return findVerifiedGame(gameId);
    }
    public Page<BalanceGame> findGames(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return balanceGameRepository.findByGameStatusNot(pageable, GameStatus.PENDING);
    }
    public void deleteGame(long gameId) {
        BalanceGame game = findVerifiedGame(gameId);
        balanceGameRepository.delete(game);
    }
    public BalanceGame findVerifiedGame(long gameId) {
        Optional<BalanceGame> optionalGame = balanceGameRepository.findById(gameId);

        return optionalGame.orElseThrow(() -> new BusinessLogicException(ExceptionCode.GAME_NOT_FOUND));
    }
}
