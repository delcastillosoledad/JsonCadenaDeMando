package cdm;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class ActiveIngredients extends CDM{

	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";

	public ActiveIngredients(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		if(name.equals(ACTINGREF_FIELD_TAGNAME)) {
			return super.everyCategory(reader, name);
		}
		else {
			if(next != null) {
				return super.readCategory(reader, name);
			}
			else {
				reader.skipValue();
				System.err.println("La categoria: '" + name + "' no es correcta.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) 
			throws IOException {
		String act_name = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				act_name = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}

		return act_name;
	}
}
