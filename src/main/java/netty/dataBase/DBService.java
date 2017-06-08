package netty.dataBase;

import netty.model.Controller;
import netty.model.ControllerChannel;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DBService {

    private static final Logger logger = Logger.getLogger(DBService.class);
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private List<ControllerChannel> controllerChannels = null;

    public void saveController(Controller controller) {
        Iterator<ControllerChannel> iterator = controller.getChannels().iterator();

        while (iterator.hasNext()) {
            ControllerChannel channel = iterator.next();
            try {
                saveChannel(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveChannel(ControllerChannel channel) throws Exception {
        addAll(channel.getAddressText(), channel.get_name(), channel.get_status(), channel.get_value());
    }

    public void addAll(String addres, String name, int status, double value) throws Exception {
        System.out.println("DBService started");
        String query = "INSERT INTO dataUnit  " + "(name,address,status,value)" + "VALUES (?,?,?,?)" ;
        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,addres);
            stmt.setInt(3, status);
            stmt.setDouble(4, value);
            stmt.executeUpdate();
            System.out.println("Insert data in DB");
        }catch (SQLException ex){
            ex.printStackTrace();
            logger.error(ex.getMessage(),ex);
        }finally{
            try {
                if (stmt !=null) stmt.close();
                if (conn!=null)conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }
}
