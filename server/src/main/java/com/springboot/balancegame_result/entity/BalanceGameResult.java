package com.springboot.balancegame_result.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.balancegame.entity.BalanceGame;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BalanceGameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonManagedReference
    private Member member;

    @ManyToOne
    @JoinColumn(name = "balanceGameId")
    @JsonManagedReference
    private BalanceGame balanceGame;

    @Enumerated(value = EnumType.STRING)
    private SelectedOption selectedOption;

    public BalanceGameResult(Member member, BalanceGame balanceGame, SelectedOption selectedOption) {
        this.member = member;
        this.balanceGame = balanceGame;
        this.selectedOption = selectedOption;
    }

    public enum SelectedOption{
        L,
        R
        ;
    }
}
