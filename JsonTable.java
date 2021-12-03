package it.netservice.applicationframework.fe.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ale
 * @version $Id: $
 */
public class JsonTable{

    private Integer page=null;
    private Integer total=null;
    private Integer records=null;
    private List rows=null;
    private String sort=null;
    private String sortProperty=null;
    private List messages=null;
    private List errors=null;

    public JsonTable(){    }
    
    public JsonTable(List l,Integer count,Integer rfp,Integer page){
        this.records= count;
        this.total= count <= rfp?1:(((count-1)/rfp)+1);
        this.page = page;
        this.rows=l;
    }
    
    public JsonTable(Integer page,Integer total,Integer records,List rows){    
        this.page=page;
        this.total=total;
        this.records=records;
        this.rows=rows;
        this.sort=sort;
       
        
    }
    
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    
    public String getSord() {
        return sort;
    }
    
    public void setSord(String sort) {
        this.sort = sort;
    }
    
    public String getSidx() {
        return sortProperty;
    }
    
    public void setSidx(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
    
    @JsonIgnore
    public String toJson() throws Exception{
        return toJson(false, "IT");
    }
    @JsonIgnore
    public String toJson(boolean ignoreEmpty) throws Exception{
        return toJson(ignoreEmpty, "IT");
    }
    
    @JsonIgnore
    public String toJson(String languageId) throws Exception{
        return _toJson(false, languageId);
    }

    @JsonIgnore
    public String toJson(boolean ignoreEmpty,String languageId) throws Exception{
        if(languageId.toUpperCase().contains("EN"))
            return _toJson(ignoreEmpty, "No Results Found");
        else if(languageId.toUpperCase().contains("IT"))
            return _toJson(ignoreEmpty, "Nessun risultato trovato");
        else if(languageId.toUpperCase().contains("FR"))
            return _toJson(ignoreEmpty, "Aucun résultat trouvé");
        else if(languageId.toUpperCase().contains("DE"))
            return _toJson(ignoreEmpty, "Keine Ergebnisse gefunden");
        else
            return _toJson(ignoreEmpty, "Nessun risultato trovato");
    }
   
    @JsonIgnore
    public String _toJson(boolean ignoreEmpty,String msg) throws Exception{
        if (!ignoreEmpty && (getRows()== null || getRows().isEmpty())){
             messages = new ArrayList<String>();
             messages.add(msg);
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
