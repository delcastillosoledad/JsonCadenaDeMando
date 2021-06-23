package cdm;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class Medicines extends CDM {

    private static final String MEDICINES_TAGNAME = "medicines";
    private static final String NAME_FIELD_TAGNAME = "name";

    public Medicines(CDM s) {
        super(s);
    }

    public StringBuffer readCategory(JsonReader reader, String name)
            throws IOException {
        if (name.equals(MEDICINES_TAGNAME)) {
            return super.everyCategory(reader, name);
        } else {
            if (next != null) {
                return super.readCategory(reader, name);
            } else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' no es correcta.");
                return new StringBuffer("");
            }
        }
    }

    public String readEntry(JsonReader reader)
            throws IOException {
        String med_name = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                med_name = reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        return med_name;
    }

}
