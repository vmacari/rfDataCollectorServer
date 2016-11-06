/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari;

import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.logger.LoggerFactory;
import gnu.io.CommPortIdentifier;
import md.vmacari.comm.*;
import md.vmacari.message.MessageReader;
import md.vmacari.webserver.RestWebServer;

import java.util.Arrays;
import java.util.Enumeration;

/**
 *
 * @author vmacari
 */
public class Main {

    private void startSerialLogger (final String portName) {
        Enumeration e = CommPortIdentifier.getPortIdentifiers();

        CommPortIdentifier selectedComPortId = null;
        System.out.println("Enumerate ports ...");
        while (e.hasMoreElements()) {
            CommPortIdentifier comPortId = (CommPortIdentifier) e.nextElement();
            System.out.println(comPortId.getName());

            if (comPortId.getName().equals(portName)) {
                selectedComPortId = comPortId;
            }

        }

        if (selectedComPortId != null) {
            System.out.print("Com port selected!");

            MessageReader reader = null;

            reader = new MessageReader(Arrays.asList(
                    new MessageInternalHadler(),
                    new MessagePresentationHandler(),
                    new MessageSetHandler(),
                    new MessageStreamHandler(),
                    new MessageRequestHandler()
                    ),
                    selectedComPortId,
                    115200);

            reader.startCommunication();
        } else {
            System.out.println("Cannot select com port!");

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "trace");
        System.setProperty(LoggerFactory.LOG_TYPE_SYSTEM_PROPERTY, LoggerFactory.LogType.LOCAL.toString());

        final String portName = "/dev/tty.wchusbserial1420";
        System.out.println("Application started!");
        Main mainApplication = new Main();
        mainApplication.startSerialLogger(portName);
        new RestWebServer(args);
    }




    
}
