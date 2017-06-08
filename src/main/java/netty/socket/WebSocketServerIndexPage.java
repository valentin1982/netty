/*
* Copyright 2012 The Netty Project
*
* The Netty Project licenses this file to you under the Apache License,
* version 2.0 (the "License"); you may not use this file except in compliance
* with the License. You may obtain a copy of the License at:
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations
* under the License.
*/
package netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import netty.handlers.WebSocketServerHandler;

/**
* Generates the demo HTML page which is served at http://localhost:8080/
*/
public final class WebSocketServerIndexPage {

    private WebSocketServerHandler handler;

    private static final String NEWLINE = "\r\n";

    public static ByteBuf getContent(String webSocketLocation) {
        return Unpooled.copiedBuffer(
                "<!DOCTYPE html>" + NEWLINE +
                        "<html lang=\"en\">" + NEWLINE +
                        "<head>" + NEWLINE +
                        "       <meta charset=\"UTF-8\">" + NEWLINE +
                        "       <title></title>" + NEWLINE +
                        "       <style>" + NEWLINE +
                        "               #point table {" + NEWLINE +
                        "                       background-color: #f2f2f2;" + NEWLINE+
                        "                       margin: 40px auto 0;" + NEWLINE +
                        "                       border-top: 1px solid #000;" + NEWLINE +
                        "                       border-left: 1px solid #000;" + NEWLINE +
                        "               }" + NEWLINE +
                        "               #point thead {" + NEWLINE +
                        "                       background-color: #ccc;" + NEWLINE +
                        "               }" + NEWLINE +
                        "               #point td {" + NEWLINE +
                        "                       width: 200px;" + NEWLINE +
                        "                       height: 70px;" + NEWLINE +
                        "                       text-align: center;" + NEWLINE +
                        "                       border-right: 1px solid #000;" + NEWLINE +
                        "                       border-bottom: 1px solid #000;" + NEWLINE +
                        "               }" + NEWLINE +
                        "               .lifted p { " + NEWLINE +
                "                        font-size:16px; " + NEWLINE +
                "                               font-weight:bold; " + NEWLINE +
                "                       } " + NEWLINE +
                "                      .lifted { " + NEWLINE +
                "                               position:relative; " + NEWLINE +
                "                               width:40%; " + NEWLINE +
                "                               padding:1em; " + NEWLINE +
                "                               margin: 40px auto 0; " + NEWLINE +
                "                               background:#fff; " + NEWLINE +
                "                               border-radius:4px; " + NEWLINE +
                "                                -webkit-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset; " + NEWLINE +
                "                                -moz-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset; " + NEWLINE +
                "                               box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset; " + NEWLINE +
                "                               -moz-border-radius:4px; " + NEWLINE +
                "                      } " + NEWLINE +
                "                     .lifted:before, " + NEWLINE +
                "                     .lifted:after { " + NEWLINE +
                "                               position:absolute; " + NEWLINE +
                "                               padding-top: 2px; " + NEWLINE +
                "                               z-index:-2; " + NEWLINE +
                "                                bottom:15px; " + NEWLINE +
                "                               left:10px; " + NEWLINE +
                "                               width:50%; " + NEWLINE +
                "                               height:20%; " + NEWLINE +
                "                               max-width:300px; " + NEWLINE +
                "                               -webkit-box-shadow:0 15px 10px rgba(0, 0, 0, 0.7); " + NEWLINE +
                "                               -moz-box-shadow:0 15px 10px rgba(0, 0, 0, 0.7); " + NEWLINE +
                "                                box-shadow:0 15px 10px rgba(0, 0, 0, 0.7); " + NEWLINE +
                "                               -webkit-transform:rotate(-3deg); " + NEWLINE +
                "                               -moz-transform:rotate(-3deg); " + NEWLINE +
                "                               -ms-transform:rotate(-3deg); " + NEWLINE +
                "                               -o-transform:rotate(-3deg); " + NEWLINE +
                "                               transform:rotate(-3deg); " + NEWLINE +
                "                     } " + NEWLINE +
                "                     .lifted:after { " + NEWLINE +
                "                               right:10px; " + NEWLINE +
                "                               left:auto; " + NEWLINE +
                "                               -webkit-transform:rotate(3deg); " + NEWLINE +
                "                               -moz-transform:rotate(3deg); " + NEWLINE +
                "                               -ms-transform:rotate(3deg); " + NEWLINE +
                "                               -o-transform:rotate(3deg); " + NEWLINE +
                "                               transform:rotate(3deg); " + NEWLINE +
                "                     } " + NEWLINE +
                        "       </style>" + NEWLINE +
                        "</head>" + NEWLINE +
                        "<body style=\"background-color: #f2f2f2\">" + NEWLINE +
                        "       <div class=\"lifted\"> " + NEWLINE +
                        "       <div id=\"point\"></div>" + NEWLINE +
                        "       <script type=\"text/javascript\">" + NEWLINE +
                        "                       var Sock = function() {" + NEWLINE +
                        "                               var socket;" + NEWLINE +
                        "                               if (!window.WebSocket) window.WebSocket = window.MozWebSocket;" + NEWLINE +
                        "                               if (window.WebSocket) {" + NEWLINE +
                        "                                       socket = new WebSocket(\"" + webSocketLocation + "\");" + NEWLINE +
                        "                                       socket.onopen = onopen;" + NEWLINE +
                        "                                       socket.onclose = onclose;" + NEWLINE +
                        "                                       socket.onmessage = onmessage;" + NEWLINE +
                        "                               } else {" + NEWLINE +
                        "                                       alert(\"Your browser does not support Web Socket.\");" + NEWLINE +
                        "                               }" + NEWLINE +
                        "                               function onopen(event) {}" + NEWLINE +
                        "                               function onclose(event) {}" + NEWLINE +
                        "                               function onmessage(event) {" + NEWLINE +
                        "                                       if(event.data) document.getElementById('point').innerHTML = parse(event.data);" + NEWLINE +
                        "                               }" + NEWLINE +
                        "                               if (typeof window.DOMParser != \"undefined\") {" + NEWLINE +
                        "                                       var parseXML = function(xmlStr) {" + NEWLINE +
                        "                                               return ( new window.DOMParser() ).parseFromString(xmlStr, \"text/xml\");" + NEWLINE +
                        "                                       };" + NEWLINE +
                        "                               } else if (typeof window.ActiveXObject != \"undefined\" && new window.ActiveXObject(\"Microsoft.XMLDOM\")) {" + NEWLINE +
                        "                                       var parseXML = function(xmlStr) {" + NEWLINE +
                        "                                               var xmlDoc = new window.ActiveXObject(\"Microsoft.XMLDOM\");" + NEWLINE +
                        "                                               xmlDoc.async = \"false\";" + NEWLINE +
                        "                                               xmlDoc.loadXML(xmlStr);" + NEWLINE +
                        "                                               return xmlDoc;" + NEWLINE +
                        "                                       };" + NEWLINE +
                        "                               } else {" + NEWLINE +
                        "                                       alert('Holy shit! Found no XML parser in this browser! WTF?');" + NEWLINE +
                        "                               }" + NEWLINE +
                        "                               function parse(str) {" + NEWLINE +
                        "                                       if(parseXML) {" + NEWLINE +
                        "                                               var xml = parseXML(str).getElementsByTagName('ArrayOfChan')[0];" + NEWLINE +
                        "                                               var thead = {" + NEWLINE +
                        "                                                       headers: []," + NEWLINE +
                        "                                                       addHeader: function(header) {" + NEWLINE +
                        "                                                               for(var i=0; i<this.headers.length; i++) {" + NEWLINE +
                        "                                                                       if(this.headers[i] == header) return i;" + NEWLINE +
                        "                                                               }" + NEWLINE +
                        "                                                               this.headers.push(header);" + NEWLINE +
                        "                                                               return this.headers.length - 1;" + NEWLINE +
                        "                                                       }" + NEWLINE +
                        "                                               };" + NEWLINE +
                        "                                               var table = [];" + NEWLINE +
                        "                                               for(var i=0; i<xml.children.length; i++) {" + NEWLINE +
                        "                                                       var row = [];" + NEWLINE +
                        "                                                       for(var j=0; j<xml.children[i].children.length; j++) {" + NEWLINE +
                        "                                                               row[thead.addHeader(xml.children[i].children[j].tagName)] = xml.children[i].children[j].innerHTML;" + NEWLINE +
                        "                                                       }" + NEWLINE +
                        "                                                       table.push(row);" + NEWLINE +
                        "                                               }" + NEWLINE +
                        "                                               var result = '<table cellspacing=\"0\" cellpadding=\"0\"><thead><tr>';" + NEWLINE +
                        "                                               for(var i=0; i<thead.headers.length; i++) result += '<td>' + thead.headers[i] + '</td>';" + NEWLINE +
                        "                                               result += '</tr></thead><tbody>';" + NEWLINE +
                        "                                               for(var i=0; i<table.length; i++) {" + NEWLINE +
                        "                                                       result += '<tr>';" + NEWLINE +
                        "                                                       for(var j=0; j<thead.headers.length; j++) {" + NEWLINE +
                        "                                                               result += '<td>' + (table[i][j] ? table[i][j] : '') + '</td>';" + NEWLINE +
                        "                                                       }" + NEWLINE +
                        "                                                       result += '</tr>';" + NEWLINE +
                        "                                               }" + NEWLINE +
                        "                                               result += '</tbody></table>';" + NEWLINE +
                        "                                               return result;" + NEWLINE +
                        "                                       } else {" + NEWLINE +
                        "                                               return 'Found no XML parser in this browser!';" + NEWLINE +
                        "                                       }" + NEWLINE +
                        "                               }" + NEWLINE +
                        "                               var cID = setTimeout(function cycle() {" + NEWLINE +
                        "                                       if(window.WebSocket && socket.readyState == WebSocket.OPEN) {" + NEWLINE +
                        "                                               socket.send('');" + NEWLINE +
                        "                                               cID = setTimeout(cycle, 1000);" + NEWLINE +
                        "                                       } else {" + NEWLINE +
                        "                                               cID = setTimeout(cycle, 10000);" + NEWLINE +
                        "                                       }" + NEWLINE +
                        "                               }, 1000);" + NEWLINE +
                        "                       }" + NEWLINE +
                        "                       window.addEventListener('load', function() { new Sock(); }, false);" + NEWLINE +
                        "       </script>" + NEWLINE +
                        "  </div>    " + NEWLINE +
                        "</body>" + NEWLINE +
                        "</html>" + NEWLINE, CharsetUtil.US_ASCII);

    }

    private WebSocketServerIndexPage() {
        // Unused
    }
}
