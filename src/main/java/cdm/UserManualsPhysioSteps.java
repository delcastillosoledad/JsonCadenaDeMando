package cdm;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualsPhysioSteps extends CDM{

	private static final String USERMANUALPHISIOSTEPS_TAGNAME = "userManualPhysioSteps";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYREF_FIELD_TAGNAME = "physioRef";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualsPhysioSteps(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(USERMANUALPHISIOSTEPS_TAGNAME)) {
			return super.everyCategory(reader, name);
		}
		else {
			if (next != null) {
				return super.readCategory(reader, name);
			} else {
				reader.skipValue();
				System.err.println("La categoria: '" + name + "' no es correcta.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) throws IOException {
		String usmaphys_title = null;
		String usmaphys_ima = null;
		String usmaphys_text = null;
		String phisio_ref = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
				usmaphys_title = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				usmaphys_ima = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				usmaphys_text = reader.nextString();
			} else if (name.equals(PHYREF_FIELD_TAGNAME)) {
				phisio_ref = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return usmaphys_title + FIELD_SEPARATOR + usmaphys_ima + FIELD_SEPARATOR + usmaphys_text + FIELD_SEPARATOR + phisio_ref;
	}

}
