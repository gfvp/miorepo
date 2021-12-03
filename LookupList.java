package it.netservice.applicationframework.fe.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ale
 * @version $Id: $
 */
public class LookupList extends ArrayList<LookupEntry>{

    private static final long serialVersionUID=5649962479521178101L;

    public LookupList(List<LookupEntry> lookupEntrys) {
        this.addAll(lookupEntrys);
    }
    
    public LookupList(List<?> list, String codProp, String descProp) {
        try {
            for (Object o : list) {
                LookupEntry lp = new LookupEntry();
                lp.setCodice(BeanUtils.getProperty(o, codProp));
                if (null != BeanUtils.getProperty(o, descProp)) {
                	lp.setDescrizione(BeanUtils.getProperty(o, descProp));
                } else {
                	lp.setDescrizione("");
                }
                add(lp);
            }
        } catch(Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    /**
     * Crea una lista di {@linkplain LookupEntry} partendo dalla lista di oggetti passata come argomento.<br />
     * Ogni {@linkplain LookupEntry} viene cosi' creata: <br />
     * <ul>
     * <li>la property <code>codice</code> viene settata con il valore della property <code>codProp</code>
     * <li>la property <code>descrizione</code> viene settata con i valori delle properties presenti nell'array
     * <code>descProps</code> concatenati mediante separatore <code>separator</code>
     * </ul>
     * @param list lista di oggetti per cui creare una LookupList
     * @param codProp property usata come <code>codice</code> della {@linkplain LookupEntry}
     * @param descProps array delle properties usate per comporre <code>descrizione</code> della {@linkplain LookupEntry}
     * @param separator stringa separatrice da inserire tra gli elementi che compongono la <code>descrizione</code> della {@linkplain LookupEntry}
     */
    public LookupList(List<?> list, String codProp, String[] descProps, String separator) {
        try {
            for (Object o : list) {
                LookupEntry lp = new LookupEntry();
                lp.setCodice(BeanUtils.getProperty(o, codProp));
                String descr = "";
                for (int i=0; i<descProps.length; i++) {
                	// If per non inserire il separatore anche all'ultima iterazione (per poi doverlo togliere)
                	if (i<descProps.length-1) {
                		if (null != BeanUtils.getProperty(o, descProps[i])) {
                			descr = descr + BeanUtils.getProperty(o, descProps[i]) + separator;
                		}
                	} else {
                		if (null != BeanUtils.getProperty(o, descProps[i])) {
                			descr = descr + BeanUtils.getProperty(o, descProps[i]);
                		}
                	}
                }
                lp.setDescrizione(descr);
                add(lp);
            }
        } catch(Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
            
    @JsonIgnore
    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }
}
