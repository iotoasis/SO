package com.pineone.icbms.so.resources.property.reference;

/**
 * Generic ontology reference.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
public interface IGenericOntologyReference extends IOntologyReference<String, String>
{
    String REF_AIR_COOLING_CONTROL = "http://www.pineone.com/campus/AirCollingControl";
    String REF_AIR_HEATING_CONTROL = "http://www.pineone.com/campus/AirHeatingControl";
    String REF_DEHUMIDIFY_CONTROL = "http://www.pineone.com/campus/DehumidifyControl";
    String REF_DOOR_CONTROL = "http://www.pineone.com/campus/DoorControl";
    String REF_HUMIDIFY_CONTROL = "http://www.pineone.com/campus/HumidifyControl";
    String REF_LUMINOSITY_CONTROL = "http://www.pineone.com/campus/LuminosityControl";
    String REF_TEMPERATURE_CONTROL = "http://www.pineone.com/campus/TemperatureControl";
    String REF_SIREN_CONTROL = "http://www.pineone.com/campus/SirenControl";
    String REF_WALLSWITCH_CONTROL = "http://www.pineone.com/campus/WallSwitchControl";
    String REF_ALARMINFO_CONTROL = "http://www.pineone.com/campus/AlarmInfoControl";
    String REF_EMERGENCYNOTI_CONTROL = "http://www.pineone.com/campus/EmergencyNotiControl";

    String getReference();
}
