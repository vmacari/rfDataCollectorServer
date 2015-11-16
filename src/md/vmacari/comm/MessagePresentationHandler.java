/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.comm;

import md.vmacari.message.GwLogger;
import md.vmacari.message.MessageReader;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentation;
import md.vmacari.services.DatabaseService;

/**
 *      Handle presentation message
 * @author vmacari
 */
public class MessagePresentationHandler implements PacketReceiverListener {

    @Override
    public void onDataPacketReceived(MessageReader reader, MessageGeneric dataPacket) {
        
        if (dataPacket.getMessageType() != MessageGeneric.MessageTypes.Presentation || 
            ! (dataPacket instanceof MessagePresentation)) {
            return; // not a presentation message
        }
        
        GwLogger.i("Handle presentation message ");
        MessagePresentation message = (MessagePresentation) dataPacket;

        DatabaseService.saveNodeSensor(message.getNodeId(),
                message.getChildSensorId(),
                message.getMessageType(),
                message.getSubType(), message.getPayload());

        DatabaseService.saveProtocol(message.getNodeId(),
                message.getChildSensorId(),
                message.getPayload());
    }
}
