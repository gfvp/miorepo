package it.netservice.applicationframework.fe.model;

/**
 *
 * @author ale
 * @version $Id: $
 */
public class LookupEntry implements Comparable<LookupEntry>{

	private String codice = null;
	private String descrizione = null;

	public LookupEntry(){}

	public LookupEntry(String codice,String descrizione){
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public static LookupEntry get(String codice,String descrizione){
		return new LookupEntry(codice, descrizione);

	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public int compareTo(LookupEntry o) {
		if(this.descrizione == null) {
			if(o.descrizione == null) {
				return 0;
			} else {
				return -1;
			}
		}
		if(o.descrizione == null) return 1;
		return this.descrizione.compareTo(o.descrizione);
	}
}
