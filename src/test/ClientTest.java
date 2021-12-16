import com.example.brief3.DAO.Client;
import com.example.brief3.models.Clients;
import com.example.brief3.DAO.Client;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;


class ClientTest {




    @Test
    public void addTest(){

        Clients client = new Clients(346,"123456RF","salma","kalkhi","+212123456789","salma.kalkhi@gmail.com","safi", Date.valueOf("2021-12-12"),"youcode");
        Client client1 = new Client();
        client1.save(client);
        Assertions.assertFalse(client1.getClients().isEmpty());
        Assertions.assertEquals(303,client1.getClients().size());
    }

    @Test
    public void searchTest(){
        Client client = new Client();
        ObservableList<Clients> clients = client.search("Barry");
        Assertions.assertEquals(1,clients.size());
    }

}