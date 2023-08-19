package com.login.loginExample.user.domain.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IConcatNumber {
    String concatNumber(List<Integer> numbers);
}
