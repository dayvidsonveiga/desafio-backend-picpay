package br.com.picpay.service;

import br.com.picpay.client.AuthorizationClient;
import br.com.picpay.controller.dto.TransferDto;
import br.com.picpay.entity.Transfer;
import br.com.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {
        var res = authorizationClient.isAuthorized();

        if (res.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return res.getBody().authorized();
    }
}
