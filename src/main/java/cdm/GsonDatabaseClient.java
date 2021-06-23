package cdm;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			//se crea la cadena de mando:
			ActiveIngredients AI = new ActiveIngredients(null);
			Medicines M = new Medicines(AI);
			MedicinePresentations MP = new MedicinePresentations(M);
			Physiotherapies P = new Physiotherapies(MP);
			Posologies Po = new Posologies(P);
			RescueMedicinePresentations RMP = new RescueMedicinePresentations(Po);
			UserManualSteps UMS = new UserManualSteps(RMP);
			UserManualsPhysioSteps UMPS = new UserManualsPhysioSteps(UMS);
			DatabaseJSonReader DJR = new DatabaseJSonReader(UMPS);
			try {
				System.out.println(DJR.parse("./src/main/resources/datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
