package com.example.meeTeam.global.exception.codes;


import com.example.meeTeam.global.exception.codes.reason.Reason;

public interface BaseCode {
    public Reason.ReasonDto getReasonHttpStatus();
}
