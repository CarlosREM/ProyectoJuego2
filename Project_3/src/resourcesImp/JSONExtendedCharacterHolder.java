package resourcesImp;

import ADT.ExtendedDefaultCharacter;
import abstraction.ACharacter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;

/**
 * Class used to encapsulates the ACharacter list to save them on JSON files.
 * @author Fabricio Ceciliano
 * @author Carlos Esquivel
 * @author Marco Gamboa
 * @author Diego Murillo
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
public class JSONExtendedCharacterHolder {
    List<ExtendedDefaultCharacter> characters;
    
    /**
     * Method to create a new JSONCharacterHolder object.
     */
    public JSONExtendedCharacterHolder(){
        
    }

    /**
     * Method to create a new JSONCharacterHolder object with the specified information.
     * @param characters An ACharacter list.
     */
    public JSONExtendedCharacterHolder(List<ExtendedDefaultCharacter> characters) {
        this.characters = characters;
    }

    /**
     * Gets the ACharacter list.
     * @return A List of ACharacters.
     */
    public List<ExtendedDefaultCharacter> getCharacters() {
        return characters;
    }

    /**
     * Sets the ACharacter list.
     * @param characters The new ACharacter list.
     */
    public void setCharacters(List<ExtendedDefaultCharacter> characters) {
        this.characters = characters;
    }
}
