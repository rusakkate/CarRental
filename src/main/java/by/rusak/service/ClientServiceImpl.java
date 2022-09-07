package by.rusak.service;

import by.rusak.domain.Client;
import by.rusak.repository.client.ClientRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepositoryInterface clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client object) {
        return clientRepository.create(object);
    }

    @Override
    public Client update(Client object) {
        return clientRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return clientRepository.delete(id);
    }

    @Override
    public List<Client> findRiskClient() {
        return clientRepository.findRiskClient();
    }

    @Override
    public Map<String, Object> getAvgClientsAge() {
        return clientRepository.getAvgClientsAge();
    }

    @Override
    public List<Client> findByName(String string) {
        return clientRepository.findByName(string);
    }
}
