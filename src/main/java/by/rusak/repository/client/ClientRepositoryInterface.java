package by.rusak.repository.client;

import by.rusak.domain.Client;
import by.rusak.repository.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface ClientRepositoryInterface extends CRUDRepository <Long, Client>{

    public List<Client> findByName(String string);
    public List<Client> findRiskClient();

    Map<String, Object> getAvgClientsAge();
}
