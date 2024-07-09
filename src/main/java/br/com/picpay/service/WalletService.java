package br.com.picpay.service;

import br.com.picpay.controller.dto.CreateWalletDto;
import br.com.picpay.entity.Wallet;
import br.com.picpay.exception.WalletDataAlreadyExistsException;
import br.com.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var walletOptional = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if (walletOptional.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
