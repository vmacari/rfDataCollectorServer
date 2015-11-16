/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.messages;

/**
 *
 * @author vmacari
 */
public enum MessagePresentationSubtypes {
        S_DOOR(0),         //	0	Door and window sensors
        S_MOTION(1),       //	1	Motion sensors
        S_SMOKE(2),        //	2	Smoke sensor
        S_LIGHT(3),        //	3	Light Actuator (on/off)
        S_BINARY(3),
        S_DIMMER(4),       //	4	Dimmable device of some kind
        S_COVER(5),        //	5	Window covers or shades
        S_TEMP(6),         //	6	Temperature sensor
        S_HUM(7),          //	7	Humidity sensor
        S_BARO(8),         //	8	Barometer sensor (Pressure)
        S_WIND(9),         //	9	Wind sensor
        S_RAIN(10),         //	10	Rain sensor
        S_UV(11),           //	11	UV sensor
        S_WEIGHT(12),       //	12	Weight sensor for scales etc.
        S_POWER(13),        //	13	Power measuring device, like power meters
        S_HEATER(14),       //	14	Heater device
        S_DISTANCE(15),     //	15	Distance sensor
        S_LIGHT_LEVEL(16),  //	16	Light sensor
        S_ARDUINO_NODE(17), //	17	Arduino node device
        S_ARDUINO_REPEATER_NODE(18),//	18	Arduino repeating node device
        S_LOCK(19),         //	19	Lock device
        S_IR(20),           //	20	Ir sender/receiver device
        S_WATER(21),        //	21	Water meter
        S_AIR_QUALITY(22),  //	22	Air quality sensor e.g. MQ-2
        S_CUSTOM(23),       //	23	Use this for custom sensors where no other fits.
        S_DUST(24),         //	24	Dust level sensor
        S_SCENE_CONTROLLER(25),         //	25	Scene controller device

    S_RGB_LIGHT(26), // RGB light. Send color component data using V_RGB. Also supports V_WATT
    S_RGBW_LIGHT(27), // RGB light with an additional White component. Send data using V_RGBW. Also supports V_WATT
    S_COLOR_SENSOR(28),  // Color sensor, send color information using V_RGB
    S_HVAC(29), // Thermostat/HVAC device. V_HVAC_SETPOINT_HEAT, V_HVAC_SETPOINT_COLD, V_HVAC_FLOW_STATE, V_HVAC_FLOW_MODE, V_TEMP
    S_MULTIMETER(30), // Multimeter device, V_VOLTAGE, V_CURRENT, V_IMPEDANCE
    S_SPRINKLER(31),  // Sprinkler, V_STATUS (turn on/off), V_TRIPPED (if fire detecting device)
    S_WATER_LEAK(32), // Water leak sensor, V_TRIPPED, V_ARMED
    S_SOUND(33), // Sound sensor, V_TRIPPED, V_ARMED, V_LEVEL (sound level in dB)
    S_VIBRATION(34), // Vibration sensor, V_TRIPPED, V_ARMED, V_LEVEL (vibration in Hz)
    S_MOISTURE(35); // Moisture sensor, V_TRIPPED, V_ARMED, V_LEVEL (water content or moisture in percentage?)


        private int value;

        private MessagePresentationSubtypes(int value) {
            this.value = value;

        }
        /**
         * 
         */
        private final static MessagePresentationSubtypes [] enumValues = MessagePresentationSubtypes.values();
        
        /**
         * 
         * @param value
         * @return 
         */
        public static MessagePresentationSubtypes parseInteger(Integer value) {
            if (enumValues.length <= value) {
                return null;
            }
            
            return enumValues[value];
        }
        
        /**
         * 
         * @param value
         * @return 
         */
        public static Integer toInteger (MessagePresentationSubtypes value) {
            for (int i =0; i < enumValues.length; i ++ ) {
                if (enumValues[i] == value) {
                    return i;
                }
            }
            
            return -1;
        }    
}
