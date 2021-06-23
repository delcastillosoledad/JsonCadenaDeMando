package cdm;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends CDM{

	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEP = "name";

	public Physiotherapies(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		if(name.equals(PHYSIOTHERAPIES_TAGNAME)) {
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
		String phy_name = null;
		String phy_img = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				phy_name = reader.nextString();
			}
			else if (name.equals(IMAGE_FIELD_TAGNAME)){
				phy_img = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}

		return phy_name + FIELD_SEP + phy_img;
	}
}
