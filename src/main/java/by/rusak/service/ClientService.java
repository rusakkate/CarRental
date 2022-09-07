package by.rusak.service;

import by.rusak.domain.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> findAll();
    Client create(Client object);
    Client update(Client object);

    Long delete(Long id);

    List<Client> findRiskClient();

    Map<String, Object> getAvgClientsAge();

    List<Client> findByName(String string);

}
