package it.netservice.applicationframework.fe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ale
 * @version $Id: $
 */
public class JsonCalendarEvent {

     private List events=null;
     private List messages=null;
    private List errors=null;
     
     public JsonCalendarEvent(List events){    
       this.events = events;   
    }

    public List getEvents() {
        return events;
    }

    public void setEvents(List events) {
        this.events = events;
    }
    
     @JsonIgnore
    public String toJson() throws Exception{
        if (getEvents()== null || getEvents().isEmpty()){
            return "Nessun risultato trovato";
        }
        return new ObjectMapper().writeValueAsString(getEvents());
    }
    
    @JsonIgnore
    public String toJson(boolean ignoreEmpty) throws Exception{
        if (!ignoreEmpty && (getEvents()== null || getEvents().isEmpty())){
            return "Nessun risultato trovato";
        }
        return new ObjectMapper().writeValueAsString(this);
    }
    
     public List getMessages() {
        return messages;
    }

    public void setMessages(List messages) {
        this.messages = messages;
    }

    public List getErrors() {
        return errors;
    }

    public void setErrors(List errors) {
        this.errors = errors;
    }
}
