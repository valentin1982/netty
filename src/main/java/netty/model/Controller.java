package netty.model;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    List<ControllerChannel> channels = new LinkedList<ControllerChannel>();

    public List<ControllerChannel> getChannels() {
        return channels;
    }

    public void addChannel(ControllerChannel channel) {
        this.channels.add(channel);
    }

}
