package netty.parser;

//import jdk.internal.org.xml.sax.InputSource;
import netty.dataBase.DBService;
import netty.model.Controller;
import netty.model.ControllerChannel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SAXPars extends DefaultHandler {

    private DBService service = new DBService();
    private Controller controller = null;
    private ControllerChannel controllerChannel;
    private String thisElement = "";
    private static final String NEWLINE = "\r\n";

    public SAXPars() {

    }

    @Override
    public void startDocument() throws SAXException {
        Date today = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        String date = dateFormat.format(today);
        System.out.println("start parse XML... " + date);
        this.controller = new Controller();
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        if (thisElement.equalsIgnoreCase("Chan")) {
            this.controllerChannel = new ControllerChannel();
            this.controller.addChannel(controllerChannel);
            System.out.println("channel created");
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equalsIgnoreCase("AddressText")) {
            controllerChannel.setAddressText(new String(ch, start, length));
            System.out.println("--------------------------------------------------------");
            System.out.println("Адресс = " + controllerChannel.getAddressText() + NEWLINE);
        }
        if (thisElement.equalsIgnoreCase("_name")) {
            controllerChannel.set_name(new String(ch, start, length));
            System.out.println("Имя = " + controllerChannel.get_name() + NEWLINE);
        }
        if (thisElement.equalsIgnoreCase("_status")) {
            controllerChannel.set_status(new Integer(new String(ch, start, length)));
            System.out.println("Статус = " + controllerChannel.get_status() + NEWLINE);
        }
        if (thisElement.equalsIgnoreCase("_value")) {
            controllerChannel.set_value(new Double(new String(ch, start, length)));
            System.out.println("Значение = " + controllerChannel.get_value() + NEWLINE);
            System.out.println("--------------------------------------------------------");
        }
    }

    @Override
    public void endDocument() throws SAXException {
        Date today = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        String date = dateFormat.format(today);
        System.out.println("Stop parse XML..." + date);
        service.saveController(controller);
        super.endDocument();
    }


}
