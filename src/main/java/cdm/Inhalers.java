package cdm;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Inhalers extends CDM {

    private static final String INHALERS_TAGNAME = "inhalers";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEP = ";";

    public Inhalers(CDM s) {
        super(s);

    }

    public StringBuffer readCategory(JsonReader reader, String name)
            throws IOException {
        if (name.equals(INHALERS_TAGNAME)) {
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
        String i_name = null;
        String i_ima = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                i_name = reader.nextString();
            } else if (name.equals(IMAGE_FIELD_TAGNAME)) {
                i_ima = reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        return i_name + FIELD_SEP + i_ima;
    }

}
