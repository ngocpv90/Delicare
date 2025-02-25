package com.app.delicare.service;

import com.app.delicare.entitys.token.Token;
import com.app.delicare.dtos.login.TokenDTO;
import com.app.delicare.repositories.TokenRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.service.implement.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void createToken(TokenDTO tokenDTO) {
        Token newToken = Token.builder()
                .tokenName(tokenDTO.getTokenName())
                .tokenCode(tokenDTO.getTokenCode())
                .tokenType(tokenDTO.getTokenType())
                .build();
        tokenRepository.save(newToken);

    }

    @Override
    public List<Token> getAllToken() {
        return tokenRepository.findAll();
    }

    @Override
    public Token getTokenById(Long id) {
        return null;
    }

    @Override
    public Token updateToken(Long tokenId, TokenDTO tokenDTO) {
        Token existstingToken = tokenRepository.getReferenceById(tokenId);
        existstingToken.setTokenCode(tokenDTO.getTokenCode());
        existstingToken.setTokenName(tokenDTO.getTokenName());
        existstingToken.setTokenType(tokenDTO.getTokenType());
        tokenRepository.save(existstingToken);
        return existstingToken;
    }

    @Override
    public void deleteToken(Long id) {

    }
}
