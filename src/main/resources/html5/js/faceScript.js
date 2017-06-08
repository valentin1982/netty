function getXmlHttp(){
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}

var xmlhttp = getXmlHttp();

function sendGetControllersList() {
    xmlhttp.open('GET', '/', true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if(xmlhttp.status == 200) {
                return new String(xmlhttp.responseText).split(';');
            }
        }
    };
    xmlhttp.setRequestHeader('getList', 'true');
    xmlhttp.send(null);
}

function sendControllerNumber(controllerNumber) {
    xmlhttp.open('GET', '/', true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if(xmlhttp.status == 200) {
                //return xmlhttp.responseText;
                return sendDataRequest();
            }
        }
    };
    xmlhttp.setRequestHeader('choseController', controllerNumber);
    xmlhttp.send(null);
}

function sendDataRequest() {
    xmlhttp.open('GET', '/', true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if(xmlhttp.status == 200) {
                return xmlhttp.responseText;
            }
        }
    };
    xmlhttp.setRequestHeader('getData', 'true');
    xmlhttp.send(null);
}




