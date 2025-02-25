package com.app.delicare.service.implement;

import com.app.delicare.entitys.token.Token;
import com.app.delicare.dtos.login.TokenDTO;

import java.util.List;

public interface ITokenService {
    void createToken(TokenDTO tokenDTO);
    List<Token> getAllToken();
    Token getTokenById(Long id);
    Token updateToken(Long tokenId, TokenDTO tokenDTO);
    void deleteToken(Long id);
}
