package cdm;

import java.io.*;
import com.google.gson.stream.JsonReader;

public class UserManualSteps extends CDM{

	private static final String USERMANUAL_TAGNAME = "userManualSteps";
	private static final String STEPITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEP = ";";

	public UserManualSteps(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(USERMANUAL_TAGNAME)) {
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
		String usmas_title = null;
		String usmas_ima = null;
		String usmas_text = null;
		String inh_ref = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPITLE_FIELD_TAGNAME)) {
				usmas_title = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				usmas_ima = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				usmas_text = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				inh_ref = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return usmas_title + FIELD_SEP + usmas_ima + FIELD_SEP + usmas_text + FIELD_SEP + inh_ref;
	}
}
