import by.rusak.domain.Car;
import by.rusak.domain.Client;
import by.rusak.service.CarService;
import by.rusak.service.CarServiceImpl;
import by.rusak.service.ClientService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext ("by.rusak");

        //ClientService clientService = annotationConfigApplicationContext.getBean(ClientService.class);
        CarService carService = annotationConfigApplicationContext.getBean(CarService.class);

        carService.findCarPriceDayBelowAvg();
        carService.findCarRatingBelowAvg();
        carService.findCarRatingBelowAvg();
        carService.findTheHighestRatingCar();
        carService.findTheHighestRatingCar();
        carService.findTheHighestRatingCar();
        carService.findTheOldestCar();
        carService.getAvgCarRating();

       /* List <Client> all = clientService.findAll();

        Client client = new Client();
        client.setSurname("Govorov");
        client.setName("Boris");
        client.setBirthday(new Timestamp(new Date().getTime()));
        client.setLicenseNumber("VVV88952");
        client.setLicenseDate(new Timestamp(new Date().getTime()));
        client.setLogin("boris");
        client.setPassword("");
        client.setEmail("");
        client.setLatitude(0);
        client.setLongitude(0);
        client.setCreationDate(new Timestamp(new Date().getTime()));
        client.setModificationDate(new Timestamp(new Date().getTime()));
        client.setIsDeleted(false);

        Client client1 = clientService.create(client);

        client.setId(client1.getId());
        client.setLogin("borisGovorov");
        client.setModificationDate(new Timestamp(new Date().getTime()));
        Client client2 = clientService.update(client);

        clientService.getAvgClientsAge();
        clientService.getAvgClientsAge();
        clientService.findRiskClient(); */

        /*Map<String, Object> carAvgRating = carService.getAvgCarRating();
        System.out.println(carAvgRating);

        for (Car car : allCars) {
            System.out.println(car);
        }

        Car car = carService.findById(1L);
        System.out.println(car);

        ClientService clientService = annotationConfigApplicationContext.getBean(ClientService.class);

        List <Client> petrName =   clientService.findByName("Petr");

        for (Client client : petrName) {
            System.out.println(client);
        }

        Map<String, Object> clientsAvgAge = clientService.getAvgClientsAge();

        for (Map.Entry<String, Object> stringObjectEntry : clientsAvgAge.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + " : " + stringObjectEntry.getValue());
        }

        List <Client> risk =  clientService.findRiskClient();

        for (Client client : risk) {
            System.out.println(client);
        }

       List <Client> all = clientService.findAll();

       for (Client client : all) {
            System.out.println(client);
        }

        Client client = new Client();
        client.setSurname("Abramov");
        client.setName("Abram");
        client.setBirthday(new Timestamp(new Date().getTime()));
        client.setLicenseNumber("PKH565689");
        client.setLicenseDate(new Timestamp(new Date().getTime()));
        client.setLogin("abr");
        client.setPassword("");
        client.setEmail("");
        client.setLatitude(0);
        client.setLongitude(0);
        client.setCreationDate(new Timestamp(new Date().getTime()));
        client.setModificationDate(new Timestamp(new Date().getTime()));
        client.setIsDeleted(false);
        System.out.println(client);

        Client client1 = clientService.create(client);
        System.out.println("New client has been created: " + client1);

        clientService.delete(4L);

        client.setId(client1.getId());
        client.setLogin("abr");
        client.setModificationDate(new Timestamp(new Date().getTime()));
        Client client2 = clientService.update(client);

        System.out.println(client2);*/

    }
}
