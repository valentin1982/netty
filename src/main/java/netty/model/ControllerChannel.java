package netty.model;

public class ControllerChannel {

    String addressText;
    String _name;
    int _status;
    double _value;

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public double get_value() {
        return _value;
    }

    public void set_value(double _value) {
        this._value = _value;
    }

    @Override
    public String toString() {
        return "{" +
                "addressText: " + addressText + "," +
                "_name: " + _name + "," +
                "_status: " + _status + "," +
                "_value: " + _value +
                "}";
    }
}
